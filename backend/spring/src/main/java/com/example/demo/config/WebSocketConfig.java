package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //registry.addEndpoint("/ws").setAllowedOrigins("http://localhost:8080").withSockJS(); // 소켓 통신 포트
        //registry.addEndpoint("/ws").setAllowedOrigins("http://localhost:63483").withSockJS(); // 소켓 통신 포트
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*");//.withSockJS(); - 스프링 내 html에서 stomp 통신시에는 필요함
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}
