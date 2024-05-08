package com.example.demo.controller;
import java.util.List;

import com.example.demo.DTO.ToClient.StoreDTO;
import com.example.demo.DTO.ToClient.StoreDynamicQueue;
import com.example.demo.DTO.ToServer.StoreInfoRequest;
import com.example.demo.DTO.ToServer.UserStoreWaitRequest;
import com.example.demo.DTO.ToClient.UserStoreWaitResponse;
import com.example.demo.model.WaitingUserInfo;
import com.example.demo.model.DataBase.UserStoreWait;
import com.example.demo.model.WaitingTable;
import com.example.demo.model.LocationData;
import com.example.demo.DTO.ToClient.StoreListDTO;
import com.example.demo.service.WaitingService;
import com.example.demo.service.StoreService;
import com.example.demo.service.StoreDynamicQueueService;
import com.example.demo.service.UserStoreMakeWaitingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
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
    private UserStoreMakeWaitingService userStoreMakeWaitingService;



    @Autowired
    public WaitingController(WaitingService waitingService, StoreService storeService, StoreDynamicQueueService storeDynamicQueueService, SimpMessagingTemplate messagingTemplate) {
        this.waitingService = waitingService;
        this.storeService = storeService;
        this.storeDynamicQueueService = storeDynamicQueueService;
        this.messagingTemplate = messagingTemplate;
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

    @MessageMapping("/user/dynamicStoreWaitingInfo/{storeCode}")
    @SendTo("/topic/user/dynamicStoreWaitingInfo/{storeCode}")
    public StoreDynamicQueue sendDynamicQueue(@DestinationVariable Integer storeCode) {
        StoreDynamicQueue dynamicQueue = storeDynamicQueueService.findStoreDynamicQueue(storeCode);
        return dynamicQueue;
    }


    @MessageMapping("/user/waiting/make/{storeCode}/{userPhoneNumber}")
    @SendTo("/topic/user/waiting/make/{storeCode}/{userPhoneNumber}")
    public UserStoreWaitResponse makeWaiting(
            @DestinationVariable Integer storeCode,
            @DestinationVariable String userPhoneNumber,
            UserStoreWaitRequest request) {
        request.setStoreCode(storeCode);
        request.setUserPhoneNumber(userPhoneNumber);

        // 대기열 생성 서비스 호출
        UserStoreWait newUserStoreWait = userStoreMakeWaitingService.createUserStoreWait(request);

        // 생성 결과를 클라이언트에 전송할 응답 객체 생성
        UserStoreWaitResponse response = new UserStoreWaitResponse();
        if (newUserStoreWait != null) {
            response.setStatus("200");
            response.setToken(newUserStoreWait); // 응답에 대기열 상세 정보 포함
        } else {
            response.setStatus("1101");
        }
        return response;
    }
    @MessageMapping("/user/waiting/cancel/{storeCode}/{userPhoneNumber}")
    @SendTo("/topic/user/waiting/cancel/{storeCode}/{userPhoneNumber}")
    public UserStoreWaitResponse cancelWaiting(@DestinationVariable Integer storeCode, @DestinationVariable String userPhoneNumber, UserStoreWaitRequest request) {
        request.setStoreCode(storeCode);
        request.setUserPhoneNumber(userPhoneNumber);
        request.setPersonNumber(0);
        // 대기열 비활성화 서비스 호출
        boolean isCancelled = userStoreMakeWaitingService.deactivateUserStoreWaitByPhoneNumber(request);

        // 응답 객체 생성
        UserStoreWaitResponse response = new UserStoreWaitResponse();
        if (isCancelled) {
            response.setStatus("200");
            response.setToken(null);
        } else {
            response.setStatus("1102");
            response.setToken(null);
        }
        return response;
    }

    //@MessageMapping("/user/userCall/{storeCode}/{waitingNumber}") -admin에서 요청을 보낼 때 자동으로 작동 : 구독시 별도의 요청 필요 없음

}