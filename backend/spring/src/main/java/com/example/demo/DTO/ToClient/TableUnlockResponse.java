package com.example.demo.DTO.ToClient;

public class TableUnlockResponse {
    private boolean success;
    private String jwtUser; // JWT 토큰 - 유저의 코드
    private int storeCode; // 가게 코드
    private int tableNumber; // 테이블 번호
    private int waitingNumber; // 웨이팅 번호

    public TableUnlockResponse(boolean success, String jwtUser, int storeCode, int tableNumber, int waitingNumber) {
        this.success = success;
        this.jwtUser = jwtUser;
        this.storeCode = storeCode;
        this.tableNumber = tableNumber;
        this.waitingNumber = waitingNumber;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getJwtUser() {
        return jwtUser;
    }

    public void setJwtUser(String jwtUser) {
        this.jwtUser = jwtUser;
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
