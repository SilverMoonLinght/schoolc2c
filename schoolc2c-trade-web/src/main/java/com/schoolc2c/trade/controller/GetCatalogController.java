package com.schoolc2c.trade.controller;



import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.bean.ProductCatalog1;
import com.schoolc2c.bean.ProductCatalog2;
import com.schoolc2c.bean.ProductCatalog3;
import com.schoolc2c.service.GetCatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class GetCatalogController {


    @Reference
    GetCatalogService getCatalogService;

    @RequestMapping("getCatalog1")
    @ResponseBody
    public List<ProductCatalog1> getCatalog1(){

        List<ProductCatalog1> productCatalog1s= getCatalogService.getCatalog1();

        return productCatalog1s;
    }

    @RequestMapping("getCatalog2")
    @ResponseBody
    public List<ProductCatalog2> getCatalog2(String catalog1Id){

        return getCatalogService.getCatalog2(catalog1Id);
    }

    @RequestMapping("getCatalog3")
    @ResponseBody
    public List<ProductCatalog3> getCatalog3(String catalog2Id){
        return getCatalogService.getCatalog3(catalog2Id);
    }

    @RequestMapping("getCatalogList")
    @ResponseBody
    public List<ProductCatalog1> getCatalogList(){

        return getCatalogService.getCatalogList();
    }


}
