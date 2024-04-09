package com.example.demo.controller;

import com.example.demo.DTO.ToClient.StoreDTO;
import com.example.demo.DTO.ToServer.StoreInfoRequest;
import com.example.demo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WaitingGetPostController {

    private final StoreService storeService;

    @Autowired
    public WaitingGetPostController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/api/user/storeInfo")
    public StoreDTO getStoreInfo(@RequestBody StoreInfoRequest request) {
        return storeService.getStoreDetailsByStoreCode(request.getStoreCode());
    }
}
