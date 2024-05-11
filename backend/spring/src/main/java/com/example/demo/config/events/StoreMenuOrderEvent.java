package com.example.demo.config.events;

import com.example.demo.DTO.ToServer.StoreMenuOrderRequest;
import org.springframework.context.ApplicationEvent;

public class StoreMenuOrderEvent extends ApplicationEvent {
    private final StoreMenuOrderRequest request;

    public StoreMenuOrderEvent(Object source, StoreMenuOrderRequest request) {
        super(source);
        this.request = request;
    }

    public StoreMenuOrderRequest getRequest() {
        return request;
    }
}
