package com.schoolc2c.service;

import com.schoolc2c.bean.Admin;



public interface AdminLoginService {
    Admin adminLogin(String adminname, String password);
}
