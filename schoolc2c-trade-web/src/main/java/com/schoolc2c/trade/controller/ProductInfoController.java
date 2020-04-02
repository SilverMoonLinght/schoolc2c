package com.schoolc2c.trade.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.annotations.LoginRequired;
import com.schoolc2c.bean.ProductInfo;
import com.schoolc2c.service.ProductInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class ProductInfoController {

    @Reference
    ProductInfoService productInfoService;


    @RequestMapping("getProductInfo")
    @ResponseBody
    public ProductInfo getProductInfo(String id){

        return productInfoService.getProductInfo(id);
    }


    @RequestMapping("editProductInfo")
    @ResponseBody
    @LoginRequired
    public String editProductInfo(@RequestBody ProductInfo productInfo){

        String status = productInfoService.editProductInfo(productInfo);

        return status;
    }

    @RequestMapping("removeProduct")
    @ResponseBody
    @LoginRequired
    public String removeProduct(@RequestBody ProductInfo productInfo){

        String status = productInfoService.removeProduct(productInfo);

        return status;
    }

}
