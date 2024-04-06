package com.example.demo.DTO.ToClient;

public class ExtendedStoreDynamicQueueTeamInfo {
    private Integer waitingTeam;
    private Integer enteringTeam;
    private String phoneNumber;
    private Integer personNumber;

    public ExtendedStoreDynamicQueueTeamInfo(Integer waitingTeam, Integer enteringTeam, String phoneNumber, Integer personNumber) {
        this.waitingTeam = waitingTeam;
        this.enteringTeam = enteringTeam;
        this.phoneNumber = phoneNumber;
        this.personNumber = personNumber;
    }

    // Getters and Setters
    public Integer getWaitingTeam() {
        return waitingTeam;
    }

    public void setWaitingTeam(Integer waitingTeam) {
        this.waitingTeam = waitingTeam;
    }

    public Integer getEnteringTeam() {
        return enteringTeam;
    }

    public void setEnteringTeam(Integer enteringTeam) {
        this.enteringTeam = enteringTeam;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(Integer personNumber) {
        this.personNumber = personNumber;
    }
}
