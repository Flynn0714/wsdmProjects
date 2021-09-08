package com.wsdm.common.enums;

/**
 * 供应商合同状态
 *
 * @author wanglei
 * @version 1.0
 * @date 2021/4/28
 */
public enum ContractStatus {

    /**
     * 合同状态 0 未生效 1 待生效 2 生效中 3 已过期 字典 dict_contract_status
     */
    INEFFECTIVE("0", "未生效"),
    WAIT_EFFECTIVE("1", "待生效"),
    EFFECTIVE("2", "生效中"),
    EXPIRED("3", "已过期");

    /**
     * 状态编码
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    ContractStatus(String code, String desc) {
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
        for (ContractStatus contractStatus : ContractStatus.values()) {
            if (contractStatus.getCode().equals(code)) {
                return contractStatus.getDesc();
            }
        }
        return "";
    }
}
