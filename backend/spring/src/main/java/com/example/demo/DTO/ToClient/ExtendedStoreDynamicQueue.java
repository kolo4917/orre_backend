package com.example.demo.DTO.ToClient;

import java.util.List;

public class ExtendedStoreDynamicQueue {
    private int storeCode;
    private List<ExtendedStoreDynamicQueueTeamInfo> teamInfoList; // ExtendedStoreDynamicQueueTeamInfo 객체의 리스트
    private int estimatedWaitingTimePerTeam;

    // 기본 생성자
    public ExtendedStoreDynamicQueue() {
    }

    // 모든 필드를 포함한 생성자
    public ExtendedStoreDynamicQueue(int storeCode, List<ExtendedStoreDynamicQueueTeamInfo> teamInfoList, int estimatedWaitingTimePerTeam) {
        this.storeCode = storeCode;
        this.teamInfoList = teamInfoList;
        this.estimatedWaitingTimePerTeam = estimatedWaitingTimePerTeam;
    }

    // Getters and Setters
    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public List<ExtendedStoreDynamicQueueTeamInfo> getTeamInfoList() {
        return teamInfoList;
    }

    public void setTeamInfoList(List<ExtendedStoreDynamicQueueTeamInfo> teamInfoList) {
        this.teamInfoList = teamInfoList;
    }

    public int getEstimatedWaitingTimePerTeam() {
        return estimatedWaitingTimePerTeam;
    }

    public void setEstimatedWaitingTimePerTeam(int estimatedWaitingTimePerTeam) {
        this.estimatedWaitingTimePerTeam = estimatedWaitingTimePerTeam;
    }
}
