package com.example.demo.controller;

import com.example.demo.DTO.ToClient.StoreDTO;
import com.example.demo.DTO.ToServer.StoreInfoRequest;
import com.example.demo.service.StoreService;
import com.example.demo.service.WaitingService;
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

    @Autowired
    public WaitingGetPostController(StoreService storeService, WaitingService waitingService) {
        this.storeService = storeService;
        this.waitingService = waitingService;
    }

    @PostMapping("/api/user/storeInfo")
    public StoreDTO getStoreInfo(@RequestBody StoreInfoRequest request) {
        return storeService.getStoreDetailsByStoreCode(request.getStoreCode());
    }

    /*@GetMapping("/api/user/storeList/basicStores")
    public List<StoreListDTO> getBasicStores(@RequestBody LocationData locationData) {
        List<StoreListDTO> basicStores = waitingService.findBasicStore(locationData);
        return basicStores;
    }*/

    @GetMapping("/api/user/storeList/basicStores")
    public List<StoreListDTO> getBasicStores(@RequestParam(name = "latitude") double latitude, @RequestParam(name = "longitude") double longitude){
        LocationData locationData = new LocationData(latitude, longitude);
        List<StoreListDTO> basicStores = waitingService.findBasicStore(locationData);
        return basicStores;
    }

    /*@GetMapping("/api/user/storeList/nearestStores")
    public List<StoreListDTO> getNearestStores(@RequestBody LocationData locationData) {
        List<StoreListDTO> nearestStores = waitingService.findNearestStores(locationData);
        return nearestStores;
    }*/

    @GetMapping("/api/user/storeList/nearestStores")
    public List<StoreListDTO> getNearestStores(@RequestParam(name = "latitude") double latitude, @RequestParam(name = "longitude") double longitude){
        LocationData locationData = new LocationData(latitude, longitude);
        List<StoreListDTO> nearestStores = waitingService.findNearestStores(locationData);
        return nearestStores;
    }
}
