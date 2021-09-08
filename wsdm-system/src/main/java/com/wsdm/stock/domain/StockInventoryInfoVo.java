/**
 * Copyright (C), 2002-2021, 苏宁易购电子商务有限公司
 * FileName: StockInventoryInfoVo
 * Author:   17101012
 * Date:     2021/1/22 16:34
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
public class StockInventoryInfoVo extends StockInventoryInfo {

    private List<StockInventoryDetail> stockInventoryDetails;

    /**
     * 操作渠道 1-待开始修改保存 2-盘点开始 3-待盘点修改保存 4-录入完成 5-盘点登账
     */
    private String optChannel;

    /**
     * 盘点日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inventoryDateStart;

    /**
     * 盘点日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inventoryDateEnd;

    public List<StockInventoryDetail> getStockInventoryDetails() {
        return stockInventoryDetails;
    }

    public void setStockInventoryDetails(List<StockInventoryDetail> stockInventoryDetails) {
        this.stockInventoryDetails = stockInventoryDetails;
    }

    public String getOptChannel() {
        return optChannel;
    }

    public void setOptChannel(String optChannel) {
        this.optChannel = optChannel;
    }

    public Date getInventoryDateStart() {
        return inventoryDateStart;
    }

    public void setInventoryDateStart(Date inventoryDateStart) {
        this.inventoryDateStart = inventoryDateStart;
    }

    public Date getInventoryDateEnd() {
        return inventoryDateEnd;
    }

    public void setInventoryDateEnd(Date inventoryDateEnd) {
        this.inventoryDateEnd = inventoryDateEnd;
    }
}
