package com.wsdm.materiel.mapper;


import com.wsdm.materiel.domain.PurchaseDealDetail;
import com.wsdm.materiel.domain.PurchaseDealTableDataInfo;
import com.wsdm.materiel.domain.PurchasePayOrderInfo;
import com.wsdm.materiel.domain.PurchasePayOrderInfoVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 物料采购付款单信息Mapper接口
 *
 * @author wanglei
 * @date 2021-06-26
 */
public interface PurchasePayOrderInfoMapper {
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
     * 删除物料采购付款单信息
     *
     * @param id 物料采购付款单信息ID
     * @return 结果
     */
    public int deletePurchasePayOrderInfoById(Long id);

    /**
     * 批量删除物料采购付款单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchasePayOrderInfoByIds(Long[] ids);

    String getMaxPayCode(String prefix);

    BigDecimal getTotalPaidAmountByReceiveNumber(String receiveNumber);

    int updatePayStatus(PurchasePayOrderInfoVo infoVo);

    List<PurchaseDealDetail> selectPurchaseDealDetailList(PurchasePayOrderInfoVo infoVo);

    PurchaseDealTableDataInfo getTotalDealNumAndAmount(PurchasePayOrderInfoVo purchasePayOrderInfo);

    List<PurchaseDealDetail> selectPurchaseDealRecordList(PurchasePayOrderInfoVo infoVo);
}
