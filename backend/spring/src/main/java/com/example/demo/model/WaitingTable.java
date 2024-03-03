package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WaitingTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private String serverCode;
    private int lastWaitingNumber;
    private int predictWaitingTime;

    @Override
    public String toString() {
        return "WaitingUserInfo{" +
                "id=" +
                ", serverCode='" + serverCode + '\'' +
                ", myName='" + lastWaitingNumber + '\'' +
                ", phoneNumber='" + predictWaitingTime +
                '}';
    }

    // 기본 생성자
    public WaitingTable() {
    }

    // 모든 필드를 포함한 생성자
    public WaitingTable(String serverCode, int lastWaitingNumber, int predictWaitingTime) {
        this.serverCode = serverCode;
        this.lastWaitingNumber = lastWaitingNumber;
        this.predictWaitingTime = predictWaitingTime;
    }

    // 게터와 세터

    public String getServerCode() {
        return serverCode;
    }

    public void setServerCode(String serverCode) {
        this.serverCode = serverCode;
    }

    public int getLastWaitingNumber() {
        return lastWaitingNumber;
    }

    public void setLastWaitingNumber(int lastWaitingNumber) {
        this.lastWaitingNumber = lastWaitingNumber;
    }

    public int getPredictWaitingTime() {
        return predictWaitingTime;
    }

    public void setPredictWaitingTime(int predictWaitingTime) {
        this.predictWaitingTime = predictWaitingTime;
    }
}
