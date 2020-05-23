package com.schoolc2c.trade.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.annotations.PassToken;
import com.schoolc2c.bean.Pages;
import com.schoolc2c.bean.ProductSearchInfo;
import com.schoolc2c.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@CrossOrigin
public class SearchController {

    @Reference
    SearchService searchService;

    @RequestMapping("searchProduct")
    @ResponseBody
    @PassToken
    public Pages searchProduct(int pageNum, int pageSize, String searchInfo)  {

        Pages pages = new Pages();
        try {
            pages = searchService.searchProduct(pageNum,pageSize,searchInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pages;
    }
}
