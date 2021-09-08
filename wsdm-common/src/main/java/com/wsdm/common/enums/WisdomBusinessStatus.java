package com.wsdm.common.enums;

/**
 * 操作状态
 *
 * @author wsdm
 */
public enum WisdomBusinessStatus {
    /**
     * 成功
     */
    STATUS_NORMAL("0", "正常"),

    /**
     * 失败
     */
    STATUS_DELETED("1", "已删除");

    // 编码
    private String code;
    // 信息
    private String msg;

    WisdomBusinessStatus(String code, String msg) {
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
