package com.schoolc2c.trade.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.bean.ProductInfo;
import com.schoolc2c.service.ReleaseProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@CrossOrigin
public class ReleaseProductController {

    @Reference
    ReleaseProductService releaseProductService;

    @RequestMapping("imgUpload")
    @ResponseBody
    public String imgUpload(@RequestParam("file") MultipartFile multipartFile){

        String fileName = multipartFile.getOriginalFilename();
        String filePath = "F://img/";
        fileName = UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf("."));


        File file = new File(filePath+fileName);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(file);
        }catch (IOException e){
            e.printStackTrace();
        }

        return "http://localhost:8084/img/"+fileName;
    }


    @RequestMapping("releaseProduct")
    @ResponseBody
    public int ReleaseProduct(@RequestBody ProductInfo productInfo){

        int status = releaseProductService.addProductInfo(productInfo);

        return status;
    }
}
