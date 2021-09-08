package com.wsdm.common.enums;

/**
 * 采购订单状态
 *
 * @author wsdm
 */
public enum PurchaseOrderStatus {
    NOT_SUBMIT("0", "未提交"),
    WAIT_AUDIT("1", "待审核"),
    AUDITED("2", "已审核");
    // 编码
    private String code;
    // 信息
    private String msg;

    PurchaseOrderStatus(String code, String msg) {
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