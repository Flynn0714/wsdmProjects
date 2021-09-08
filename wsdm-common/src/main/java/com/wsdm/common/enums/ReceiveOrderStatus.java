package com.wsdm.common.enums;

/**
 * 收货订单状态
 *
 * @author wanglei
 */
public enum ReceiveOrderStatus {
    NOT_SUBMIT("0", "未提交"),
    WAIT_CHECK("1", "待质检"),
    WAIT_AUDIT("2", "待审核"),
    AUDITED("3", "已审核");
    // 编码
    private String code;
    // 信息
    private String msg;

    ReceiveOrderStatus(String code, String msg) {
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