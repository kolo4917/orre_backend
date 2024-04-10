package com.example.demo.controller;

import com.example.demo.DTO.ToClient.EmptySeat;
import com.example.demo.DTO.ToClient.StoreDTO;
import com.example.demo.DTO.ToServer.StoreInfoRequest;
import com.example.demo.service.EmptySeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@RestController
public class StoreAdminGetPostController {

    @Autowired
    private final EmptySeatService emptySeatService;

    // 생성자를 통한 EmptySeatService 의존성 주입
    public StoreAdminGetPostController(EmptySeatService emptySeatService) {
        this.emptySeatService = emptySeatService;
    }
    @PostMapping("/api/admin/StoreAdmin/available")
    public List<EmptySeat> emptySeat(@RequestBody StoreInfoRequest request) {
        // EmptySeatService를 사용하여 storeCode에 해당하는 비어 있는 자리 정보 조회
        return emptySeatService.findEmptySeats(request.getStoreCode());
    }
}
