package com.wsdm.common.enums;

/**
 * 供应商合同审核状态
 *
 * @author wanglei
 * @version 1.0
 * @date 2021/4/28
 */
public enum ContractAuditStatus {

    /**
     * 审核状态 0 未提交 1 待审核 2 已审核 3 已删除 字典 dict_contract_audit_status
     */
    NOT_SUBMIT("0", "未提交"),
    NOT_APPROVE("1", "待审核"),
    APPROVED("2", "已审核"),
    DELETED("3", "已删除");

    /**
     * 状态编码
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    ContractAuditStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String codeToDesc(String code) {
        for (ContractAuditStatus contractAuditStatus : ContractAuditStatus.values()) {
            if (contractAuditStatus.getCode().equals(code)) {
                return contractAuditStatus.getDesc();
            }
        }
        return "";
    }
}
