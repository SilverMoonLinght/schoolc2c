package com.schoolc2c.user.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.annotations.LoginRequired;
import com.schoolc2c.annotations.PassToken;
import com.schoolc2c.bean.User;
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
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class UserLoginAndRegisterController {

    @Reference
    UserLoginAndRegisterService userLoginAndRegisterService;



    @RequestMapping("userLogin")
    @ResponseBody
    @PassToken
    public String userLogin(@RequestBody User user){

        String token="";

        User user1 = userLoginAndRegisterService.userLogin(user);

        if (user1 != null){
            String id = user1.getId();

            Map<String,Object> map = new HashMap<>();
            map.put("id",id);

            token = JwtUtil.encode("2016051146",map);

            userLoginAndRegisterService.addUserToken(token,id);

        }else {
            token = "fail";
        }

        return token;

    }

    @RequestMapping("userRegister")
    @ResponseBody
    @PassToken
    public String userRegister(@RequestBody User user){

        String flag = userLoginAndRegisterService.userRegister(user);

        return flag;
    }


    @RequestMapping("getUserByToken")
    @ResponseBody
    @LoginRequired
    public User getUserByToken(HttpServletRequest request){

        String token = request.getHeader("token");

        Map<String,Object> map = JwtUtil.decode(token,"2016051146");

        Object id = map.get("id");

        User user = userLoginAndRegisterService.getUserByToken(id.toString());

        return user;
    }


}
