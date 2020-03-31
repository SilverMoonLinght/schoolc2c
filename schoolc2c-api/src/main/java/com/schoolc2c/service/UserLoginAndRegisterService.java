package com.schoolc2c.service;

import com.schoolc2c.bean.User;

import java.util.List;

public interface UserLoginAndRegisterService {
    User userLogin(User user);

    void addUserToken(String token, String id);

    String verifyToken(String token, String id);

    String userRegister(User user);

    User getUserByToken(String toString);

    String updateUserInfo(User user);

    String updatePass(String id, String oldPass, String newPass);
}
