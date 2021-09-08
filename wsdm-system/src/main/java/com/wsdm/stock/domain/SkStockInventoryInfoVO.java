package com.wsdm.stock.domain;

import java.util.List;

/**
 * 库存盘点信息对象 sk_stock_inventory_info
 *
 * @author wsdm
 * @date 2021-05-25
 */
public class SkStockInventoryInfoVO
{
    private SkStockInventoryInfo         skStockInventoryInfo;
    private List<SkStockInventoryDetail> skStockInventoryDetailList;

    /**
     * 审批类型：1 - 开始盘点，2 - 录入完成，3 - 盘点登账
     */
    private String action;

    public SkStockInventoryInfo getSkStockInventoryInfo()
    {
        return skStockInventoryInfo;
    }

    public void setSkStockInventoryInfo(SkStockInventoryInfo skStockInventoryInfo)
    {
        this.skStockInventoryInfo = skStockInventoryInfo;
    }

    public List<SkStockInventoryDetail> getSkStockInventoryDetailList()
    {
        return skStockInventoryDetailList;
    }

    public void setSkStockInventoryDetailList(List<SkStockInventoryDetail> skStockInventoryDetailList)
    {
        this.skStockInventoryDetailList = skStockInventoryDetailList;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }
}
