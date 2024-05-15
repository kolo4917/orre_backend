package com.example.demo.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp initializeFirebaseApp() throws IOException {
        // 상대 경로를 사용하여 serviceAccount 파일 읽기
        Path serviceAccountPath = Paths.get("src/main/resources/firebase/firebase_service_key.json");
        byte[] serviceAccountBytes = Files.readAllBytes(serviceAccountPath);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(serviceAccountBytes)))
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
