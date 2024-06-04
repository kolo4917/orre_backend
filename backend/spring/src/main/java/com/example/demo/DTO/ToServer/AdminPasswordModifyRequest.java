package com.example.demo.DTO.ToServer;

public class AdminPasswordModifyRequest {
    private String adminPhoneNumber;
    private String adminPassword;
    private String newAdminPassword;
    private String jwtAdmin;

    public AdminPasswordModifyRequest(String adminPhoneNumber, String adminPassword, String newAdminPassword, String jwtAdmin) {
        this.adminPhoneNumber = adminPhoneNumber;
        this.adminPassword = adminPassword;
        this.newAdminPassword = newAdminPassword;
        this.jwtAdmin = jwtAdmin;
    }

    public String getAdminPhoneNumber() {
        return adminPhoneNumber;
    }

    public void setAdminPhoneNumber(String adminPhoneNumber) {
        this.adminPhoneNumber = adminPhoneNumber;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getNewAdminPassword() {
        return newAdminPassword;
    }

    public void setNewAdminPassword(String newAdminPassword) {
        this.newAdminPassword = newAdminPassword;
    }

    public String getJwtAdmin() {
        return jwtAdmin;
    }

    public void setJwtAdmin(String jwtAdmin) {
        this.jwtAdmin = jwtAdmin;
    }
}
