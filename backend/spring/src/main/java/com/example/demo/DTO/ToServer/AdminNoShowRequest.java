package com.example.demo.DTO.ToServer;

public class AdminNoShowRequest {
    private Integer storeCode;
    private Integer noShowUserCode;
    public AdminNoShowRequest(Integer storeCode, Integer noShowUserCode) {
        this.storeCode = storeCode;
        this.noShowUserCode = noShowUserCode;
    }

    public Integer getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(Integer storeCode) {
        this.storeCode = storeCode;
    }

    public Integer getNoShowUserCode() {
        return noShowUserCode;
    }

    public void setNoShowUserCode(Integer noShowUserCode) {
        this.noShowUserCode = noShowUserCode;
    }

}
