package com.schoolc2c.passport.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.annotations.LoginRequired;
import com.schoolc2c.service.UserLoginAndRegisterService;
import com.schoolc2c.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
public class PassportController {

    @Reference
    UserLoginAndRegisterService userLoginAndRegisterService;


    @RequestMapping("nullToken")
    @ResponseBody
    public String index(){
        return "fail";
    }

    @RequestMapping("verifyToken")
    @ResponseBody
    public String verifyToken(String token){


        Map<String,Object> map = JwtUtil.decode(token,"2016051146");

        Object id = map.get("id");

        String verify = userLoginAndRegisterService.verifyToken(token,id.toString());

        return verify;
    }
}
