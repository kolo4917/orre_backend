package com.example.demo.DTO.ToClient;

import com.example.demo.model.DataBase.UserStoreWait;

public class UserStoreWaitResponse {
    private String status;
    private UserStoreWait token;


    // Getters and Setters


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserStoreWait getToken() {
        return token;
    }

    public void setToken(UserStoreWait token) {
        this.token = token;
    }
}

