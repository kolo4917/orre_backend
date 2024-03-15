package com.example.demo.DTO.ToClient;

public class StoreDynamicQueue {
    private int storeCode;
    private int numberOfTeamsWaiting;
    private int estimatedWaitingTime;

    // 기본 생성자
    public StoreDynamicQueue() {
    }

    // 모든 필드를 포함한 생성자
    public StoreDynamicQueue(int storeCode, int numberOfTeamsWaiting, int estimatedWaitingTime) {
        this.storeCode = storeCode;
        this.numberOfTeamsWaiting = numberOfTeamsWaiting;
        this.estimatedWaitingTime = estimatedWaitingTime;
    }

    // Getters
    public int getStoreCode() {
        return storeCode;
    }

    public int getNumberOfTeamsWaiting() {
        return numberOfTeamsWaiting;
    }

    public int getEstimatedWaitingTime() {
        return estimatedWaitingTime;
    }

    // Setters
    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public void setNumberOfTeamsWaiting(int numberOfTeamsWaiting) {
        this.numberOfTeamsWaiting = numberOfTeamsWaiting;
    }

    public void setEstimatedWaitingTime(int estimatedWaitingTime) {
        this.estimatedWaitingTime = estimatedWaitingTime;
    }
}
