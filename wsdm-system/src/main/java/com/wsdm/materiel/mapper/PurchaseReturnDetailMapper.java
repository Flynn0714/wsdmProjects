package com.wsdm.materiel.mapper;

import com.wsdm.materiel.domain.PurchaseReturnDetail;
import com.wsdm.materiel.domain.PurchaseReturnInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wjj
 * @Description  采购退货详情单Mapper接口
 * @date 2021/7/15 15:46
 */
public interface PurchaseReturnDetailMapper {


    /**
    *  查询采购退货单详情列表
    * @Param:
    * @return:
    */
    public List<PurchaseReturnDetail> selectPurchaseReturnDetailList(PurchaseReturnDetail purchaseReturnDetail);


    /**
     * 新增采购退货订单详情信息
     *
     * @param list 物料退货订单详情
     * @return 结果
     */
    public int insertReturnDetailBatch(List<PurchaseReturnDetail> list);

    /**
     * 删除采购退货订单详情
     *
     * @param returnNumber 物料退货订单编号
     * @return 结果
     */
    public int deleteReturnDetail(String returnNumber);


    /**
     * 根据退货单号查询退货订单详情
     *
     * @param returnNumber 退货订单详情ID
     * @return 退货订单详情
     */
    List<PurchaseReturnDetail> selectReturnOrderDetailByReturnNumber(String returnNumber);


    /**
    * @Description:  查询已退货数量
    * @Param:
    * @return:  结果
    */
    public Integer queryReturnedNum(@Param("receiveNumber") String receiveNumber,@Param("purchaseReturnDetail") PurchaseReturnDetail purchaseReturnDetail);


    /**
     * 统计已退货数量
     * @return
     */
    @Select("select sum(wrod.return_quantity)\n" +
            " from wl_return_order_info wroi\n" +
            " left join wl_return_order_detail wrod\n" +
            " on wroi.return_number = wrod.return_number\n" +
            "WHERE wroi.receive_number=#{receiveNumber} \n" +
            "and wroi.status = 2")
    public Integer countReturnQuantity(String receiveNumber);

}
