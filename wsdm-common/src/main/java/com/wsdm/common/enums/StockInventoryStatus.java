package com.wsdm.common.enums;

/**
 * 库存盘点状态
 *
 * @author wanglei
 * @version 1.0
 * @date 2021/1/12 15:11
 */
public enum StockInventoryStatus {

    NOT_STARTED("1", "未开始"),

    WAIT_ENTRY("2", "待盘点录入"),

    WAIT_BOOKED("3", "待盘点登账"),

    COMPLETED("4", "已完成"),

    DELETED("5", "已删除");


    // 编码
    private String code;
    // 信息
    private String msg;

    StockInventoryStatus(String code, String msg) {
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
        for (StockInventoryStatus e : StockInventoryStatus.values()) {
            if (e.getCode().equals(code)) {
                return e.getMsg();
            }
        }
        return "";
    }

}
