package com.example.demo.DTO.ToServer;

public class AdminClosingRequest {
    private Integer storeCode;
    private String jwtAdmin;

    public AdminClosingRequest(Integer storeCode, String jwtAdmin) {
        this.storeCode = storeCode;
        this.jwtAdmin = jwtAdmin;
    }

    public Integer getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(Integer storeCode) {
        this.storeCode = storeCode;
    }

    public String getJwtAdmin() {
        return jwtAdmin;
    }

    public void setJwtAdmin(String jwtAdmin) {
        this.jwtAdmin = jwtAdmin;
    }
}
