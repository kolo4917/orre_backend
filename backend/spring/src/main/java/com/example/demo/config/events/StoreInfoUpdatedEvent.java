package com.example.demo.config.events;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class StoreInfoUpdatedEvent extends ApplicationEvent {

    private final Integer storeCode;

    public StoreInfoUpdatedEvent(Object source, Integer storeCode) {
        super(source);
        this.storeCode = storeCode;
    }

    public Integer getStoreCode() {
        return storeCode;
    }
}
