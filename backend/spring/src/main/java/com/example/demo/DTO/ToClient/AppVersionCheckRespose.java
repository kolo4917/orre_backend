package com.example.demo.DTO.ToClient;

public class AppVersionCheckRespose {
    private String status;
    private Integer appCode;
    private String appVersion;
    private String appEssentialUpdateVersion;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAppCode() {
        return appCode;
    }

    public void setAppCode(Integer appCode) {
        this.appCode = appCode;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppEssentialUpdateVersion() {
        return appEssentialUpdateVersion;
    }

    public void setAppEssentialUpdateVersion(String appEssentialUpdateVersion) {
        this.appEssentialUpdateVersion = appEssentialUpdateVersion;
    }
}
