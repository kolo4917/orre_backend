package com.example.demo.controller;
import java.util.List;
import com.example.demo.model.WaitingUserInfo;
import com.example.demo.model.WaitingTable;
import com.example.demo.model.LocationData;
import com.example.demo.model.StoreInfo;
import com.example.demo.DTO.StoreDistanceDTO;
import com.example.demo.service.WaitingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WaitingController {

    private final WaitingService waitingService;

    @Autowired
    public WaitingController(WaitingService waitingService) {
        this.waitingService = waitingService;
    }

    // 웹소켓을 통한 웨이팅 정보 등록
    @MessageMapping("/register")
    @SendTo("/topic/waitingInfo")
    public WaitingUserInfo registerWaitingWebSocket(WaitingUserInfo waitingUserInfo) {
        // 웹소켓을 통해 받은 데이터 DB에 입력(웨이팅 번호 - 현재 입장한 웨이팅 번호 = 남은 대기 웨이팅)
        // DB에서 가장 뒷 번호 +1  = 웨이팅 번호 SQL 삽입
        // 입장 처리되지 않은 제일 작은 번호 = 이제 불릴 번호 -> MYSQL에서 입장처리 되지 않은 제일 작은 번호 추출하는 알고리즘

        // DB로 부터 해당 유저 정보 출력
        System.out.println("Received waiting info via WebSocket: " + waitingUserInfo.toString()); // toString 호출


        // 웨이팅 번호 보여주는 알고리즘
        return waitingUserInfo;
    }
    @MessageMapping("/waitingInfoRequest")
    @SendTo("/topic/waitingInfo")
    public WaitingUserInfo sendCurrentWaitingInfo() {
        WaitingUserInfo tempWaitingInfo = new WaitingUserInfo();
        tempWaitingInfo.setServerCode("12345");
        tempWaitingInfo.setMyName("임시 사용자");
        tempWaitingInfo.setPhoneNumber("010-1234-5678");
        tempWaitingInfo.setNumberOfUs(4);
        // 생성한 임시 웨이팅 정보 객체를 클라이언트에게 전송
        return tempWaitingInfo;
    }
    @MessageMapping("/waitingTimeInfoRequest")
    @SendTo("/topic/waitingTimeInfo")
    public  WaitingTable sendCurrentWaitingTimeInfo() {
        WaitingTable tempWaitingTable = new WaitingTable();
        tempWaitingTable.setServerCode("77777");
        tempWaitingTable.setLastWaitingNumber(7);
        tempWaitingTable.setPredictWaitingTime(10);
        // 생성한 임시 웨이팅 정보 객체를 클라이언트에게 전송
        return tempWaitingTable;
    }
    @MessageMapping("/nearestStores")
    @SendTo("/topic/nearestStores")
    public List<StoreDistanceDTO> sendNearestStores(LocationData locationData) {
        // 위치 데이터를 받아 가장 가까운 가게 목록을 조회하는 서비스 메소드 호출
        List<StoreDistanceDTO> nearestStores = waitingService.findNearestStores(locationData);

        // 객체로 클라이언트에게 보내줌 // {"id":3,"storeName":"단대골목","address":"경기도 용인시 죽전로 165","distance":45040.0},
        return nearestStores;
    }
}
