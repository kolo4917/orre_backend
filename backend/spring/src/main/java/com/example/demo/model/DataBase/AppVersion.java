package com.example.demo.model.DataBase;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
    @Entity
    @Table(name = "app_version")
    public class AppVersion {
        @Id
        @Column(name = "app_code")
        private Integer appCode;
        @Column(name = "app_name")
        private String appName;
        @Column(name = "app_version")
        private String appVersion;
        @Column(name = "app_essential_update")
        private Integer appEssentialUpdate;
        public AppVersion() {}

        public AppVersion(Integer appCode, String appName, String appVersion, Integer appEssentialUpdate) {
            this.appCode = appCode;
            this.appName = appName;
            this.appVersion = appVersion;
            this.appEssentialUpdate = appEssentialUpdate;
        }

        public Integer getAppCode() {
            return appCode;
        }

        public void setAppCode(Integer appCode) {
            this.appCode = appCode;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }

        public Integer getAppEssentialUpdate() {
            return appEssentialUpdate;
        }

        public void setAppEssentialUpdate(Integer appEssentialUpdate) {
            this.appEssentialUpdate = appEssentialUpdate;
        }
    }

