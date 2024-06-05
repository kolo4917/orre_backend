package com.example.demo.DTO.ToServer;

public class AppVersionCheckRequest {

    private Integer appCode;
    private String appVersion;

    public AppVersionCheckRequest(Integer appCode, String appVersion) {
        this.appCode = appCode;
        this.appVersion = appVersion;
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
}
