package com.schoolc2c.trade.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.schoolc2c.annotations.LoginRequired;
import com.schoolc2c.annotations.PassToken;
import com.schoolc2c.bean.Message;
import com.schoolc2c.service.MessageService;
import com.schoolc2c.util.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class MessageController {

    @Reference
    MessageService messageService;


    @RequestMapping("releaseMessage")
    @ResponseBody
    @LoginRequired
    public String releaseMessage(HttpServletRequest request, @RequestBody Message message){

        String token = request.getHeader("token");
        Map<String,Object> map = JwtUtil.decode(token,"2016051146");
        String id = map.get("id").toString();

        message.setUid(id);
        String status = messageService.releaseMessage(message);

        return status;
    }


    @RequestMapping("getMessageByPid")
    @ResponseBody
    @PassToken
    public List<Message> getMessageByPid(String id){

        return messageService.getMessage(id);

    }

}
