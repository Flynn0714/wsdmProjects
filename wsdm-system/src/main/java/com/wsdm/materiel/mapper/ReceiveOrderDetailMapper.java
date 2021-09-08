package com.wsdm.materiel.mapper;


import com.wsdm.materiel.domain.ReceiveOrderDetail;
import com.wsdm.materiel.domain.ReceiveOrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 物料收货订单详情Mapper接口
 *
 * @author wsdm
 * @date 2021-01-09
 */
public interface ReceiveOrderDetailMapper {
    /**
     * 查询物料收货订单详情
     *
     * @param id 物料收货订单详情ID
     * @return 物料收货订单详情
     */
    public ReceiveOrderDetail selectReceiveOrderDetailById(Long id);

    /**
     * 查询物料收货订单详情列表
     *
     * @param receiveOrderDetail 物料收货订单详情
     * @return 物料收货订单详情集合
     */
    public List<ReceiveOrderDetail> selectReceiveOrderDetailList(ReceiveOrderDetail receiveOrderDetail);

    /**
     * 新增物料收货订单详情
     *
     * @param receiveOrderDetail 物料收货订单详情
     * @return 结果
     */
    public int insertReceiveOrderDetail(ReceiveOrderDetail receiveOrderDetail);

    /**
     * 修改物料收货订单详情
     *
     * @param receiveOrderDetail 物料收货订单详情
     * @return 结果
     */
    public int updateReceiveOrderDetail(ReceiveOrderDetail receiveOrderDetail);

    /**
     * 删除物料收货订单详情
     *
     * @param id 物料收货订单详情ID
     * @return 结果
     */
    public int deleteReceiveOrderDetailById(Long id);

    /**
     * 批量删除物料收货订单详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteReceiveOrderDetailByIds(Long[] ids);

    /**
     * 查询采购订单已收货数量
     *
     * @param purchaseNumber
     * @param receiveOrderDetail
     * @return 结果
     */
    public int queryReceivedNum(@Param("purchaseNumber") String purchaseNumber, @Param("receiveOrderDetail") ReceiveOrderDetail receiveOrderDetail);

    /**
     * 查询物料采购订单详情
     *
     * @param receiveNumber 物料收货订单号
     * @return 物料收货订单详情
     */
    public List<ReceiveOrderDetail> selectReceiveOrderDetailByReceiveNumber(String receiveNumber);

    /**
     * 新增物料收货订单详情
     *
     * @param list 物料收货订单详情
     * @return 结果
     */
    public int insertReceiveOrderDetailBatch(List<ReceiveOrderDetail> list);

    public int updateReceiveOrderDetailBatch(List<ReceiveOrderDetail> list);

    /**
     * 删除物料收货订单详情
     *
     * @param receiveNumber 物料收货订单编号
     * @return 结果
     */
    public int deleteReceiveOrderDetailByReceiveNumber(String receiveNumber);

    List<Map<String, Object>> selectDetailListForPay(ReceiveOrderInfo receiveOrderInfo);

    public int updateCheckInfo(String receiveNumber); //ffl
}