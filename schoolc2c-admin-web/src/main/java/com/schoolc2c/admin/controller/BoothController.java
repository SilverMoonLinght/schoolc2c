package com.schoolc2c.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.bean.Booth;
import com.schoolc2c.service.BoothService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class BoothController {

    @Reference
    BoothService boothService;

    @RequestMapping("getBoothList")
    @ResponseBody
    public List<Booth> getBoothList(){
        return boothService.getBoothList(null);
    }

    @RequestMapping("removeBooth")
    @ResponseBody
    public String removeBooth(@RequestBody Booth booth){
        return boothService.removeBooth(booth);
    }
}
