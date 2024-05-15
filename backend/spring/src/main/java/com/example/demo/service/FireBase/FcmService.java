package com.example.demo.service.FireBase;

import com.example.demo.DTO.ToFireBase.FcmRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class FcmService {

    // Firebase FCM 메시지 전송 API URL
    private static final String API_URL = "https://fcm.googleapis.com/v1/projects/orre-be/messages:send";

    public void sendMessage(FcmRequest fcmRequest) throws IOException {
        // FCM 메시지를 JSON 문자열로 변환
        String message = makeMessage(fcmRequest);

        // RESTful API 호출을 위한 RestTemplate 객체 생성
        RestTemplate restTemplate = new RestTemplate();

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + getAccessToken());

        // HTTP 요청 엔터티 생성
        HttpEntity<String> entity = new HttpEntity<>(message, headers);

        // FCM 서버로 메시지 전송하고 응답 받음
        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

        // 응답 상태 코드 출력
        System.out.println(response.getStatusCode() == HttpStatus.OK ? 1 : 0);
    }

    private String getAccessToken() throws IOException {
        // Firebase 서비스 계정 키 파일 경로
        String firebaseConfigPath = "firebase/firebase_service_key.json";

        // 서비스 계정 키 파일을 사용하여 GoogleCredentials 객체 생성
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        // 토큰을 갱신하고 Bearer 토큰을 반환
        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }

    private String makeMessage(FcmRequest fcmRequest) throws JsonProcessingException {
        // ObjectMapper를 사용하여 FcmRequest 객체를 JSON 문자열로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(fcmRequest);
    }
}
