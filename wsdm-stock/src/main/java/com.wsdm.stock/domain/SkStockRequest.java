/**
 * Copyright (C), 2002-2021, 苏宁易购电子商务有限公司
 * FileName: SkStockRequest
 * Author:   17101012
 * Date:     2021/1/29 16:06
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package com.wsdm.stock.domain;

import java.math.BigDecimal;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17101012
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SkStockRequest extends SkStock {

    /**
     * 批次编码
     */
    private String batchCode;
    /**
     * 供应商
     */
    private String supplierCode;
    /**
     * 锁定单号
     */
    private String lockNumber;

    /**
     * 锁定单号类型
     */
    private String lockType;

    /**
     * 操作类型
     */
    private String operation;

    /**
     * 操作说明
     */
    private String operationName;
    /**
     * 变动单号
     */
    private String relationNumber;

    /**
     * 变动编号
     */
    private String relationCode;

    /**
     * 变动数量 整数
     */
    private BigDecimal changeNum;


    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getLockNumber() {
        return lockNumber;
    }

    public void setLockNumber(String lockNumber) {
        this.lockNumber = lockNumber;
    }

    public String getLockType() {
        return lockType;
    }

    public void setLockType(String lockType) {
        this.lockType = lockType;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getRelationNumber() {
        return relationNumber;
    }

    public void setRelationNumber(String relationNumber) {
        this.relationNumber = relationNumber;
    }

    public String getRelationCode() {
        return relationCode;
    }

    public void setRelationCode(String relationCode) {
        this.relationCode = relationCode;
    }

    public BigDecimal getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(BigDecimal changeNum) {
        this.changeNum = changeNum;
    }
}
