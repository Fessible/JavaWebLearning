package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;

public class uploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        InputStream in = req.getInputStream();
//
//        Reader reader = new InputStreamReader(in);
//        BufferedReader bufferedReader = new BufferedReader(reader);
//        String str = null;
//
//        while ((str = bufferedReader.readLine()) != null) {
//            System.out.println(str);
//        }

        //1.得到FileItem的集合items
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

// Set factory constraints
        factory.setSizeThreshold(1024 * 500);
        //临时目录
        File yourTempDirectory = new File("d:\\temp");
        factory.setRepository(yourTempDirectory);

// Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

// Set overall request size constraint
        //设置总的大小
        upload.setSizeMax(1024 * 1024 * 5);

// Parse the request
        try {
            List<FileItem> items = upload.parseRequest(req);

            //遍历items
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();

                //如果是表单域
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString();
                    System.out.println(name + "----" + value);
                } else {
                    //若是文件域，就保存到tempDirectory中
                    String fieldName = item.getFieldName();
                    String fileName = item.getName();
                    String contentType = item.getContentType();
                    boolean isInMemory = item.isInMemory();
                    long sizeInBytes = item.getSize();
                    System.out.println("fieldName----" + fieldName);
                    System.out.println("fileName----" + fileName);
                    System.out.println("contentType---" + contentType);
                    System.out.println("isInMemory---" + isInMemory);
                    System.out.println("sizeInBytes---" + sizeInBytes);

                    InputStream inputStream = item.getInputStream();
                    byte[] bytes = new byte[1024];
                    int len = 0;

                    fileName = yourTempDirectory + "\\" + fileName;
                    System.out.println("temp" + fileName);
                    OutputStream outputStream = new FileOutputStream(fileName);

                    while ((len = inputStream.read(bytes)) != -1) {
                        System.out.println("len ----" + len);
                        outputStream.write(bytes, 0, len);
                    }
                    inputStream.close();
                    outputStream.close();
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        }

    }
}
