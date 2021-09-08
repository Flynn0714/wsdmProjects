package com.wsdm.common.enums;

/**
 * 质检单单状态
 *
 * @author wanglei
 */
public enum CheckOrderStatus {
    WAIT_CHECK("1", "待质检"),
    WAIT_AUDIT("2", "待审核"),
    AUDITED("3", "已审核");
    // 编码
    private String code;
    // 信息
    private String msg;

    CheckOrderStatus(String code, String msg) {
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