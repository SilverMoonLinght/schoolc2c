package com.schoolc2c.trade.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.schoolc2c.bean.Message;
import com.schoolc2c.bean.User;
import com.schoolc2c.service.MessageService;
import com.schoolc2c.trade.mapper.MessageMapper;
import com.schoolc2c.trade.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    UserMapper userMapper;


    @Override
    public String releaseMessage(Message message) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            message.setDate(simpleDateFormat.parse(simpleDateFormat.format(new Date())));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        int flag = messageMapper.insert(message);
        if (flag>0){
            return "success";
        }


        return "fail";
    }

    @Override
    public List<Message> getMessage(String id) {

        User user =new User();

        Message message =new Message();
        message.setPid(id);


        //格式化时间戳形式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Message> messageList= messageMapper.select(message);
        for (int i=0;i<messageList.size();i++){
            user = userMapper.selectByPrimaryKey(messageList.get(i).getUid());
            messageList.get(i).setNickname(user.getNickname());
            messageList.get(i).setIcon(user.getIcon());
            //将时间戳转换为String形式
            messageList.get(i).setDatetime(simpleDateFormat.format(messageList.get(i).getDate()));
        }

        return messageList;
    }
}
