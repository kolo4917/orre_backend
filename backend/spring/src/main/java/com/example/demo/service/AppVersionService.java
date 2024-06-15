package com.example.demo.service;

import com.example.demo.model.DataBase.AppVersion;
import com.example.demo.DTO.ToClient.AppVersionCheckRespose;
import com.example.demo.DTO.ToClient.StatusResponse;
import com.example.demo.repository.AppVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AppVersionService {
    @Autowired
    private AppVersionRepository appVersionRepository;
    public AppVersionCheckRespose checkVersion(Integer appCode, String appVersion){
        AppVersion Version = appVersionRepository.findByAppCode(appCode);
        AppVersionCheckRespose response = new AppVersionCheckRespose();

        if(Objects.equals(Version.getAppVersion(), appVersion)){
            response.setStatus("200");
            response.setAppVersion(Version.getAppVersion());
            response.setAppCode(Version.getAppCode());
            response.setAppEssentialUpdateVersion(Version.getAppEssentialUpdateVersion());
        }
        else{
            response.setStatus("1301");
            response.setAppVersion(Version.getAppVersion());
            response.setAppCode(Version.getAppCode());
            response.setAppEssentialUpdateVersion(Version.getAppEssentialUpdateVersion());
        }
        return response;
    }

    public StatusResponse modifyVersion(Integer appCode, String newAppVersion, Integer essential) {
        AppVersion version = appVersionRepository.findByAppCode(appCode);

        version.setAppVersion(newAppVersion);

        if (essential == 1) {
            version.setAppEssentialUpdateVersion(newAppVersion);
        }

        appVersionRepository.save(version);

        return new StatusResponse("200");
    }
}
