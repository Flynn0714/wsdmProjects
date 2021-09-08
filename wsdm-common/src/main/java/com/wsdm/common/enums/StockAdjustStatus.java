/**
 * Copyright (C), 2002-2021, 苏宁易购电子商务有限公司
 * FileName: StockAdjustStatus
 * Author:   17101012
 * Date:     2021/1/21 11:22
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package com.wsdm.common.enums;

/**
 * 库调状态
 *
 * @author wanglei
 * @version 1.0
 * @date 2021/1/12 15:11
 */
public enum StockAdjustStatus {

    TO_SUBMIT("1", "待提交"),

    TO_AUDIT("2", "未审核"),

    AUDITED("3", "已审核"),

    DELETED("4", "已删除");

    // 编码
    private String code;
    // 信息
    private String msg;

    StockAdjustStatus(String code, String msg) {
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
        for (StockAdjustStatus e : StockAdjustStatus.values()) {
            if (e.getCode().equals(code)) {
                return e.getMsg();
            }
        }
        return "";
    }
}
