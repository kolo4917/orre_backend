package com.example.demo.DTO.ToClient;

import java.time.LocalDateTime;

public class UserCallResponse {
    private Integer storeCode; // 가게 코드
    private Integer waitingTeam; // 현재 대기 팀 번호
    private LocalDateTime entryTime; // 예상 입장 마감 시간

    public UserCallResponse(Integer storeCode, Integer waitingTeam, LocalDateTime entryTime) {
        this.storeCode = storeCode;
        this.waitingTeam = waitingTeam;
        this.entryTime = entryTime;
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

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }
}
