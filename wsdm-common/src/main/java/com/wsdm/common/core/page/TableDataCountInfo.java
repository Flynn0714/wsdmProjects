package com.wsdm.common.core.page;

import java.math.BigDecimal;

/**
 * 表格分页数据对象
 *
 * @author wsdm
 */
public class TableDataCountInfo extends TableDataInfo {
    private BigDecimal quantity;

    private BigDecimal amount;

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
