package com.example.demo.controller;

import com.example.demo.DTO.ToClient.BooleanResponse;
import com.example.demo.DTO.ToClient.StatusResponse;
import com.example.demo.DTO.ToClient.StoreDTO;
import com.example.demo.DTO.ToServer.StoreInfoRequest;
import com.example.demo.DTO.ToServer.StoreMenuOrderRequest;
import com.example.demo.service.StoreService;
import com.example.demo.service.WaitingService;
import com.example.demo.service.JwtService;
import com.example.demo.service.StoreMenuOrderService;
import com.example.demo.DTO.ToClient.StoreListDTO;
import com.example.demo.model.LocationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class WaitingGetPostController {

    private final StoreService storeService;
    private final WaitingService waitingService;
    private final JwtService jwtService;
    private final StoreMenuOrderService storeMenuOrderService;

    @Autowired
    public WaitingGetPostController(StoreService storeService, WaitingService waitingService,
                                    JwtService jwtService, StoreMenuOrderService storeMenuOrderService) {
        this.storeService = storeService;
        this.waitingService = waitingService;
        this.jwtService = jwtService;
        this.storeMenuOrderService = storeMenuOrderService;
    }

    @PostMapping("/api/user/storeInfo")
    public StoreDTO getStoreInfo(@RequestBody StoreInfoRequest request) {
        return storeService.getStoreDetailsByStoreCode(request.getStoreCode());
    }

    @GetMapping("/api/user/storeList/basicStores")
    public List<StoreListDTO> getBasicStores(@RequestParam(name = "latitude") double latitude, @RequestParam(name = "longitude") double longitude){
        LocationData locationData = new LocationData(latitude, longitude);
        List<StoreListDTO> basicStores = waitingService.findBasicStore(locationData);
        return basicStores;
    }

    @GetMapping("/api/user/storeList/nearestStores")
    public List<StoreListDTO> getNearestStores(@RequestParam(name = "latitude") double latitude, @RequestParam(name = "longitude") double longitude){
        LocationData locationData = new LocationData(latitude, longitude);
        List<StoreListDTO> nearestStores = waitingService.findNearestStores(locationData);
        return nearestStores;
    }

    @PostMapping("/api/user/menu/order/amount")
    public StatusResponse handleMenuAdd(@RequestBody StoreMenuOrderRequest request){
        String jwtUser = request.getJwt();
        boolean isValidAdmin = jwtService.isValid(jwtUser); //테스트를 위해 발급이 쉬운 jwt admin으로 주문하게 했음 : 최종 배포전 수정
        if (isValidAdmin) {
            try {
                storeMenuOrderService.ModifyMenuAmountForUser(request);
                return new StatusResponse("200");
            } catch (Exception e) {
                return new StatusResponse("902");
            }
        } else {
            return new StatusResponse("901");
        }
    }
}
