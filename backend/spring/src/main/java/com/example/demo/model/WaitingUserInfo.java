package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WaitingUserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String serverCode;
    private String myName;
    private String phoneNumber;
    private int numberOfUs;

    @Override
    public String toString() {
        return "WaitingUserInfo{" +
                "id=" +
                ", serverCode='" + serverCode + '\'' +
                ", myName='" + myName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", numberOfUs=" + numberOfUs +
                '}';
    }
    // 기본 생성자
    public WaitingUserInfo() {
    }

    // 모든 필드를 포함한 생성자
    public WaitingUserInfo(String myName, String phoneNumber, int numberOfUs) {
        this.serverCode = serverCode;
        this.myName = myName;
        this.phoneNumber = phoneNumber;
        this.numberOfUs = numberOfUs;
    }

    // 게터와 세터 가고싶다...

    public String getServerCode() {
        return serverCode;
    }

    public void setServerCode(String serverCode) {
        this.serverCode = serverCode;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getNumberOfUs() {
        return numberOfUs;
    }

    public void setNumberOfUs(int numberOfUs) {
        this.numberOfUs = numberOfUs;
    }
}
