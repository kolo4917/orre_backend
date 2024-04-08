package com.example.demo.DTO.ToServer;

public class TableLockRequest {
    private String jwtAdmin; // JWT 가게 토큰
    private int storeCode; // 가게 코드
    private int tableNumber; // 테이블 번호

    public TableLockRequest(String jwtAdmin, int storeCode, int tableNumber) {
        this.jwtAdmin = jwtAdmin;
        this.storeCode = storeCode;
        this.tableNumber = tableNumber;
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
}
