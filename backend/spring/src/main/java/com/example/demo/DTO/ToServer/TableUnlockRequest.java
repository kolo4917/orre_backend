package com.example.demo.DTO.ToServer;

public class TableUnlockRequest {
    private String jwtAdmin; // JWT 토큰 - 가게의 코드
    private int storeCode; // 가게 코드
    private int tableNumber; // 테이블 번호
    private int waitingNumber; // 웨이팅 번호
}
