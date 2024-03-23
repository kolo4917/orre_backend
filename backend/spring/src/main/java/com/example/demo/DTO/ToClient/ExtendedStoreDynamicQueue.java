package com.example.demo.DTO.ToClient;

import java.util.List;

public class ExtendedStoreDynamicQueue {
    private int storeCode;
    private List<Integer> waitingTeamList;
    private List<Integer> enteringTeamList;
    private List<String> phoneNumberList;
    private List<Integer> personNumberList;
    private int estimatedWaitingTimePerTeam;

    // 기본 생성자
    public ExtendedStoreDynamicQueue() {
    }

    // 모든 필드를 포함한 생성자
    public ExtendedStoreDynamicQueue(int storeCode, List<Integer> waitingTeamList, List<Integer> enteringTeamList, List<String> phoneNumberList, List<Integer> personNumberList, int estimatedWaitingTimePerTeam) {
        this.storeCode = storeCode;
        this.waitingTeamList = waitingTeamList;
        this.enteringTeamList = enteringTeamList;
        this.phoneNumberList = phoneNumberList;
        this.personNumberList = personNumberList;
        this.estimatedWaitingTimePerTeam = estimatedWaitingTimePerTeam;
    }

    // Getters and Setters
    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public List<Integer> getWaitingTeamList() {
        return waitingTeamList;
    }

    public void setWaitingTeamList(List<Integer> waitingTeamList) {
        this.waitingTeamList = waitingTeamList;
    }

    public List<Integer> getEnteringTeamList() {
        return enteringTeamList;
    }

    public void setEnteringTeamList(List<Integer> enteringTeamList) {
        this.enteringTeamList = enteringTeamList;
    }

    public List<String> getPhoneNumberList() {
        return phoneNumberList;
    }

    public void setPhoneNumberList(List<String> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }

    public List<Integer> getPersonNumberList() {
        return personNumberList;
    }

    public void setPersonNumberList(List<Integer> personNumberList) {
        this.personNumberList = personNumberList;
    }

    public int getEstimatedWaitingTimePerTeam() {
        return estimatedWaitingTimePerTeam;
    }

    public void setEstimatedWaitingTimePerTeam(int estimatedWaitingTimePerTeam) {
        this.estimatedWaitingTimePerTeam = estimatedWaitingTimePerTeam;
    }
}
