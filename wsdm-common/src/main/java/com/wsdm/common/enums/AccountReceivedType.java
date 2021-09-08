package com.wsdm.common.enums;

public enum AccountReceivedType {
    /**
     * 预收款
     */
    ADVANCE("ADVANCE", "预收款"),

    /**
     * 收款
     */
    COLLECTION("COLLECTION", "收款");

    // 编码
    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    AccountReceivedType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String codeToValue(String code) {
        for (AccountReceivedType a : AccountReceivedType.values()) {
            if (a.getCode().equals(code)) {
                return a.getValue();
            }
        }
        return "";
    }
}
