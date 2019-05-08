package cn.rhm.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class FileUploadController {

    @RequestMapping("/uploadFile")
    public String upload(HttpServletRequest request) throws Exception {
        //使用fileupload组件上传文件

        //首先确定上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        System.out.println(path);

        //判断该路径是否存在
        File file = new File(path);
        if (!file.exists()) {
            //创建文件夹
            file.mkdirs();
        }

        //解析request对象，获取上传文件对象
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        //解析request
        List<FileItem> items = upload.parseRequest(request);

        //遍历
        for (FileItem fileItem : items) {
            //进行判断，当前的item对象是否为上传的文件项
            if (fileItem.isFormField()) {


            } else {
                String fileName = fileItem.getName();

                //设置文件存储唯一的文件名
                String uuid = UUID.randomUUID().toString().replace("-", "");
                fileName = uuid + "_" + fileName;

                fileItem.write(new File(path, fileName));

                //删除临时文件
                fileItem.delete();

            }
        }
        return "success";
    }

    @RequestMapping("/fileupload2")
    public String upload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        //使用fileupload组件上传文件

        //首先确定上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        System.out.println(path);

        //判断该路径是否存在
        File file = new File(path);
        if (!file.exists()) {
            //创建文件夹
            file.mkdirs();
        }


        //获取上传的文件名
        String fileName = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //文件名唯一化
        fileName = uuid + "_" + fileName;

        //上传文件
        upload.transferTo(new File(path, fileName));
        return "success";
    }


    //跨服务器上传
    @RequestMapping("/fileupload3")
    public String upload3(MultipartFile upload) throws IOException {
        System.out.println("跨服务器上传文件");

        //定义上传文件服务器路径
        String path = "http://localhost:8089/fileupload/uploads/";

        //设置文件名
        String fileName = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        fileName = uuid + "_" + fileName;

        //创建客户端对象
        Client client = Client.create();

        //和图片服务器进行连接
        WebResource resource = client.resource(path + fileName);

        //上传文件
        resource.put(upload.getBytes());


        return "success";
    }

}
