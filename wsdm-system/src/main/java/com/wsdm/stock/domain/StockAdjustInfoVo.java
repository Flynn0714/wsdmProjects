/**
 * Copyright (C), 2002-2021, 苏宁易购电子商务有限公司
 * FileName: StockAdjustInfoVo
 * Author:   17101012
 * Date:     2021/1/20 11:33
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package com.wsdm.stock.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17101012
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class StockAdjustInfoVo extends StockAdjustInfo {

    private List<StockAdjustDetail> stockAdjustDetails;

    /**
     * 操作渠道 optChannel 1-新增保存 2-新增保存审核 3-修改保存 4-修改保存审核
     */
    private String optChannel;

    /**
     * 库调时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date auditTimeStart;

    /**
     * 库调时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date auditTimeEnd;

    public List<StockAdjustDetail> getStockAdjustDetails() {
        return stockAdjustDetails;
    }

    public void setStockAdjustDetails(List<StockAdjustDetail> stockAdjustDetails) {
        this.stockAdjustDetails = stockAdjustDetails;
    }

    public String getOptChannel() {
        return optChannel;
    }

    public void setOptChannel(String optChannel) {
        this.optChannel = optChannel;
    }

    public Date getAuditTimeStart() {
        return auditTimeStart;
    }

    public void setAuditTimeStart(Date auditTimeStart) {
        this.auditTimeStart = auditTimeStart;
    }

    public Date getAuditTimeEnd() {
        return auditTimeEnd;
    }

    public void setAuditTimeEnd(Date auditTimeEnd) {
        this.auditTimeEnd = auditTimeEnd;
    }
}
