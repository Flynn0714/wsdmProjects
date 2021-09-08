package com.wsdm.common.enums;

/**
 * 操作状态
 *
 * @author wsdm
 */
public enum ProduceStatus {
    PRODUCE_SEMI("1", "半成品"),

    PRODUCE_FITTINGS("2", "配件");

    // 编码
    private String code;
    // 信息
    private String msg;

    ProduceStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
