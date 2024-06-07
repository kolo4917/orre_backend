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
        private Android android;
        private Apns apns;

        public Message() {
        }

        public Message(String token, Notification notification, Android android, Apns apns) {
            this.token = token;
            this.notification = notification;
            this.android = android;
            this.apns = apns;
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

        public Android getAndroid() {
            return android;
        }

        public void setAndroid(Android android) {
            this.android = android;
        }

        public Apns getApns() {
            return apns;
        }

        public void setApns(Apns apns) {
            this.apns = apns;
        }
    }

    public static class Notification {
        private String title;
        private String body;

        public Notification() {
        }

        public Notification(String title, String body) {
            this.title = title;
            this.body = body;
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
    }

    public static class Android {
        private AndroidNotification notification;

        public Android() {
        }

        public Android(AndroidNotification notification) {
            this.notification = notification;
        }

        public AndroidNotification getNotification() {
            return notification;
        }

        public void setNotification(AndroidNotification notification) {
            this.notification = notification;
        }

        public static class AndroidNotification {
            private String sound;

            public AndroidNotification() {
            }

            public AndroidNotification(String sound) {
                this.sound = sound;
            }

            public String getSound() {
                return sound;
            }

            public void setSound(String sound) {
                this.sound = sound;
            }
        }
    }

    public static class Apns {
        private ApnsPayload payload;

        public Apns() {
        }

        public Apns(ApnsPayload payload) {
            this.payload = payload;
        }

        public ApnsPayload getPayload() {
            return payload;
        }

        public void setPayload(ApnsPayload payload) {
            this.payload = payload;
        }

        public static class ApnsPayload {
            private Aps aps;

            public ApnsPayload() {
            }

            public ApnsPayload(Aps aps) {
                this.aps = aps;
            }

            public Aps getAps() {
                return aps;
            }

            public void setAps(Aps aps) {
                this.aps = aps;
            }

            public static class Aps {
                private String sound;

                public Aps() {
                }

                public Aps(String sound) {
                    this.sound = sound;
                }

                public String getSound() {
                    return sound;
                }

                public void setSound(String sound) {
                    this.sound = sound;
                }
            }
        }
    }
}
