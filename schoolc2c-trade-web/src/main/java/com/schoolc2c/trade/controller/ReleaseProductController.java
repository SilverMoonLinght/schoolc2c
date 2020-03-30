package com.schoolc2c.trade.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.annotations.LoginRequired;
import com.schoolc2c.annotations.PassToken;
import com.schoolc2c.bean.Pages;
import com.schoolc2c.bean.ProductInfo;
import com.schoolc2c.bean.ProductWanted;
import com.schoolc2c.service.ReleaseProductService;
import com.schoolc2c.util.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
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
    @LoginRequired
    public int ReleaseProduct(HttpServletRequest request,@RequestBody ProductInfo productInfo){

        String token = request.getHeader("token");

        Map<String,Object> map = JwtUtil.decode(token,"2016051146");
        String id = map.get("id").toString();
        productInfo.setUserId(id);

        int status = releaseProductService.addProductInfo(productInfo);

        return status;
    }


    @RequestMapping("productWanted")
    @ResponseBody
    @LoginRequired
    public String productWanted(HttpServletRequest request,@RequestBody ProductWanted productWanted){

        String token = request.getHeader("token");

        Map<String,Object> map = JwtUtil.decode(token,"2016051146");
        String id = map.get("id").toString();
        productWanted.setUserId(id);

        String flag=releaseProductService.addProductWanted(productWanted);
        return flag;
    }


    @RequestMapping("getProductWantedList")
    @ResponseBody
    @PassToken
    public Pages getProductWantedList(int pageNum, int pageSize){

        return releaseProductService.getProductWantedList(pageNum,pageSize);

    }
}
