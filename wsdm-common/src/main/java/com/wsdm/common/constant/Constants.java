package com.wsdm.common.constant;

/**
 * 通用常量信息
 *
 * @author wsdm
 */
public class Constants {
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * 用户ID
     */
    public static final String JWT_USERID = "userid";

    /**
     * 用户名称
     */
    public static final String JWT_USERNAME = "sub";

    /**
     * 用户头像
     */
    public static final String JWT_AVATAR = "avatar";

    /**
     * 创建时间
     */
    public static final String JWT_CREATED = "created";

    /**
     * 用户权限
     */
    public static final String JWT_AUTHORITIES = "authorities";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /** 供应商编码前缀 */
    public static final String GYS = "V";

    /** 客户编码前缀 */
    public static final String  CUSTOMER_M = "M";

    public static final String MP = "[毛坯]";

    /** 销售订单前缀 */
    public static final String  SALSE_XS = "XS";

    /** 发货单前缀 */
    public static final String  SALES_DELIVERY_FH = "FH";

    /** 退货单前缀*/
    public static final String SALES_EXCHANGE_HH = "HH";

    /** 收款单前缀 */
    public static final String  SALES_GATHERING_SK = "SK";

    /** 开票单前缀 */
    public static final String  SALES_BILLING_KP = "KP";

    /** 领料单号前缀 */
    public static final String  MATERIALRECEIVE_CODE = "L";

    public static final String PD_ASSOCIATION_CODE = "PD";


    /**
     * 库存物料单号前缀
     */
    public static final String STOCK_PREFIX = "W";

    /**
     * 采购单号前缀
     */
    public static final String PURCHASE_NUMBER_PREFIX = "CG";

    /**
     * 收货单号前缀
     */
    public static final String RECEIVE_NUMBER_PREFIX = "SH";

    /**
     * 付款单号前缀
     */
    public static final String PAY_NUMBER_PREFIX = "FK";

    /**
     * 资产编号前缀
     */
    public static final String ASSET_NUMBER_PREFIX = "ZC";

    /**
     * 收货质检单号前缀
     */
    public static final String RECEIVE_CHECK_NUMBER_PREFIX = "SHZJ";

    public static final String STRING_0 = "0";
    public static final String STRING_1 = "1";
    public static final String STRING_2 = "2";
    public static final String STRING_3 = "3";


    // 作废
    public static final String CANCEL = "cancel";
    // 通过
    public static final String ADOPT = "adopt";

    public static final String SALES_MANAGE = "sales_manage";

    public static final String SALES_DELIVERY = "sales_delivery";

    public static final String SALES_GATHERING = "sales_gathering";

    public static final String SALES_BILLING = "sales_billing";

    //根据code获取用户openid;
    public static final String WX_API_GET_OPENID_BYCODE = "https://api.weixin.qq.com/sns/jscode2session";
    /**
     * 获取微信access_token
     */
    public static final String WX_API_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public static final String ACCESS_TOKEN_KEY = "access_token";
    public static final String ACCESS_TOKEN_TIMEOUT_KEY = "timeStamp";

    public static final String REDIS_KEY_PREFIX_OPENID = "OPENID";
}
