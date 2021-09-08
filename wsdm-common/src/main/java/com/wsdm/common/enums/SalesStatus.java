package com.wsdm.common.enums;

/**
 * 发货状态
 *
 * @author wangr
 * @version 1.0
 * @date 2021/1/12 15:11
 */
public enum SalesStatus {

    TO_BE_DELIVERED("1", "待发货"),
    PARTIAL_DELIVERED("2", "部分发货"),
    DELIVERED("3", "已发货"),
    DELETED("0", "已删除");
    // 编码
    private String code;
    // 信息
    private String msg;

    SalesStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String codeToMsg(String code) {
        for (SalesStatus e : SalesStatus.values()) {
            if (e.getCode().equals(code)) {
                return e.getMsg();
            }
        }
        return "";
    }
}
