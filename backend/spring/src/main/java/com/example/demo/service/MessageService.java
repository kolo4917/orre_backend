package com.example.demo.service;

import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;

public interface MessageService {
    SingleMessageSentResponse sendOne(Message message);
}
