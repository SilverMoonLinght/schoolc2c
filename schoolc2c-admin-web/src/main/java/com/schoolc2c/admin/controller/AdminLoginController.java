package com.schoolc2c.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.bean.Admin;
import com.schoolc2c.service.AdminLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@CrossOrigin
public class AdminLoginController {

    @Reference
    AdminLoginService adminLoginService;

    @RequestMapping("/adminLogin")
    @ResponseBody
    public Admin adminLogin(@RequestBody Map<String,String> login){
        String adminname=login.get("adminname");
        String password=login.get("password");

        System.out.println(login);

        Admin admin = adminLoginService.adminLogin(adminname,password);

        return admin;
    }


}
