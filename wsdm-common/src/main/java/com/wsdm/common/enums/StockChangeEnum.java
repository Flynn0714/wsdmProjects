package com.wsdm.common.enums;


/**
 * 库存变更类型枚举值
 *
 * @author wanglei
 * @date 2021-05-10
 */
public enum StockChangeEnum {

    PURCHASE_INPUT("purchase_input", "采购进库"),

    PURCHASE_OUT("purchase_out", "采购退库"),

    SALE_OUT("sale_out", "销售出库"),

    SALE_INPUT("sale_input", "销售退库"),

    MANUAL_ADD("manual_add", "手工增加"),

    STOCK_LOSS_REPORT("stock_loss_report", "库存报损"),

    STOCK_COLLECT_RETURNS("stock_collect_returns", "库存领退"),

    STOCK_SYSTEM_ADD("stock_system_add", "系统自动-增加"),

    MANUAL_SUBTRACT("manual_subtract", "手工减少"),

    INVENTORY_SUBTRACT("inventory_subtract", "盘点调整-减少"),

    STOCK_SYSTEM_SUBTRACT("stock_system_subtract", "系统自动-减少"),

    INVENTORY_ADD("inventory_add", "盘点调整-增加");

    /**
     * 操作类型
     */
    private String type;

    /**
     * 操作描述
     */
    private String desc;

    StockChangeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }


    public String getDesc() {
        return desc;
    }

    public String getType() {
        return type;
    }


    public static String typeToDesc(String type) {
        for (StockChangeEnum e : StockChangeEnum.values()) {
            if (e.getType().equals(type)) {
                return e.getDesc();
            }
        }
        return "";
    }
}