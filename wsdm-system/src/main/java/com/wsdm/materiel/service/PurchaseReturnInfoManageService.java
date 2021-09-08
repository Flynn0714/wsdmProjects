package com.wsdm.materiel.service;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.materiel.domain.PurchaseReturnInfo;
import com.wsdm.materiel.domain.PurchaseReturnInfoVo;


import java.util.List;

/**
 * @author wjj
 * @Description 采购退货信息业务层
 * @date 2021/7/14 15:26
 */
public interface PurchaseReturnInfoManageService {

    public static final String STRING_0 = "0";
    public static final String STRING_1 = "1";
    public static final String STRING_2 = "2";
    public static final String STRING_3 = "3";

    /**
    * @Description: 查询采购退货信息列表
    * @Param:
    * @return:  集合
    */
    public List<PurchaseReturnInfo> selectPurchaseReturnInfoList(PurchaseReturnInfoVo purchaseReturnInfoVo);


    /**
    * @Description: 选择采购收获单
    * @Param:
    * @return:
    */
    public List<PurchaseReturnInfo> selectByReturnForReceive(PurchaseReturnInfo purchaseReturnInfo);



    /**
    * @Description: 新增采购退货单
    * @Param:   purchaseReturnInfo 退货单信息对象
    * @return:
    */
    public AjaxResult insertPurchaseReturnInfo(PurchaseReturnInfo purchaseReturnInfo);


    /**
    * @Description: 修改退货单信息
    * @Param:  purchaseReturnInfo 退货单信息对象
    * @return:
    */
    public AjaxResult updatePurchaseReturnInfo(PurchaseReturnInfo purchaseReturnInfo);

    /**
     * 查询退货订单信息
     *
     * @param id 退货订单信息ID
     * @return 退货订单信息
     */
    public PurchaseReturnInfo selectReturnOrderInfoById(Long id);

    /**
     * 查询退货订单信息
     *
     * @param returnNumber 退货订单信息ID
     * @return 退货订单信息
     */
    public PurchaseReturnInfoVo selectReturnOrderInfoByReturnNumber(String returnNumber);

    /**
    * @Description: 根据退货单id删除退货单
    * @Param:  id
    * @return:
    */
    public int deleteReturnOrderInfoById(Long id);

    /**
     * 审核采购退货单
     * @param id
     * @param action
     * @return
     */

    public AjaxResult updateApproveStatus(Long id, String action);


    /**
     * 批量删除退货订单信息
     *
     * @param ids 需要删除的退货订单信息ID
     * @return 结果
     */
    public AjaxResult deleteReturnOrderInfoByIds(Long[] ids);



}
