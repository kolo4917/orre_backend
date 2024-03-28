package com.example.demo.DTO.ToServer;

public class UserCallRequest {
    private Integer storeCode; // 가게 코드
    private Integer waitingTeam; // 현재 대기 팀
    private Integer minutesToAdd; // 추가할 분

    public UserCallRequest(Integer storeCode, Integer waitingTeam, Integer minutesToAdd) {
        this.storeCode = storeCode;
        this.waitingTeam = waitingTeam;
        this.minutesToAdd = minutesToAdd;
    }
    // 생성자, Getter 및 Setter

    public Integer getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(Integer storeCode) {
        this.storeCode = storeCode;
    }

    public Integer getWaitingTeam() {
        return waitingTeam;
    }

    public void setWaitingTeam(Integer waitingTeam) {
        this.waitingTeam = waitingTeam;
    }

    public Integer getMinutesToAdd() {
        return minutesToAdd;
    }

    public void setMinutesToAdd(Integer minutesToAdd) {
        this.minutesToAdd = minutesToAdd;
    }
}
