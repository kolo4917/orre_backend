package com.example.demo.config.events;

import com.example.demo.DTO.ToClient.UserCallResponse;
import org.springframework.context.ApplicationEvent;

public class UserCallEvent extends ApplicationEvent {
    private final UserCallResponse userCallResponse;

    public UserCallEvent(Object source, UserCallResponse userCallResponse) {
        super(source);
        this.userCallResponse = userCallResponse;
    }

    public UserCallResponse getUserCallResponse() {
        return userCallResponse;
    }
}
