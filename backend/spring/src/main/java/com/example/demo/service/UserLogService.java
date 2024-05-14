package com.example.demo.service;

import com.example.demo.model.DataBase.UserLog;
import com.example.demo.repository.UserLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserLogService {


    @Autowired
    private UserLogRepository userLogRepository;

    public void makeWaiting(String phoneNumber, int storeCode) {
        // 현재 시간 가져오기
        Date currentTime = new Date();

        // 새로운 히스토리 번호 계산
        int historyNum = calculateNewHistoryNumber(phoneNumber);

        // 새로운 UserLog 생성
        UserLog userLog = new UserLog(phoneNumber, historyNum, "waiting", currentTime, storeCode, currentTime, 0, "");

        // Repository를 통해 저장
        userLogRepository.save(userLog);
    }

    public void modifyWaiting(String phoneNumber, int storeCode, String status) {

        // 가장 최근의 UserLog 가져오기
        UserLog latestUserLog = userLogRepository.findFirstByUserPhoneNumberAndStoreCodeOrderByHistoryNumDesc(phoneNumber, storeCode);

        Date currentTime = new Date();
        latestUserLog.setStatusChangeTime(currentTime);
        // 상태 및 매장 코드 수정
        latestUserLog.setStatus(status);

        // Repository를 통해 업데이트
        userLogRepository.save(latestUserLog);
    }
    public void modifyWaitingByClosing(String phoneNumber, int storeCode, String status) {

        // 가장 최근의 UserLog 가져오기
        UserLog latestUserLog = userLogRepository.findByUserPhoneNumberAndStatusAndStoreCode(phoneNumber, "waiting", storeCode);

        Date currentTime = new Date();
        latestUserLog.setStatusChangeTime(currentTime);
        // 상태 및 매장 코드 수정
        latestUserLog.setStatus(status);

        // Repository를 통해 업데이트
        userLogRepository.save(latestUserLog);
    }

    private int calculateNewHistoryNumber(String phoneNumber) {
        // DB에서 해당 번호에 대한 최신 히스토리 번호 가져오기
        Integer latestHistoryNum = userLogRepository.findLatestHistoryNumByUserPhoneNumber(phoneNumber);

        // 최신 히스토리 번호가 null이면 1 반환, 아니면 최신 히스토리 번호 + 1 반환
        return (latestHistoryNum != null) ? latestHistoryNum + 1 : 1;
    }
}
