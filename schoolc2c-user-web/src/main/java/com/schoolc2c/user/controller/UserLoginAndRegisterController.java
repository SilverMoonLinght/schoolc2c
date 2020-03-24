package com.schoolc2c.user.controller;


import com.schoolc2c.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class UserLoginAndRegisterController {


    @RequestMapping("userLogin")
    @ResponseBody
    public User userLogin(@RequestBody User user){



        return user;

    }


}
