package com.schoolc2c.user.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.schoolc2c.bean.User;
import com.schoolc2c.service.UserLoginAndRegisterService;
import com.schoolc2c.user.mapper.UserMapper;
import com.schoolc2c.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;




@Service
public class UserLoginAndRegisterServiceImpl implements UserLoginAndRegisterService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisUtil redisUtil;


    @Override
    public User userLogin(User user) {

        User users = userMapper.selectOne(user);

        return users;
    }

    @Override
    public void addUserToken(String token, String id) {

        Jedis jedis = redisUtil.getJedis();

        jedis.setex("user:" + id + ":token", 60 * 60 * 2, token);

        jedis.close();
    }

    @Override
    public String verifyToken(String token, String id) {

        Jedis jedis = redisUtil.getJedis();

        String oldToken = jedis.get("user:"+id+":token");

        jedis.close();
        if (StringUtils.isBlank(oldToken)){
            return "fail";
        }
        if (!oldToken.equals(token)){
            return "fail";
        }

        return "success";
    }

    @Override
    public String userRegister(User user) {


        int flag=0;
        User user1 = new User();
        user1.setUsername(user.getUsername());

        User user2 = userMapper.selectOne(user1);

        if (user2 == null){
            user.setGender("0");
            user.setStatus("1");
            flag = userMapper.insert(user);
        }

        if (flag>0){
            return "success";
        }
        return "fail";
    }

    @Override
    public User getUserByToken(String toString) {
        return userMapper.selectByPrimaryKey(toString);
    }

    @Override
    public String updateUserInfo(User user) {

        int status = userMapper.updateByPrimaryKey(user);
        if (status > 0){
            return "success";
        }
        return "fail";
    }

    @Override
    public String updatePass(String id, String oldPass, String newPass) {

        User user = userMapper.selectByPrimaryKey(id);

        int flag = 0;
        if (!user.getPassword().equals(oldPass)){
            return "fail";
        }
        user.setPassword(newPass);
        flag = userMapper.updateByPrimaryKey(user);
        if (flag>0){
            return "success";
        }

        return "error";
    }

}
