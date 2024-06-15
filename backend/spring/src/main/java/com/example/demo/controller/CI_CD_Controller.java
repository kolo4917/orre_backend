package com.example.demo.controller;


import com.example.demo.DTO.ToClient.AppVersionCheckRespose;
import com.example.demo.DTO.ToClient.StatusResponse;
import com.example.demo.DTO.ToServer.AppVersionCheckRequest;
import com.example.demo.DTO.ToServer.AppVersionModifyRequest;
import com.example.demo.service.AppVersionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CI_CD_Controller {

    private final AppVersionService appVersionCheckService;
    public CI_CD_Controller(AppVersionService appVersionCheckService) {
        this.appVersionCheckService = appVersionCheckService;
    }

    @PostMapping("/api/appVersion")
    public AppVersionCheckRespose appVersionCheckRespose(@RequestBody AppVersionCheckRequest request){
        return appVersionCheckService.checkVersion(request.getAppCode(), request.getAppVersion());
    }
    @PostMapping("/api/appVersion/modify")
    public StatusResponse appVersionModifyResponse(@RequestBody AppVersionModifyRequest request){
        return appVersionCheckService.modifyVersion(request.getAppCode(),request.getNewAppVersion(), request.getEssential());
    }




}

