package com.wsdm.common.enums;

public enum TransactionType {
    /**
     * 发货
     */
    DELIVER("DELIVER", "销售发货"),

    /**
     * 换货
     */
    EXCHANGE("EXCHANGE", "销售换货"),

    /**
     * 退货
     */
    RETURN("RETURN", "销售退货");

    // 编码
    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    TransactionType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String codeToValue(String code) {
        for (TransactionType t : TransactionType.values()) {
            if (t.getCode().equals(code)) {
                return t.getValue();
            }
        }
        return "";
    }
}
