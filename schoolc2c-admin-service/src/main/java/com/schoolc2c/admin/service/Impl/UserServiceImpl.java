package com.schoolc2c.admin.service.Impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.schoolc2c.bean.Pages;
import com.schoolc2c.bean.User;
import com.schoolc2c.service.UserService;
import com.schoolc2c.admin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Pages getAllUser(String query,int pageNum,int pageSize) {

        Pages pages=new Pages();
        int startNum=(pageNum-1)*pageSize;
        pages.setUserList(userMapper.selectPageUser(startNum,pageSize));
        List<User> userList = userMapper.selectAll();
        pages.setTotalPageNum(userList.size());
        pages.setPageNum(pageNum);

        return pages;

    }

    @Override
    public User updateUserState(User user) {

        int flag = userMapper.updateByPrimaryKey(user);

        if (flag>0) {return user;}
        else {return null;}

    }

    @Override
    public User insertUser(User user) {

        int flag = userMapper.insert(user);
        if (flag>0) {return user;}
        else {return null;}

    }

    @Override
    public int removeUser(User user) {

        int flag = userMapper.delete(user);

        if(flag>=0){
            return 200;
        }else {return 0;}

    }
}
