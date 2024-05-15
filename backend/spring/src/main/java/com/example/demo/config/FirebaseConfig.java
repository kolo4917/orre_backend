package com.example.demo.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp initializeFirebaseApp() throws IOException {
        // 상대 경로를 사용하여 serviceAccount 파일 읽기
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase/firebase_service_key.json");

        if (serviceAccount == null) {
            throw new IllegalStateException("firebase_service_key.json 파일을 찾을 수 없습니다.");
        }
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        return FirebaseApp.initializeApp(options);
    }
}
