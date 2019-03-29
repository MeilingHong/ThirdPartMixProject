package com.meiling.weibo.common.bean;

/**
 * Created by Hz on 2018-12-11.
 */

public class WeiboInfoBean {
    private String uid;
    private String access_token;
    private String expires_in;
    private String refresh_token;

    private String errorCode;
    private String errorMsg;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "WeiboInfoBean{" +
                "uid='" + uid + '\'' +
                ", access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
