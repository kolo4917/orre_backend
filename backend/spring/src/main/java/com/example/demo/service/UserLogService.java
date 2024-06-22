package com.example.demo.service;

import com.example.demo.model.DataBase.UserLog;
import com.example.demo.repository.UserLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;


import java.util.Date;

@Service
public class UserLogService {


    @Autowired
    private UserLogRepository userLogRepository;
    @Autowired SimpMessagingTemplate simpMessagingTemplate;

    public void makeWaiting(String phoneNumber, int storeCode, int waitingNumber, int personNumber) {
        // 현재 시간 가져오기
        Date currentTime = new Date();

        // 새로운 히스토리 번호 계산
        int historyNum = calculateNewHistoryNumber(phoneNumber);

        // 새로운 UserLog 생성
        UserLog userLog = new UserLog(phoneNumber, historyNum, "waiting", currentTime, storeCode, currentTime, 0, "",waitingNumber, personNumber);

        // Repository를 통해 저장
        userLogRepository.save(userLog);
        //웹 소켓으로 정보 전송
        simpMessagingTemplate.convertAndSend("/topic/admin/log/" + storeCode, userLog);
        simpMessagingTemplate.convertAndSend("/topic/user/log/" + storeCode + "/"+ phoneNumber,userLog);
    }

    public void modifyWaiting(String phoneNumber, int storeCode, String status) {

        // 가장 최근의 UserLog 가져오기
        UserLog latestUserLog = userLogRepository.findFirstByUserPhoneNumberAndStoreCodeOrderByHistoryNumDesc(phoneNumber, storeCode);

        if(latestUserLog != null){
            Date currentTime = new Date();
            latestUserLog.setStatusChangeTime(currentTime);
            // 상태 및 매장 코드 수정
            latestUserLog.setStatus(status);

            // Repository를 통해 업데이트
            userLogRepository.save(latestUserLog);
            // 웹소켓 전송
            simpMessagingTemplate.convertAndSend("/topic/admin/log/" + storeCode, latestUserLog);
            simpMessagingTemplate.convertAndSend("/topic/user/log/" + storeCode + "/"+ phoneNumber, latestUserLog);
        }

    }
    public void modifyWaitingByClosing(String phoneNumber, int storeCode, String status) {

        // 가장 최근의 UserLog 가져오기
        UserLog latestUserLog = userLogRepository.findByUserPhoneNumberAndStatusAndStoreCode(phoneNumber, "waiting", storeCode);

        if (latestUserLog == null) {
            // waiting 상태가 없는 경우 called로 시작하는 가장 최근의 UserLog 가져오기
            latestUserLog = userLogRepository.findByUserPhoneNumberAndStoreCodeAndStatusStartingWith(phoneNumber, storeCode, "called");
//            if (!calledLogs.isEmpty()) {
//                latestUserLog = calledLogs.get(0); // 가장 최근의 것 선택
//            } 리스트 형태일 경우 다중 예약 서비스 개시 할 경우
        }

        if(latestUserLog != null){
            Date currentTime = new Date();
            latestUserLog.setStatusChangeTime(currentTime);
            // 상태 및 매장 코드 수정
            latestUserLog.setStatus(status);

            // Repository를 통해 업데이트
            userLogRepository.save(latestUserLog);

            // 웹소켓 전송
            simpMessagingTemplate.convertAndSend("/topic/admin/log/" + storeCode, latestUserLog);
            simpMessagingTemplate.convertAndSend("/topic/user/log/" + storeCode + "/"+ phoneNumber, latestUserLog);
        }

    }

    private int calculateNewHistoryNumber(String phoneNumber) {
        // DB에서 해당 번호에 대한 최신 히스토리 번호 가져오기
        Integer latestHistoryNum = userLogRepository.findLatestHistoryNumByUserPhoneNumber(phoneNumber);

        // 최신 히스토리 번호가 null이면 1 반환, 아니면 최신 히스토리 번호 + 1 반환
        return (latestHistoryNum != null) ? latestHistoryNum + 1 : 1;
    }
}
