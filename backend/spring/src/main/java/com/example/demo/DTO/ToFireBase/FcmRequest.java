package com.example.demo.DTO.ToFireBase;

public class FcmRequest {
    private Message message;

    public FcmRequest() {
    }

    public FcmRequest(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public static class Message {
        private String token;
        private Notification notification;

        public Message() {
        }

        public Message(String token, Notification notification) {
            this.token = token;
            this.notification = notification;
        }

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
    }

    public static class Notification {
        private String title;
        private String body;
        private String sound; // sound 필드 추가

        public Notification() {
        }

        public Notification(String title, String body, String sound) { // 생성자 수정
            this.title = title;
            this.body = body;
            this.sound = sound;
        }

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

        public String getSound() { // getter 추가
            return sound;
        }

        public void setSound(String sound) { // setter 추가
            this.sound = sound;
        }
    }
}
