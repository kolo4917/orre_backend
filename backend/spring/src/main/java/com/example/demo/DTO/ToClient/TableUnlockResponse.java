package com.example.demo.DTO.ToClient;

public class TableUnlockResponse {
    private boolean success;
    private String jwtUser; // JWT 토큰 - 유저의 코드
    private int storeCode; // 가게 코드
    private int tableNumber; // 테이블 번호
    private int waitingNumber; // 웨이팅 번호
}
