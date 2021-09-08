package com.wsdm.materiel.service;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.materiel.domain.*;

import java.util.List;

/**
 * 物料采购付款单信息Service接口
 *
 * @author wanglei
 * @date 2021-06-26
 */
public interface PurchasePayOrderManageService {
    /**
     * 查询物料采购付款单信息
     *
     * @param id 物料采购付款单信息ID
     * @return 物料采购付款单信息
     */
    public PurchasePayOrderInfoVo selectPurchasePayOrderInfoById(Long id);

    /**
     * 查询物料采购付款单信息列表
     *
     * @param purchasePayOrderInfo 物料采购付款单信息
     * @return 物料采购付款单信息集合
     */
    public List<PurchasePayOrderInfo> selectPurchasePayOrderInfoList(PurchasePayOrderInfo purchasePayOrderInfo);

    /**
     * 新增物料采购付款单信息
     *
     * @param purchasePayOrderInfo 物料采购付款单信息
     * @return 结果
     */
    public int insertPurchasePayOrderInfo(PurchasePayOrderInfo purchasePayOrderInfo);

    /**
     * 修改物料采购付款单信息
     *
     * @param purchasePayOrderInfo 物料采购付款单信息
     * @return 结果
     */
    public int updatePurchasePayOrderInfo(PurchasePayOrderInfo purchasePayOrderInfo);

    /**
     * 批量删除物料采购付款单信息
     *
     * @param ids 需要删除的物料采购付款单信息ID
     * @return 结果
     */
    public int deletePurchasePayOrderInfoByIds(Long[] ids);

    /**
     * 删除物料采购付款单信息信息
     *
     * @param id 物料采购付款单信息ID
     * @return 结果
     */
    public int deletePurchasePayOrderInfoById(Long id);

    List<PurchasePayOrderDetail> selectPurchasePayRecordList(PurchasePayOrderInfoVo purchasePayOrderInfo);

    AjaxResult insertPurchasePayRecord(PurchasePayOrderInfoVo purchasePayOrderInfo);

    List<PurchaseDealDetail> selectPurchaseDealDetailList(PurchasePayOrderInfoVo purchasePayOrderInfo);

    AjaxResult selectPurchasePayDetailInfo(String dealType, String dealNumber);

    PurchaseDealTableDataInfo getTotalDealNumAndAmount(PurchasePayOrderInfoVo purchasePayOrderInfo);

    List<PurchaseDealDetail> selectPurchaseDealRecordList(PurchasePayOrderInfoVo purchasePayOrderInfo);
}
