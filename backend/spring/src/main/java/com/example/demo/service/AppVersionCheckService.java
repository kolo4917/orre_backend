package com.example.demo.service;

import com.example.demo.model.DataBase.AppVersion;
import com.example.demo.DTO.ToClient.AppVersionCheckRespose;
import com.example.demo.repository.AppVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AppVersionCheckService {
    @Autowired
    private AppVersionRepository appVersionRepository;
    public AppVersionCheckRespose checkVersion(Integer appCode, String appVersion){
        AppVersion Version = appVersionRepository.findByAppCode(appCode);
        AppVersionCheckRespose response = new AppVersionCheckRespose();

        if(Objects.equals(Version.getAppVersion(), appVersion)){
            response.setStatus("200");
            response.setAppVersion(Version.getAppVersion());
            response.setAppCode(Version.getAppCode());
            response.setAppEssentialUpdate(0);
        }
        else{
            response.setStatus("1301");
            response.setAppVersion(Version.getAppVersion());
            response.setAppCode(Version.getAppCode());
            response.setAppEssentialUpdate(Version.getAppEssentialUpdate());
        }
        return response;
    }
}
