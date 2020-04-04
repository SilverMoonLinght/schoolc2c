package com.schoolc2c.service;

import com.schoolc2c.bean.Message;

import java.util.List;

public interface MessageService {
    String releaseMessage(Message message);

    List<Message> getMessage(String id);
}
