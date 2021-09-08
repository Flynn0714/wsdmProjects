package com.wsdm.common.enums;

/**
 * 领料状态
 *
 * @author wangr
 * @version 1.0
 * @date 2021/1/12 15:11
 */
public enum  MaterialStatus {

    APPROVE("1", "待审核"),
    APPROVED("2", "已审核"),
    DELETED("0", "已删除");
    // 编码
    private String code;
    // 信息
    private String msg;

    MaterialStatus(String code, String msg) {
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
        for (ProductionResolveStatus e : ProductionResolveStatus.values()) {
            if (e.getCode().equals(code)) {
                return e.getMsg();
            }
        }
        return "";
    }
}
