package com.schoolc2c.trade.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.annotations.LoginRequired;
import com.schoolc2c.bean.ProductInfo;
import com.schoolc2c.service.GetProductInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class GetProductInfoController {

    @Reference
    GetProductInfoService getProductInfoService;


    @RequestMapping("getProductInfo")
    @ResponseBody
    public ProductInfo getProductInfo(String id){

        return getProductInfoService.getProductInfo(id);
    }

    @RequestMapping("test")
    @ResponseBody
    @LoginRequired
    public String test(){

        return "111";
    }


}
