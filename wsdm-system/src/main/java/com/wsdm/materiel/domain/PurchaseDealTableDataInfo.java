/**
 * Copyright (C), 2002-2021, 苏宁易购电子商务有限公司
 * FileName: PurchaseDealTableDataInfo
 * Author:   17101012
 * Date:     2021/7/23 14:20
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package com.wsdm.materiel.domain;

import java.math.BigDecimal;

import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.page.TableDataInfo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17101012
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class PurchaseDealTableDataInfo extends TableDataInfo {

    /**
     * 交易总数量
     */
    @Excel(name = "交易总数量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal totalDealNum;

    /**
     * 交易总金额
     */
    @Excel(name = "交易总金额")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal totalDealAmount;

    public BigDecimal getTotalDealNum() {
        return totalDealNum;
    }

    public void setTotalDealNum(BigDecimal totalDealNum) {
        this.totalDealNum = totalDealNum;
    }

    public BigDecimal getTotalDealAmount() {
        return totalDealAmount;
    }

    public void setTotalDealAmount(BigDecimal totalDealAmount) {
        this.totalDealAmount = totalDealAmount;
    }
}
