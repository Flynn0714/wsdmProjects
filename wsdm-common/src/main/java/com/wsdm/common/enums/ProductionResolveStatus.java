package com.wsdm.common.enums;

/**
 * 操作状态
 *
 * @author wsdm
 */
public enum ProductionResolveStatus {

    /**
     * 已审核
     */
    REVIEWED("1", "待分解"),

    RESOLVE("2", "已分解"),

    PRODUCE("3", "待生产"),

    UNKNOWN("-1", "错误");

    // 编码
    private String code;
    // 信息
    private String msg;

    ProductionResolveStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ProductionResolveStatus codeToEnum(String code) {
        for (ProductionResolveStatus e : ProductionResolveStatus.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return ProductionResolveStatus.UNKNOWN;
    }

    public static ProductionResolveStatus msgToEnum(String name) {
        for (ProductionResolveStatus e : ProductionResolveStatus.values()) {
            if (e.getMsg().equals(name)) {
                return e;
            }
        }
        return ProductionResolveStatus.UNKNOWN;
    }
}
