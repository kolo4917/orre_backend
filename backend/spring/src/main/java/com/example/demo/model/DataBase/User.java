package com.example.demo.model.DataBase;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @Column(name = "user_phone_number")
    private String phoneNumber;

    @Column(name = "user_store_code")
    private Integer storeCode;

    @Column(name = "user_pw")
    private String password;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_token")
    private String token;

    @Column(name = "user_credit_info")
    private Integer creditInfo;

    @Column(name = "user_location")
    private String location;
    @Column(name = "user_fcm_token")
    private String userFcmToken;

    public User() {}

    public User(String phoneNumber, Integer storeCode, String password, String name, String token, Integer creditInfo, String location, String fcmToken) {
        this.phoneNumber = phoneNumber;
        this.storeCode = storeCode;
        this.password = password;
        this.name = name;
        this.token = token;
        this.creditInfo = creditInfo;
        this.location = location;
        this.userFcmToken = userFcmToken;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Integer getStoreCode() { return storeCode; }
    public void setStoreCode(Integer storeCode) { this.storeCode = storeCode; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Integer getCreditInfo() { return creditInfo; }
    public void setCreditInfo(Integer creditInfo) { this.creditInfo = creditInfo; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getUserFcmToken() {
        return userFcmToken;
    }

    public void setUserFcmToken(String userFcmToken) {
        this.userFcmToken = userFcmToken;
    }

// toString, equals, hashCode 메소드는 필요에 따라 구현
}
