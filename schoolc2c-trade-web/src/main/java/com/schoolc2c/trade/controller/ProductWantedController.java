package com.schoolc2c.trade.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.annotations.LoginRequired;
import com.schoolc2c.bean.ProductWanted;
import com.schoolc2c.service.ProductWantedService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class ProductWantedController {

    private String status;

    @Reference
    ProductWantedService productWantedService;


    @RequestMapping("editProductWanted")
    @ResponseBody
    @LoginRequired
    public String editProductWanted(@RequestBody ProductWanted productWanted){

        status = productWantedService.editProductWanted(productWanted);

        return status;
    }


    @RequestMapping("removeProductWanted")
    @ResponseBody
    @LoginRequired
    public String removeProductWanted(@RequestBody ProductWanted productWanted){

        status = productWantedService.removeProductWanted(productWanted);

        return status;

    }
}
