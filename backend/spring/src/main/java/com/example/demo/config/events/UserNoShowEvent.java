package com.example.demo.config.events;

import com.example.demo.DTO.ToClient.UserStoreWaitResponse;
import org.springframework.context.ApplicationEvent;

public class UserNoShowEvent extends ApplicationEvent {
    private final String userPhoneNumber;
    private final int storeCode;

    private final UserStoreWaitResponse userStoreWaitResponse;

    public UserNoShowEvent(Object source, String userPhoneNumber, int storeCode, UserStoreWaitResponse userStoreWaitResponse) {
        super(source);
        this.userPhoneNumber = userPhoneNumber;
        this.storeCode = storeCode;
        this.userStoreWaitResponse = userStoreWaitResponse;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public UserStoreWaitResponse getUserStoreWaitResponse() {
        return userStoreWaitResponse;
    }
}
