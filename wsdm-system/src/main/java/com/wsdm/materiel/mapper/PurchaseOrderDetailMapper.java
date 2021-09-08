package com.wsdm.materiel.mapper;

import com.wsdm.materiel.domain.PurchaseOrderDetail;

import java.util.List;

/**
 * 物料采购订单详情Mapper接口
 *
 * @author wsdm
 * @date 2020-11-26
 */
public interface PurchaseOrderDetailMapper {
    /**
     * 查询物料采购订单详情
     *
     * @param id 物料采购订单详情ID
     * @return 物料采购订单详情
     */
    public PurchaseOrderDetail selectPurchaseOrderDetailById(Long id);

    /**
     * 查询物料采购订单详情
     *
     * @param purchaseNumber 物料采购订单号
     * @return 物料采购订单详情
     */
    public List<PurchaseOrderDetail> selectPurchaseOrderDetailByPurchaseNumber(String purchaseNumber);

    /**
     * 查询物料采购订单详情列表
     *
     * @param purchaseOrderDetail 物料采购订单详情
     * @return 物料采购订单详情集合
     */
    public List<PurchaseOrderDetail> selectPurchaseOrderDetailList(PurchaseOrderDetail purchaseOrderDetail);

    /**
     * 新增物料采购订单详情
     *
     * @param purchaseOrderDetail 物料采购订单详情
     * @return 结果
     */
    public int insertPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail);

    /**
     * 新增物料采购订单详情
     *
     * @param list 物料采购订单详情
     * @return 结果
     */
    public int insertPurchaseOrderDetailBatch(List<PurchaseOrderDetail> list);

    /**
     * 修改物料采购订单详情
     *
     * @param purchaseOrderDetail 物料采购订单详情
     * @return 结果
     */
    public int updatePurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail);

    /**
     * 删除物料采购订单详情
     *
     * @param id 物料采购订单详情ID
     * @return 结果
     */
    public int deletePurchaseOrderDetailById(Long id);

    /**
     * 删除物料采购订单详情
     *
     * @param purchaseNumber 物料采购订单编号
     * @return 结果
     */
    public int deletePurchaseOrderDetailByPurchaseNumber(String purchaseNumber);

    /**
     * 批量删除物料采购订单详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseOrderDetailByIds(Long[] ids);
}
