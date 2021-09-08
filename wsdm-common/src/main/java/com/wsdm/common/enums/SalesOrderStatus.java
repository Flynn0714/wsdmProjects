package com.wsdm.common.enums;

/**
 * 销售订单状态
 *
 * @author wsdm
 */
public enum SalesOrderStatus {
    DELETED("-1", "已删除"),
    NOT_SUBMIT("0", "未提交"),
    TO_BE_REVIEWED("1", "待审核"),
    REVIEWED("2", "已审核");
    // 编码
    private String code;
    // 信息
    private String msg;

    SalesOrderStatus(String code, String msg) {
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
