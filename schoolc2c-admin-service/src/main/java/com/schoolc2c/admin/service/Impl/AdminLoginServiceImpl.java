package com.schoolc2c.admin.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.schoolc2c.admin.mapper.AdminMapper;
import com.schoolc2c.bean.Admin;
import com.schoolc2c.bean.User;
import com.schoolc2c.service.AdminLoginService;
import com.schoolc2c.admin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin adminLogin(String adminname, String password) {

        Admin admin = adminMapper.selectAdmin(adminname);

        if (admin != null){
            if (password.equals(admin.getPassword())){
                return admin;
            }else return null;
        }

        else return null;
    }
}
