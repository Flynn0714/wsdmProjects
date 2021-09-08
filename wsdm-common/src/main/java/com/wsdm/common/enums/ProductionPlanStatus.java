package com.wsdm.common.enums;

/**
 * 操作状态
 *
 * @author wsdm
 */
public enum ProductionPlanStatus {

    /**
     * 已审核
     */
    CFMD("1", "已确认"),

    RESOLVED("2", "已分解"),

    PRODUCE("3", "待生产"),

    UNKNOWN("-1", "错误");

    // 编码
    private String code;
    // 信息
    private String msg;

    ProductionPlanStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ProductionPlanStatus codeToEnum(String code) {
        for (ProductionPlanStatus e : ProductionPlanStatus.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return ProductionPlanStatus.UNKNOWN;
    }

    public static ProductionPlanStatus msgToEnum(String name) {
        for (ProductionPlanStatus e : ProductionPlanStatus.values()) {
            if (e.getMsg().equals(name)) {
                return e;
            }
        }
        return ProductionPlanStatus.UNKNOWN;
    }
}
