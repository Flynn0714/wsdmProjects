package com.wsdm.materiel.mapper;

import com.wsdm.materiel.domain.AutomaticPurchaseInfo;
import com.wsdm.materiel.domain.PurchaseOrderInfo;
import com.wsdm.materiel.domain.PurchaseOrderInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 物料采购订单信息Mapper接口
 *
 * @author wsdm
 * @date 2020-11-26
 */
public interface PurchaseOrderInfoMapper {
    /**
     * 查询物料采购订单信息
     *
     * @param id 物料采购订单信息ID
     * @return 物料采购订单信息
     */
    public PurchaseOrderInfoVo selectPurchaseOrderInfoById(Long id);

    /**
     * 查询物料采购订单信息
     *
     * @param ids 物料采购订单信息ID
     * @return 物料采购订单信息
     */
    public List<PurchaseOrderInfo> selectPurchaseOrderInfoByIds(Long[] ids);

    /**
     * 查询物料采购订单信息列表
     *
     * @param purchaseOrderInfo 物料采购订单信息
     * @return 物料采购订单信息集合
     */
    public List<PurchaseOrderInfo> selectPurchaseOrderInfoList(PurchaseOrderInfoVo purchaseOrderInfo);

    /**
     * 获取自动采购信息
     *
     * @param salesNumberList 销售单
     * @return 物料采购订单信息集合
     */
    List<AutomaticPurchaseInfo> automaticPurchaseInfo(@Param("list") List<String> salesNumberList);
    /**
     * 查询最大物料采购单号
     *
     * @param prefix 物料采购单号前缀
     * @return 结果
     */
    public String getMaxPurchaseCode(String prefix);

    String getMaxAutomaticPurchaseCode(String prefix);

    /**
     * 新增物料采购订单信息
     *
     * @param purchaseOrderInfo 物料采购订单信息
     * @return 结果
     */
    public int insertPurchaseOrderInfo(PurchaseOrderInfoVo purchaseOrderInfo);

    /**
     * 修改物料采购订单信息
     *
     * @param purchaseOrderInfo 物料采购订单信息
     * @return 结果
     */
    public int updatePurchaseOrderInfo(PurchaseOrderInfo purchaseOrderInfo);

    /**
     * 删除物料采购订单信息
     *
     * @param id 物料采购订单信息ID
     * @return 结果
     */
    public int deletePurchaseOrderInfoById(Long id);

    /**
     * 批量删除物料采购订单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseOrderInfoByIds(Long[] ids);

    /**
     * 更新物料采购订单审核状态
     *
     * @param purchaseInfo
     * @return 结果
     */
    public int updateAuditStatus(PurchaseOrderInfo purchaseInfo);

    List<PurchaseOrderInfoVo> selectPurchaseListForReceive(PurchaseOrderInfo purchaseOrderInfo);
}
