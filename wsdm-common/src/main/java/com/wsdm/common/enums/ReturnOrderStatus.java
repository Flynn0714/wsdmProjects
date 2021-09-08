package com.wsdm.common.enums;

/**
 * 退货订单状态
 *
 * @author wanglei
 */
public enum ReturnOrderStatus {

    NOT_SUBMIT("0", "未提交"),
    WAIT_APPROVE("1", "待审核"),
    APPROVED("2", "已审核"),
    DELETED("3", "已删除"),
    CANCEL("4", "已作废");

    /**
     * 编码
     */
    private String code;
    /**
     * 信息
     */
    private String msg;

    ReturnOrderStatus(String code, String msg) {
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