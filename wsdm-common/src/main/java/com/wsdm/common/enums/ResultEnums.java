package com.wsdm.common.enums;

/**
 * @author wsdm
 * @date 2020-11-7 20:15
 */
public enum ResultEnums {

    UNKNOWN_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    SYSTEM_BUSY_ERROR(100001, "系统正忙，请稍后再试"),

    NULL_PARAM(200102, "参数为空"),
    PARAM_FORMAT_ERROR(200103, "参数格式不符"),
    JSON_FORMAT_ERROR(200104, "JSON解析失败,参数格式不符"),
    NO_FORMATTED_DATA(200105, "无符合条件的数据"),

    FITTINGS_EXIST(300101, "该配件已存在"),
    SEMI_EXIST(300102, "该半成品已存在"),
    PRODUCE_EXIST(300103, "该成品已存在"),

    STATUS_ERROR(400101, "该状态不可操作");


    // 编码
    private Integer code;
    // 信息
    private String msg;

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
