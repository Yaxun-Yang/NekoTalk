package org.csu.nekotalk.domain;

import java.io.Serializable;

public class QQAccessToken implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5258435811207021018L;
    private String accessToken;//接口调用凭证
    private int expiresIn;//access_token接口调用凭证超时时间，单位（秒）
    private String openid;//授权用户唯一标识
    private String refreshToken;//用户刷新access_token
    private String scope;//用户授权的作用域，使用逗号（,）分隔

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

}