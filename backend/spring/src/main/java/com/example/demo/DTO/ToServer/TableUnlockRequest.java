package com.example.demo.DTO.ToServer;

public class TableUnlockRequest {
    private String jwtAdmin; // JWT 토큰 - 가게의 코드
    private int storeCode; // 가게 코드
    private int tableNumber; // 테이블 번호
    private int waitingNumber; // 웨이팅 번호

    public TableUnlockRequest(String jwtAdmin, int storeCode, int tableNumber, int waitingNumber) {
        this.jwtAdmin = jwtAdmin;
        this.storeCode = storeCode;
        this.tableNumber = tableNumber;
        this.waitingNumber = waitingNumber;
    }

    public String getJwtAdmin() {
        return jwtAdmin;
    }

    public void setJwtAdmin(String jwtAdmin) {
        this.jwtAdmin = jwtAdmin;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getWaitingNumber() {
        return waitingNumber;
    }

    public void setWaitingNumber(int waitingNumber) {
        this.waitingNumber = waitingNumber;
    }
}
