package servlet;

import bean.FileUploadBean;
import dao.FileUploadDao;
import dao.FileUploadDaoImp;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import utils.FileUploadAppProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.*;
import java.util.*;

public class FileUploadServlet extends HttpServlet {

    private static final String FILE_PATH = "upload";
    private String uploadPath = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String exts = FileUploadAppProperties.getInstance().getProperty("exts");
        String maxsize = FileUploadAppProperties.getInstance().getProperty("file.max.size");
        String totalMaxSize = FileUploadAppProperties.getInstance().getProperty("total.file.max.size");

        System.out.println(exts);
        System.out.println(maxsize);
        System.out.println(totalMaxSize);
        ServletFileUpload upload = getServletUpload();
        String path = "/jsp/success.jsp";

        //uploadPath ---/Users/rhm/github/JavaWebLearning/SimpleLibrary/out/artifacts/SimpleLibrary_war_exploded//upload
        uploadPath = req.getServletContext().getRealPath("./") + File.separator + FILE_PATH;
        System.out.println("uploadPath ---" + uploadPath);
        //上传目录
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            //把需要上传的FileItem放入Map中
            Map<String, FileItem> uploadFiles = new HashMap<>();


            //解析请求，得到FileItem的集合
            List<FileItem> items = upload.parseRequest(req);

            //构建FileUploadBean的集合，同事填充uploadFiles
            List<FileUploadBean> beans = buildFileUploadBeans(items, uploadFiles);
            //校验扩展名
            validateExtName(beans);

            //校验文件大小，解析的时候已经校验，我们只需要通过异常的到结果

            //进行文件的上传操作
            upload(uploadFiles);

            //把上传的信息保存到数据库中
            saveBeans(beans);
        } catch (IOException e) {
            e.printStackTrace();

            //超过文件大小会自动报异常
            System.out.println("超出异常");

        } catch (ValidationException e) {
            e.printStackTrace();
            path = "/jsp/error.jsp";
            req.setAttribute("message", e.getMessage());
        } catch (FileUploadException e) {
            e.printStackTrace();
            System.out.println("超出文件限制大小");
            req.setAttribute("message", "超出文件限制大小");
            req.getRequestDispatcher("/jsp/uploadFile.jsp").forward(req, resp);
        }

        req.getRequestDispatcher(path).forward(req, resp);

    }

    private void saveBeans(List<FileUploadBean> beans) {
        FileUploadDao dao = new FileUploadDaoImp();
        for (FileUploadBean uploadBean : beans) {
            dao.save(uploadBean);
        }
    }

    private void upload(Map<String, FileItem> uploadFiles) throws IOException {
        for (Map.Entry<String, FileItem> uploadFile : uploadFiles.entrySet()) {
            String filePath = uploadFile.getKey();
            FileItem fileItem = uploadFile.getValue();
            upload(filePath, fileItem.getInputStream());
        }
    }

    private void upload(String filePath, InputStream inputStream) throws IOException {
        OutputStream outputStream = new FileOutputStream(filePath);

        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }

        inputStream.close();
        outputStream.close();
    }

    //抛出异常，由上一个处理
    private void validateExtName(List<FileUploadBean> beans) throws ValidationException {
        String exts = FileUploadAppProperties.getInstance().getProperty("exts");
        List<String> extList = Arrays.asList(exts.split(","));

        for (FileUploadBean bean : beans) {
            String fileName = bean.getFileName();
            String extName = fileName.substring(fileName.indexOf(".")+1);
            if (!extList.contains(extName)) {
                throw new ValidationException(fileName + "文件的扩展名不合法");
            }

        }


    }

    private List<FileUploadBean> buildFileUploadBeans(List<FileItem> items, Map<String, FileItem> upload) {
        Map<String, String> descs = new HashMap<>();
        List<FileUploadBean> beans = new ArrayList<>();

        for (FileItem item : items) {
            if (item.isFormField()) {
                descs.put(item.getFieldName(), item.getString());
            }
        }

        //根据filed去匹配desc
        for (FileItem item : items) {
            if (!item.isFormField()) {
                String filedName = item.getFieldName();
                String index = filedName.substring(filedName.length() - 1);

                String fileName = item.getName();
                String desc = descs.get("desc" + index);
                String filePath = getFilePath(fileName);

                FileUploadBean bean = new FileUploadBean(fileName, filePath, desc);
                beans.add(bean);
                upload.put(filePath, item);
            }
        }
        return beans;

    }

    private String getFilePath(String fileName) {
        String extName = fileName.substring(fileName.indexOf("."));
        Random random = new Random();
        String filePath = uploadPath + File.separator + System.currentTimeMillis() + random.nextInt() * 10000 + extName;
        return filePath;
    }

    private ServletFileUpload getServletUpload() {
        //1.得到FileItem的集合items
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 500);
        //临时目录
//        File yourTempDirectory = new File("d:\\temp");
        //mac
        File yourTempDirectory = new File("/Users/rhm/temp");
        factory.setRepository(yourTempDirectory);

// Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

// Set overall request size constraint
        //设置总的大小
        upload.setSizeMax(1024 * 1024 * 5);
        return upload;
    }
}
