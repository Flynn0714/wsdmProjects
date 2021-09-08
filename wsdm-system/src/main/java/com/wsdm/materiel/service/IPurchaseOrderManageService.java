package com.wsdm.materiel.service;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.materiel.domain.AutomaticPurchaseInfo;
import com.wsdm.materiel.domain.PurchaseOrderInfo;
import com.wsdm.materiel.domain.PurchaseOrderInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 物料采购订单信息Service接口
 *
 * @author wsdm
 * @date 2020-11-26
 */
public interface IPurchaseOrderManageService {
    /**
     * 查询物料采购订单信息
     *
     * @param id 物料采购订单信息ID
     * @return 物料采购订单信息
     */
    public PurchaseOrderInfoVo selectPurchaseOrderInfoById(Long id);

    /**
     * 查询物料采购订单信息列表
     *
     * @param purchaseOrderInfo 物料采购订单信息
     * @return 物料采购订单信息集合
     */
    public List<PurchaseOrderInfo> selectPurchaseOrderInfoList(PurchaseOrderInfoVo purchaseOrderInfo);

    public List<PurchaseOrderInfoVo> selectPurchaseListForReceive(PurchaseOrderInfo purchaseOrderInfo);

    /**
     * 获取自动采购信息
     *
     * @param salesNumberList 销售单
     * @return 获取自动采购信息
     */
    List<AutomaticPurchaseInfo> automaticPurchaseInfo(@Param("list") List<String> salesNumberList);
    /**
     * 新增物料采购订单信息
     *
     * @param purchaseOrderInfoVo 物料采购订单信息
     * @return 结果
     */
    public AjaxResult insertPurchaseOrderInfo(PurchaseOrderInfoVo purchaseOrderInfoVo);

    AjaxResult insertAutomaticPurchaseInfo(List<AutomaticPurchaseInfo> automaticPurchaseInfos);

    /**
     * 修改物料采购订单信息
     *
     * @param purchaseOrderInfoVo 物料采购订单信息
     * @return 结果
     */
    public AjaxResult updatePurchaseOrderInfo(PurchaseOrderInfoVo purchaseOrderInfoVo);

    /**
     * 批量删除物料采购订单信息
     *
     * @param ids 需要删除的物料采购订单信息ID
     * @return 结果
     */
    public AjaxResult deletePurchaseOrderInfoByIds(Long[] ids);

    /**
     * 删除物料采购订单信息信息
     *
     * @param id 物料采购订单信息ID
     * @return 结果
     */
    public int deletePurchaseOrderInfoById(Long id);

    /**
     * 审核物料采购订单
     *
     * @param id 物料采购订单信息ID
     * @return 结果
     */
    public AjaxResult updateApproveStatus(Long id, String action);

    List<?> selectMobilePurchaseApproveList(String orderType, String approveStatus);

    AjaxResult updateMobilePurchaseApproveStatus(Long id, String orderType, String action, String approvedType);

    Object selectMobilePurchaseApproveInfoById(Long id, String orderType);
    int selectMobilePurchaseOneTouch();
}
