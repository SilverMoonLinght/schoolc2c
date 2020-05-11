package com.schoolc2c.trade.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.annotations.LoginRequired;
import com.schoolc2c.annotations.PassToken;
import com.schoolc2c.bean.Pages;
import com.schoolc2c.bean.ProductInfo;
import com.schoolc2c.bean.ProductWanted;
import com.schoolc2c.service.ProductListService;
import com.schoolc2c.util.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("getProductByUser")
    @ResponseBody
    @LoginRequired
    public List<ProductInfo> getProductByUser(HttpServletRequest request){

        String token = request.getHeader("token");
        Map<String,Object> map = JwtUtil.decode(token,"2016051146");
        String id = map.get("id").toString();

        List<ProductInfo> productInfoList = productListService.getProductListByUser(id);

        return productInfoList;
    }


    @RequestMapping("getProductWantedByUser")
    @ResponseBody
    @LoginRequired
    public List<ProductWanted> getProductWantedByUser(HttpServletRequest request){

        String token = request.getHeader("token");
        Map<String,Object> map = JwtUtil.decode(token,"2016051146");
        String id = map.get("id").toString();

        return productListService.getProductWantedByUser(id);
    }


    @RequestMapping("getHotProduct")
    @ResponseBody
    @PassToken
    public List<ProductInfo> getHotProduct(){
        return productListService.getHotProduct();
    }

}
