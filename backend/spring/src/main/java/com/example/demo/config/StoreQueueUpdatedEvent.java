package com.example.demo.config;

import org.springframework.context.ApplicationEvent;

public class StoreQueueUpdatedEvent extends ApplicationEvent {
    private final Integer storeCode;

    public StoreQueueUpdatedEvent(Object source, Integer storeCode) {
        super(source);
        this.storeCode = storeCode;
    }

    public Integer getStoreCode() {
        return storeCode;
    }
}

