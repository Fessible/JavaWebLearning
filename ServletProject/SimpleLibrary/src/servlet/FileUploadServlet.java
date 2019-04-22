package servlet;

import bean.FileUploadBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import utils.FileUploadAppProperties;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class FileUploadServlet extends HttpServlet {

    private static final String FILE_PATH = "/WEB-INF/files/";

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
        try {
            //把需要上传的FileItem放入Map中
            Map<String, FileItem> uploadFiles = new HashMap<>();


            //解析请求，得到FileItem的集合
            List<FileItem> items = upload.parseRequest(req);

            //构建FileUploadBean的集合，同事填充uploadFiles
            List<FileUploadBean> beans = buildFileUploadBeans(items, uploadFiles);
            //校验扩展名
            vaidateExtName(beans);

            //校验文件大小，解析的时候已经校验，我们只需要通过异常的到结果

            //进行文件的上传操作
            upload(uploadFiles);

            //把上传的信息保存到数据库中
            saveBeans(beans);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    private void saveBeans(List<FileUploadBean> beans) {
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

    private void vaidateExtName(List<FileUploadBean> beans) {
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
            String filedName = item.getFieldName();
            String index = filedName.substring(filedName.length() - 1);

            String fileName = item.getName();
            String desc = descs.get("desc" + index);
            String filePath = getFilePath(fileName);

            FileUploadBean bean = new FileUploadBean(fileName, filePath, desc);
            beans.add(bean);
            upload.put(filePath, item);
        }
        return beans;

    }

    private String getFilePath(String fileName) {
        String extName = fileName.substring(fileName.indexOf("."));
        Random random = new Random();
        String filePath = getServletContext().getRealPath(FILE_PATH) + "\\" + System.currentTimeMillis() + random.nextInt() * 10000 + extName;
        return filePath;
    }

    private ServletFileUpload getServletUpload() {
        //1.得到FileItem的集合items
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 500);
        //临时目录
        File yourTempDirectory = new File("d:\\temp");
        factory.setRepository(yourTempDirectory);

// Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

// Set overall request size constraint
        //设置总的大小
        upload.setSizeMax(1024 * 1024 * 5);
        return upload;
    }
}
