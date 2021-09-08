package com.wsdm.materiel.mapper;

import com.wsdm.materiel.domain.PurchasePayOrderDetail;
import com.wsdm.materiel.domain.PurchasePayOrderInfoVo;

import java.util.List;

/**
 * 物料采购付款单详情Mapper接口
 *
 * @author wanglei
 * @date 2021-06-26
 */
public interface PurchasePayOrderDetailMapper {
    /**
     * 查询物料采购付款单详情
     *
     * @param id 物料采购付款单详情ID
     * @return 物料采购付款单详情
     */
    public PurchasePayOrderDetail selectPurchasePayOrderDetailById(Long id);

    /**
     * 查询物料采购付款单详情
     *
     * @param payNumber 物料采购付款单详情payNumber
     * @return 物料采购付款单详情
     */
    public List<PurchasePayOrderDetail> selectPurchasePayOrderDetailByPayNumber(String payNumber);

    /**
     * 查询物料采购付款单详情列表
     *
     * @param infoVo 物料采购付款单详情
     * @return 物料采购付款单详情集合
     */
    public List<PurchasePayOrderDetail> selectPurchasePayOrderDetailList(PurchasePayOrderInfoVo infoVo);

    /**
     * 新增物料采购付款单详情
     *
     * @param purchasePayOrderDetail 物料采购付款单详情
     * @return 结果
     */
    public int insertPurchasePayOrderDetail(PurchasePayOrderDetail purchasePayOrderDetail);

    /**
     * 修改物料采购付款单详情
     *
     * @param purchasePayOrderDetail 物料采购付款单详情
     * @return 结果
     */
    public int updatePurchasePayOrderDetail(PurchasePayOrderDetail purchasePayOrderDetail);

    /**
     * 删除物料采购付款单详情
     *
     * @param id 物料采购付款单详情ID
     * @return 结果
     */
    public int deletePurchasePayOrderDetailById(Long id);

    /**
     * 批量删除物料采购付款单详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchasePayOrderDetailByIds(Long[] ids);

    int insertPurchasePayOrderDetailBatch(List<PurchasePayOrderDetail> details);

    int deletePurchasePayOrderDetailByPayNumber(String payNumber);
}
