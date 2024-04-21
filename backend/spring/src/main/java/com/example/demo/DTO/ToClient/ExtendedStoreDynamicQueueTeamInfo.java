package com.example.demo.DTO.ToClient;

public class ExtendedStoreDynamicQueueTeamInfo {
    private Integer waitingTeam;
    private Integer status;
    private String phoneNumber;
    private Integer personNumber;

    public ExtendedStoreDynamicQueueTeamInfo(Integer waitingTeam, Integer status, String phoneNumber, Integer personNumber) {
        this.waitingTeam = waitingTeam;
        this.status = status;
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

    public Integer getstatus() {
        return status;
    }

    public void setstatus(Integer status) {
        this.status = status;
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
