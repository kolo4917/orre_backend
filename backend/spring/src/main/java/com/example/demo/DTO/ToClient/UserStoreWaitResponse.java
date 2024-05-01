package com.example.demo.DTO.ToClient;

import com.example.demo.model.DataBase.UserStoreWait;

public class UserStoreWaitResponse {
    private boolean success;
    private String message;
    private UserStoreWait waitingDetails;


    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserStoreWait getWaitingDetails() {
        return waitingDetails;
    }

    public void setWaitingDetails(UserStoreWait waitingDetails) {
        this.waitingDetails = waitingDetails;
    }
}

