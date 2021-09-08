package com.wsdm.common.enums;


/**
 * 仓库分区枚举值
 *
 * @author wanglei
 * @version 1.0
 * @date 2021/1/28 15:11
 */
public enum StockEnum {

    RAW_MATERIAL_STOCK("A", "原料仓"),

    SEMI_PRODUCT_STOCK("B", "半成品仓"),

    FINISHED_PRODUCT_STOCK("C", "成品仓"),

    HARDWARE_STOCK("D", "五金仓"),

    ROUGH_FINISH_STOCK("E", "粗加工品仓");

    /**
     * 仓库编码
     */
    private String code;

    /**
     * 仓库名称
     */
    private String name;

    StockEnum(String code, String name) {
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
        for (StockEnum e : StockEnum.values()) {
            if (e.getCode().equals(code)) {
                return e.getName();
            }
        }
        return "";
    }
}