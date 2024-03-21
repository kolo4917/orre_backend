package com.example.demo.DTO.ToClient;

import java.util.List;

public class StoreDynamicQueue {
    private int storeCode;
    private List<Integer> waitingTeamList;
    private List<Integer> enteringTeamList;
    private int estimatedWaitingTimePerTeam;

    // 기본 생성자
    public StoreDynamicQueue() {
    }

    // 모든 필드를 포함한 생성자
    public StoreDynamicQueue(int storeCode, List<Integer> waitingTeamList, List<Integer> enteringTeamList, int estimatedWaitingTimePerTeam) {
        this.storeCode = storeCode;
        this.waitingTeamList = waitingTeamList;
        this.enteringTeamList = enteringTeamList;
        this.estimatedWaitingTimePerTeam = estimatedWaitingTimePerTeam;
    }

    // Getters
    public int getStoreCode() {
        return storeCode;
    }

    public List<Integer> getWaitingTeamList() {
        return waitingTeamList;
    }

    public List<Integer> getEnteringTeamList() {
        return enteringTeamList;
    }

    public int getEstimatedWaitingTimePerTeam() {
        return estimatedWaitingTimePerTeam;
    }

    // Setters
    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public void setWaitingTeamList(List<Integer> waitingTeamList) {
        this.waitingTeamList = waitingTeamList;
    }

    public void setEnteringTeamList(List<Integer> enteringTeamList) {
        this.enteringTeamList = enteringTeamList;
    }

    public void setEstimatedWaitingTimePerTeam(int estimatedWaitingTimePerTeam) {
        this.estimatedWaitingTimePerTeam = estimatedWaitingTimePerTeam;
    }
}
