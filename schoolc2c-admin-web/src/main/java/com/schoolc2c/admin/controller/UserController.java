package com.schoolc2c.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.schoolc2c.bean.Pages;
import com.schoolc2c.bean.User;
import com.schoolc2c.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class UserController {

    @Reference
    UserService userService;

    @RequestMapping("getAllUser")
    @ResponseBody
    public Pages getAllUser(String query, int pagenum, int pagesize) {

        Pages pages = userService.getAllUser(query,pagenum,pagesize);

        return pages;
    }

    @RequestMapping("userUpdate")
    @ResponseBody
    public User userUpdate(@RequestBody User user){

        User newUser = userService.updateUserState(user);

        return user;
    }

    @RequestMapping("addUser")
    @ResponseBody
    public User addUser(@RequestBody User user){

        System.out.println(user);

        User insertUser = userService.insertUser(user);

        return user;
    }

    @RequestMapping("removeUser")
    @ResponseBody
    public int removeUser(@RequestBody User user){

        int reInfo = userService.removeUser(user);

        return reInfo;
    }
}
