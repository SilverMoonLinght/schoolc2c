package com.schoolc2c.service;

import com.schoolc2c.bean.Pages;
import com.schoolc2c.bean.User;


public interface UserService {
    Pages getAllUser(String query, int pageNum, int pageSize);

    User updateUserState(User user);

    User insertUser(User user);

    int removeUser(User user);

}
