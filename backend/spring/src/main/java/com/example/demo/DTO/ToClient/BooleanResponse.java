package com.example.demo.DTO.ToClient;

public class BooleanResponse {

    private boolean success;

    public BooleanResponse(boolean success) {
        this.success = success;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


}

