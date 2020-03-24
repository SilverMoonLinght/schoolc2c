package com.schoolc2c.trade.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.bean.Pages;
import com.schoolc2c.bean.ProductInfo;
import com.schoolc2c.service.ProductListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class GetProductListController {

    @Reference
    ProductListService productListService;

    @RequestMapping("getProductList")
    @ResponseBody
    public Pages getProductList(String catalog3Id,int pageNum, int pageSize){
        return productListService.getProductList(catalog3Id,pageNum,pageSize);
    }


}
