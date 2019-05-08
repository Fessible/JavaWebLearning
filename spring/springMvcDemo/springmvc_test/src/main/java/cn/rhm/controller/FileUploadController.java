package cn.rhm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileUploadController {

    @RequestMapping("/uploadFile")
    public String upload(){
        return "success";
    }

}
