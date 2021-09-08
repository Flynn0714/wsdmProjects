package com.wsdm.stock.service;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.stock.domain.SkStock;
import com.wsdm.stock.domain.SkStockBatch;
import com.wsdm.stock.domain.SkStockRequest;

import java.math.BigDecimal;
import java.util.List;

/**
 * 库存主Service接口
 *
 * @author wsdm
 * @date 2020-12-07
 */
public interface ISkStockService
{
    /**
     * 查询库存主
     *
     * @param id 库存主ID
     * @return 库存主
     */
    public SkStock selectSkStockById(Long id);

    /**
     * 查询库存主列表
     *
     * @param skStock 库存主
     * @return 库存主集合
     */
    public List<SkStock> selectSkStockList(SkStock skStock);

    /**
     * 新增库存主
     *
     * @param skStock 库存主
     * @return 结果
     */
    public int insertSkStock(SkStock skStock);

    /**
     * 原料采购-在途
     *
     * @param skStocks
     * @return 结果
     */
    public AjaxResult insertStockTransit(List<SkStockRequest> skStocks);

    /**
     * 原料采购-入库
     *
     * @param stockRequests
     * @return 结果
     */
    public AjaxResult insertPurchaseStock(List<SkStockRequest> stockRequests);

    /**
     * 原料采购-退货
     *
     * @param stockRequests
     * @return 结果
     */
    public AjaxResult returnPurchaseStock(List<SkStockRequest> stockRequests);

    /**
     * 库存调整-手工增加
     *
     * @param stockRequests
     * @return 结果
     */
    public AjaxResult addManual(List<SkStockRequest> stockRequests);

    /**
     * 库存调整-手工减少
     *
     * @param stockRequests
     * @return 结果
     */
    public AjaxResult subtractManual(List<SkStockRequest> stockRequests);

    /**
     * 库存调整-库存报损
     *
     * @param stockRequests
     * @return 结果
     */
    public AjaxResult lossReportStock(List<SkStockRequest> stockRequests);

    /**
     * 盘点调整-增加
     *
     * @param stockRequests
     * @return 结果
     */
    public AjaxResult addInventory(List<SkStockRequest> stockRequests);

    /**
     * 盘点调整-减少
     *
     * @param stockRequests
     * @return 结果
     */
    public AjaxResult subtractInventory(List<SkStockRequest> stockRequests);

    /**
     * 修改库存主
     *
     * @param skStock 库存主
     * @return 结果
     */
    public int updateSkStock(SkStock skStock);

    /**
     * 批量删除库存主
     *
     * @param ids 需要删除的库存主ID
     * @return 结果
     */
    public int deleteSkStockByIds(Long[] ids);

    /**
     * 删除库存主信息
     *
     * @param id 库存主ID
     * @return 结果
     */
    public int deleteSkStockById(Long id);

    SkStock getSkStock(String produce);

    List<SkStock> batchQueryByProduce(List<String> produceList);

    /**
     * 管理：新建在途
     */
    void addStockTransitNum(SkStock param);
//
//    /**
//     * 管理：确认新建在途库存
//     */
//    void confirmStockTransitNum(SkStockBatch stockBatch, BigDecimal stockTransitNum);

    /**
     * 新建库存
     */
    void addStock(SkStockBatch stockBatch);

//    /**
//     * 管理：扣减库存
//     */
//    void takeStock(String code, String produce, BigDecimal num, String operator);

//    /**
//     * 锁定库存
//     */
//    void lockStock(String produce, BigDecimal num, String operator);
//
//    /**
//     * 解锁库存
//     */
//    void unlockStock(String produce, BigDecimal num, String operator);
//
//    /**
//     * 确认扣减锁定库存
//     */
//    void confirmLockedStock(String produce, BigDecimal num, String operator);

    /**
     * 锁定库存
     */
    void lockStock(String code, String produce, BigDecimal num, String operator);

    /**
     * 解锁库存
     */
    void unlockStock(String code, String produce, BigDecimal num, String operator);

    /**
     * 确认扣减锁定库存
     */
    void confirmLockedStock(String code, String produce, BigDecimal num, String operator);

    void removeStockTransitNum(String code, String produce, BigDecimal num, String operator);
}
