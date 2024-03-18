package com.example.demo.DTO.ToClient;

public class LoginResponse {
    private String status;
    private String token;



    private int storeCode;

    public LoginResponse(String status, String token, int storeCode) {
        this.status = status;
        this.token = token;
        this.storeCode = storeCode;
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }
}
