package com.example.demo.controller;
import java.util.List;

import com.example.demo.DTO.ToClient.StoreDTO;
import com.example.demo.DTO.ToClient.StoreDynamicQueue;
import com.example.demo.DTO.ToServer.StoreInfoRequest;
import com.example.demo.model.WaitingUserInfo;
import com.example.demo.model.WaitingTable;
import com.example.demo.model.LocationData;
import com.example.demo.DTO.ToClient.StoreListDTO;
import com.example.demo.service.WaitingService;
import com.example.demo.service.StoreService;
import com.example.demo.service.StoreDynamicQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;


@Controller
public class WaitingController {

    private final WaitingService waitingService;
    private final StoreService storeService;
    private final StoreDynamicQueueService storeDynamicQueueService;
    private final SimpMessagingTemplate messagingTemplate;



    @Autowired
    public WaitingController(WaitingService waitingService, StoreService storeService, StoreDynamicQueueService storeDynamicQueueService, SimpMessagingTemplate messagingTemplate) {
        this.waitingService = waitingService;
        this.storeService = storeService;
        this.storeDynamicQueueService = storeDynamicQueueService;
        this.messagingTemplate = messagingTemplate;
    }
    // 웹소켓을 통한 웨이팅 정보 등록
    @MessageMapping("/register")
    @SendTo("/topic/waitingInfo")
    public WaitingUserInfo registerWaitingWebSocket(WaitingUserInfo waitingUserInfo) {
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
    @MessageMapping("/user/storeList/nearestStores")
    @SendTo("/topic/user/storeList/nearestStores")
    public List<StoreListDTO> sendNearestStores(LocationData locationData) {
        // 위치 데이터를 받아 가장 가까운 가게 목록을 조회하는 서비스 메소드 호출
        List<StoreListDTO> nearestStores = waitingService.findNearestStores(locationData);
        // 객체로 클라이언트에게 보내줌 // {"storeCode":3,"storeName":"단대골목","address":"경기도 용인시 죽전로 165","distance":45040.0},
        return nearestStores;
    }
    @MessageMapping("/user/storeList/basicStores")
    @SendTo("/topic/user/storeList/basicStores")
    public List<StoreListDTO> sendbasicStores(LocationData locationData) {
        List<StoreListDTO> basicStores = waitingService.findBasicStore(locationData);
        // 객체로 스토어 번호 오름차순으로 클라이언트에게 보내줌 // {"storeCode":3,"storeName":"단대골목","address":"경기도 용인시 죽전로 165","distance":45040.0},
        return basicStores;
    }
    @MessageMapping("/user/storeInfo")
    @SendTo("/topic/user/storeInfo")
    public StoreDTO sendStoreInfo(StoreInfoRequest request) {
        StoreDTO storeDTO = storeService.getStoreDetailsByStoreCode(request.getStoreCode());

        // 조회된 스토어 정보를 객체로 클라이언트에게 보내줌
        return storeDTO;
    }
    @MessageMapping("/user/dynamicQueue")
    @SendTo("/topic/user/dynamicQueue") //기존의 클라이언트 -> 서버 구조
    public List<StoreDynamicQueue> sendDynamicQueue() {
        List<StoreDynamicQueue> dynamicQueues = storeDynamicQueueService.findStoreDynamicQueue(null);
        return dynamicQueues;
    }
    @Scheduled(fixedRate = 50000)
    public void sendDynamicQueue2() {
        System.out.println("Sending dynamic queue...");
        List<StoreDynamicQueue> dynamicQueues = storeDynamicQueueService.findStoreDynamicQueue(null);
        messagingTemplate.convertAndSend("/topic/dynamicQueue", dynamicQueues);
    }

}