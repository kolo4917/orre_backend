package com.example.demo.model.DataBase;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @Column(name = "admin_phone_number")
    private String adminPhoneNumber; // 데이터베이스 컬럼 이름과 일치해야 합니다.
    @Column(name = "admin_password")
    private String adminPassword;
    @Column(name = "admin_store_code")
    private Integer adminStoreCode;

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

    public Integer getAdminStoreCode() {
        return adminStoreCode;
    }

    public void setAdminStoreCode(Integer adminStoreCode) {
        this.adminStoreCode = adminStoreCode;
    }
}