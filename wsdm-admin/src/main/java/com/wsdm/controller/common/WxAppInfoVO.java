package com.wsdm.controller.common;

import java.io.Serializable;

/**
 * @author 王瑞
 * @description 微信服务号基本参数VO
 * @create 2019-8-16 15:35:43
 **/
public class WxAppInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appId;

    private String appSecret;

    private String code;

    private String openId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}