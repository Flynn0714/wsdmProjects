package com.wsdm.sale.mapper;

import com.wsdm.sale.domain.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 销售退货Mapper接口
 *
 * @author Lu
 * @date 2021-7-16
 */
public interface SalesGoodsRejectMapper {

    List<SalesGoodsReject> selectSalesGoodsRejectAll(SalesGoodsRejectQuery salesGoodsRejectQuery);

    List<SalesGoodsRejectInfo> selectSalesGoodsRejectInfo(SalesGoodsRejectInfoQuery salesGoodsRejectInfoQuery);

    SalesGoodsReject selectSalesGoodsRejectDetailAllBySaleName(String saleName);

    List<SalesGoodsRejectDetail> selectSalesGoodsRejectDetailBySaleName(String saleName);

    int insertSalesGoodsReturnDetail(SalesGoodsRejectDetail salesGoodsRejectDetail);

    int insertSalesGoodsReturn(SalesGoodsRejectInfo salesGoodsRejectInfo);

    int updateSalesGoodsReturnDetail(SalesGoodsRejectDetail salesGoodsRejectDetail);

    int updateSalesGoodsReturnDate(SalesGoodsRejectInfo salesGoodsRejectInfo);

    @Delete("delete from sales_goods_return where return_number=#{returnNumber}")
    int deleteSalesGoodsReturn(String returnNumber);

    @Delete("delete from sales_goods_return_details where id=#{id}")
    int deleteSalesGoodsReturnDetail(Long id);

    String getMaxBillingCode(@Param("code") String code);

    SalesGoodsReject getSalesGoodsRejectDetailByReturnNumber(String returnNumber);

    List<SalesGoodsRejectDetail> selectSalesGoodsRejectDetailByReturnNumber(String returnNumber);

    Double countReturnQuantity(String saleNumber);

    List<String> selectCustomerList();

    @Update("update sales_goods_return\n" +
            "set return_status = #{action},approve_time=#{approveTime},approve=#{approve}\n" +
            "where  return_number = #{returnNumber}")
    int updateSalesGoodsReturn(@Param("returnNumber") String returnNumber,
                               @Param("approveTime") Date approveTime,
                               @Param("approve") String approve,
                               @Param("action") String action);


    int updateSkStock(@Param("materialName") String materialName,
                      @Param("detailsType") String detailsType,
                      @Param("materialNumber") String materialNumber,
                      @Param("returnQuantity") BigDecimal returnQuantity);

    @Update("update sales_goods_return_details SET status=1 where id=#{id}")
    int updateStatus(Long id);

    @Select("SELECT SUM(quantity) FROM sales_goods_return_details " +
            "WHERE STATUS = 1 " +
            "AND material_number=#{materialNumber}" +
            "AND material_name=#{materialName}" +
            "AND details_type=#{detailsType}")
    Double countReturnQuantityDetail(
            @Param("materialNumber") String materialNumber,
            @Param("materialName") String materialName,
            @Param("detailsType") String detailsType
    );



    Map<String,Object> getMaterial(@Param("materialNumber") String materialNumber);
}
