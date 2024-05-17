package com.example.demo.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp initializeFirebaseApp() throws IOException {
        // 상대 경로를 사용하여 serviceAccount 파일 읽기
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase/firebase_service_key.json");

        if (serviceAccount == null) {
            throw new IllegalStateException("firebase_service_key.json 파일을 찾을 수 없습니다.");
        }

        // InputStream을 문자열로 읽기
        StringBuilder jsonContent = new StringBuilder();
        int ch;
        while ((ch = serviceAccount.read()) != -1) {
            jsonContent.append((char) ch);
        }

        // 개행 문자 변환
        String modifiedJsonContent = jsonContent.toString().replace("\\n", "\n");

        // 변환된 문자열을 ByteArrayInputStream으로 다시 읽기
        ByteArrayInputStream correctedServiceAccount = new ByteArrayInputStream(modifiedJsonContent.getBytes(StandardCharsets.UTF_8));

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(correctedServiceAccount))
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
