package com.schoolc2c.trade.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.annotations.LoginRequired;
import com.schoolc2c.annotations.PassToken;
import com.schoolc2c.bean.Booth;
import com.schoolc2c.bean.BoothProduct;
import com.schoolc2c.service.BoothService;
import com.schoolc2c.util.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class BoothController {

    @Reference
    BoothService boothService;

    @RequestMapping("getBoothInfo")
    @ResponseBody
    @LoginRequired
    public Booth getBoothInfo(HttpServletRequest request){

        String token = request.getHeader("token");
        Map<String,Object> map = JwtUtil.decode(token,"2016051146");
        String uid = map.get("id").toString();

        return boothService.getBoothInfo(uid);
    }

    @RequestMapping("createBooth")
    @ResponseBody
    @LoginRequired
    public String createBooth(HttpServletRequest request){

        String token = request.getHeader("token");
        Map<String,Object> map = JwtUtil.decode(token,"2016051146");
        String uid = map.get("id").toString();

        String status = boothService.createBooth(uid);

        return status;
    }


    @RequestMapping("getBoothProductList")
    @ResponseBody
    @LoginRequired
    public List<BoothProduct> getBoothProductList(String bid){
        return boothService.getBoothProductList(bid);
    }


    @RequestMapping("addBoothProduct")
    @ResponseBody
    @LoginRequired
    public String addBoothProduct(@RequestBody BoothProduct boothProduct){


        String status = boothService.addBoothProduct(boothProduct);

        return status;
    }

    @RequestMapping("editBoothProduct")
    @ResponseBody
    @LoginRequired
    public String editBoothProduct(@RequestBody BoothProduct boothProduct){

        String status = boothService.editBoothProduct(boothProduct);

        return status;
    }

    @RequestMapping("deleteBoothProduct")
    @ResponseBody
    @LoginRequired
    public String deleteBoothProduct(@RequestBody BoothProduct boothProduct){

        String status = boothService.deleteBoothProduct(boothProduct);

        return status;
    }

    @RequestMapping("getBoothList")
    @ResponseBody
    @PassToken
    public List<Booth> getBoothList(String bid){

        return boothService.getBoothList(bid);
    }

    @RequestMapping("clearBoothProduct")
    @ResponseBody
    @LoginRequired
    public String clearBoothProduct(String bid){
        return boothService.clearBoothProduct(bid);
    }

}
