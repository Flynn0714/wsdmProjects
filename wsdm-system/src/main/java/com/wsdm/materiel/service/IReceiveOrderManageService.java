package com.wsdm.materiel.service;


import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.materiel.domain.ReceiveOrderInfo;
import com.wsdm.materiel.domain.ReceiveOrderInfoVo;

import java.util.List;
import java.util.Map;

/**
 * 物料收货订单信息Service接口
 *
 * @author wsdm
 * @date 2021-01-09
 */
public interface IReceiveOrderManageService {
    /**
     * 查询物料收货订单信息
     *
     * @param id 物料收货订单信息ID
     * @return 物料收货订单信息
     */
    public ReceiveOrderInfo selectReceiveOrderInfoById(Long id);

    /**
     * 查询物料收货订单信息
     *
     * @param receiveNumber 物料收货订单信息ID
     * @return 物料收货订单信息
     */
    public ReceiveOrderInfo selectReceiveOrderInfoByReceiveNumber(String receiveNumber);

    /**
     * 查询物料收货订单信息列表
     *
     * @param receiveOrderInfo 物料收货订单信息
     * @return 物料收货订单信息集合
     */
    public List<ReceiveOrderInfo> selectReceiveOrderInfoList(ReceiveOrderInfo receiveOrderInfo);

    /**
     * 新增物料收货订单信息
     *
     * @param receiveOrderInfo 物料收货订单信息
     * @return 结果
     */
    public AjaxResult insertReceiveOrderInfo(ReceiveOrderInfoVo receiveOrderInfo);

    /**
     * 修改物料收货订单信息
     *
     * @param receiveOrderInfo 物料收货订单信息
     * @return 结果
     */
    public AjaxResult updateReceiveOrderInfo(ReceiveOrderInfoVo receiveOrderInfo);

    /**
     * 批量删除物料收货订单信息
     *
     * @param ids 需要删除的物料收货订单信息ID
     * @return 结果
     */
    public int deleteReceiveOrderInfoByIds(Long[] ids);

    /**
     * 删除物料收货订单信息信息
     *
     * @param id 物料收货订单信息ID
     * @return 结果
     */
    public int deleteReceiveOrderInfoById(Long id);

    AjaxResult updateApproveStatus(Long id, String action);

    List<ReceiveOrderInfo> selectListForPay(ReceiveOrderInfo receiveOrderInfo);

    List<Map<String,Object>> selectDetailListForPay(ReceiveOrderInfo receiveOrderInfo);
}
