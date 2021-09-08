package com.wsdm.materiel.mapper;

import com.wsdm.materiel.domain.PurchaseReturnDetail;
import com.wsdm.materiel.domain.PurchaseReturnInfo;
import com.wsdm.materiel.domain.PurchaseReturnInfoVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wjj
 * @Description 采购退货信息Mapper接口
 * @date 2021/7/14 09:56
 */

public interface PurchaseReturnInfoMapper {

    /**
     * @Description: 查询采购退货信息列表
     * @Param:
     * @return: 采购退货信息集合
     */
    public List<PurchaseReturnInfo> selectPurchaseReturnInfoList(PurchaseReturnInfoVo purchaseReturnInfoVo);


    /**
     * 选择采购收货单
     */
    public List<PurchaseReturnInfo> selectPurchaseReturnForReceiveList(PurchaseReturnInfo purchaseReturnInfo);




    /**
     * @Description: 新增采购退货单信息
     * @Param: purchaseReturnInfo 采退信息对象
     * @return: 新增结果
     */
    public int insertPurchaseReturnOrderInfo(PurchaseReturnInfo purchaseReturnInfo);


    /**
     * 查询最大物料退货单号
     *
     * @param prefix 物料退货单号前缀
     * @return 结果
     */
    public String getMaxReturnCode(String prefix);


    /**
    * @Description: 修改退货订单信息
    * @Param:    purchaseReturnInfo 退货订单信息
    * @return:
    */
    public int updatePurchaseReturnInfo(PurchaseReturnInfo purchaseReturnInfo);


    /**
     * 查询退货订单信息
     *
     * @param id 退货订单信息ID
     * @return 退货订单信息
     */
    public PurchaseReturnInfo selectReturnOrderInfoById(Long id);


    /**
    * @Description: 根据退货单id删除采购退货单信息
    * @Param:  id
    * @return:
    */
    public int deleteReturnOrderInfoById(Long id);


    /**
     * 更新物料退货订单状态
     *
     * @param returnInfo
     * @return 结果
     */
    public int updateReturnStatus(PurchaseReturnInfo returnInfo);

    /**
     * 批量删除退货订单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteReturnOrderInfoByIds(Long[] ids);



    /**
     * 查询退货订单信息
     *
     * @param ids 退货订单ID
     * @return 退货订单订单信息
     */
    public List<PurchaseReturnInfo> selectReturnOrderInfoByIds(Long[] ids);


    PurchaseReturnInfoVo selectReturnOrderInfoByReturnNumber(String returnNumber);
}
