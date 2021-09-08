package com.wsdm.common.enums;


/**
 * 采购类型枚举值
 *
 * @author wanglei
 * @version 1.0
 * @date 2021/1/28 15:11
 */
public enum PurchaseTypeEnum {

    RAW_MATERIAL("A", "原料采购"),

    SEMI_PRODUCT("B", "外协半成品采购"),

    ROUGH_FINISH_PRODUCT("E", "外协粗加工品采购"),

    HARDWARE("D", "五金采购");

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    PurchaseTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    public static String codeToName(String code) {
        for (PurchaseTypeEnum e : PurchaseTypeEnum.values()) {
            if (e.getCode().equals(code)) {
                return e.getName();
            }
        }
        return "";
    }

}