package com.schoolc2c.trade.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@CrossOrigin
public class ReleaseProductController {

    @RequestMapping("imgUpload")
    @ResponseBody
    public String imgUpload(@RequestParam("file") MultipartFile multipartFile){


        return "success";
    }
}
