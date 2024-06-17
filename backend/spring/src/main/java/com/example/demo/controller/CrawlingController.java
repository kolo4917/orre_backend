package com.example.demo.controller;

import com.example.demo.DTO.ToServer.DankookMenuRequest;
import com.example.demo.DTO.ToClient.StatusResponse;
import com.example.demo.DTO.ToClient.DankookMenuResponse;
import com.example.demo.service.DankookMenuService;

import com.example.demo.service.AppVersionService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
public class CrawlingController {

    private final DankookMenuService dankookMenuService;
    public CrawlingController(DankookMenuService dankookMenuService) {
        this.dankookMenuService = dankookMenuService;
    }
    @Scheduled(cron = "0 0 9 * * MON") // 매주 월요일 00시에 실행
    public void performRegularTask() {
        LocalDate today = LocalDate.now();

        try {
            List<String> menuItems1 = dankookMenuService.fetchMenu(today, "혜당관");
            List<String> menuItems2 = dankookMenuService.fetchMenu(today, "교직원");

            System.out.println("Executing regular task...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PostMapping("/api/dankook/menu")
    public DankookMenuResponse dankookMenuCheck(@RequestBody DankookMenuRequest request){
        return dankookMenuService.dankookMenuCheck(request.getDate(), request.getRestaurantLocation());
    }




}

