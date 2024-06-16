package com.example.demo.DTO.ToServer;

public class AdminDeleteUserRequest {
    private Integer storeCode;
    private Integer deleteUserCode;

    public AdminDeleteUserRequest(Integer storeCode, Integer deleteUserCode) {
        this.storeCode = storeCode;
        this.deleteUserCode = deleteUserCode;
    }

    public Integer getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(Integer storeCode) {
        this.storeCode = storeCode;
    }

    public Integer getDeleteUserCode() {
        return deleteUserCode;
    }

    public void setDeleteUserCode(Integer deleteUserCode) {
        this.deleteUserCode = deleteUserCode;
    }
}
