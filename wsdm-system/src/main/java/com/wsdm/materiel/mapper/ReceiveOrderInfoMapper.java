package com.wsdm.materiel.mapper;

import com.wsdm.materiel.domain.ReceiveOrderInfo;
import com.wsdm.materiel.domain.ReceiveOrderInfoVo;

import java.util.List;


/**
 * 物料收货订单信息Mapper接口
 *
 * @author wsdm
 * @date 2021-01-09
 */
public interface ReceiveOrderInfoMapper {
    /**
     * 查询物料收货订单信息
     *
     * @param id 物料收货订单信息ID
     * @return 物料收货订单信息
     */
    public ReceiveOrderInfoVo selectReceiveOrderInfoById(Long id);

    public ReceiveOrderInfoVo selectReceiveOrderInfoByCheckNumber(String checkNumber);

    /**
     * 查询物料收货订单信息列表
     *
     * @param receiveOrderInfo 物料收货订单信息
     * @return 物料收货订单信息集合
     */
    public List<ReceiveOrderInfo> selectReceiveOrderInfoList(ReceiveOrderInfo receiveOrderInfo);

    /**
     * 查询物料收货订单信息列表
     *
     * @param receiveOrderInfo 物料收货订单信息
     * @return 物料收货订单信息集合
     */
    public List<ReceiveOrderInfo> selectListForPay(ReceiveOrderInfo receiveOrderInfo);

    /**
     * 新增物料收货订单信息
     *
     * @param receiveOrderInfo 物料收货订单信息
     * @return 结果
     */
    public int insertReceiveOrderInfo(ReceiveOrderInfo receiveOrderInfo);

    /**
     * 修改物料收货订单信息
     *
     * @param receiveOrderInfo 物料收货订单信息
     * @return 结果
     */
    public int updateReceiveOrderInfo(ReceiveOrderInfo receiveOrderInfo);

    /**
     * 删除物料收货订单信息
     *
     * @param id 物料收货订单信息ID
     * @return 结果
     */
    public int deleteReceiveOrderInfoById(Long id);

    /**
     * 批量删除物料收货订单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteReceiveOrderInfoByIds(Long[] ids);

    /**
     * 查询最大物料收货单号
     *
     * @param code 物料收货单号前缀
     * @return 结果
     */
    public String getMaxReceiveCode(String code);

    int updateReceiveStatus(ReceiveOrderInfoVo receiveInfo);

    ReceiveOrderInfoVo selectReceiveOrderInfoByReceiveNumber(String receiveNumber);
}
