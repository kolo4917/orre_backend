package com.example.demo.config.events.listener;

import javax.sql.DataSource;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionListener implements ApplicationListener<ApplicationReadyEvent> {

    private final DataSource dataSource;

    public DatabaseConnectionListener(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (dataSource != null) {
            System.out.println("데이터베이스 연결 성공!");
        } else {
            System.out.println("데이터베이스 연결 실패. 데이터 소스가 null입니다.");
        }
    }
}
