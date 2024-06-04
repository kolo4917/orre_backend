package com.example.demo.DTO.ToServer;

public class AdminPhoneNumberRequest {
    private String adminPhoneNumber;
    public AdminPhoneNumberRequest() {
    }

    public AdminPhoneNumberRequest(String adminPhoneNumber) {
        this.adminPhoneNumber = adminPhoneNumber;
    }

    public String getAdminPhoneNumber() {
        return adminPhoneNumber;
    }

    public void setAdminPhoneNumber(String adminPhoneNumber) {
        this.adminPhoneNumber = adminPhoneNumber;
    }
}
