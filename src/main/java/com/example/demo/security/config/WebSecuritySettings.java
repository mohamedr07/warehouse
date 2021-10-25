package com.example.demo.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebSecuritySettings {

    @Value("${app.secretKey}")
    private String secretKey;

    @Value("${app.accessTokenExp}")
    private int accessTokenExp;

    @Value("${app.refreshTokenExp}")
    private int refreshTokenExp;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public int getAccessTokenExp() {
        return accessTokenExp;
    }

    public void setAccessTokenExp(int accessTokenExp) {
        this.accessTokenExp = accessTokenExp;
    }

    public int getRefreshTokenExp() {
        return refreshTokenExp;
    }

    public void setRefreshTokenExp(int refreshTokenExp) {
        this.refreshTokenExp = refreshTokenExp;
    }

}
