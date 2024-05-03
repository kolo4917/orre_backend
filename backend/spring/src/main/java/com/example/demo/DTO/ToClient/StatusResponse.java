package com.example.demo.DTO.ToClient;

public class StatusResponse {

    private String success;

    public StatusResponse(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}

