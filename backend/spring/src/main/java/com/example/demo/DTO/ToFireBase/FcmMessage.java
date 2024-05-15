package com.example.demo.DTO.ToFireBase;

import java.util.Map;

public class FcmMessage {
    private String token; //메시지를 받을 FCM 토큰
    private Notification notification; // 알림 제목과 본문

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }


    public static class Notification {
        private String title;
        private String body;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }

}
