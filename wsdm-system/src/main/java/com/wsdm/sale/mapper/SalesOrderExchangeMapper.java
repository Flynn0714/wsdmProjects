package com.wsdm.sale.mapper;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.sale.domain.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SalesOrderExchangeMapper {

    /*
    * 查询所有换货单
    * */
    public List<SalesOrderExchange> queryExchangeList(SalesOrderExchangeQuery salesOrderExchangeQuery);

    /*
     * 根据换货单号查询换货单
     * */
    public SalesOrderExchange getExchange(@Param("exchangeNumber") String exchangeNumber);

    /*
    * 查询所有已发货和部分发货的销售单
    * */
    public List<SalesOrderExchangeSelect> queryDeliverySalesOrders(SalesOrderExchangeQuery salesOrderExchangeQuery);

    /*
    * 获取销售单发货详细信息
    * */
    public Map getAddInfo(String salesNumber);

    /*
    * 添加时获取详情
    * */
    public List<SalesOrderExchangeDetails> getDetailsList(String SalesNumber);

    /*
    * 审核时获取换货详情
    * */
    public List<SalesOrderExchangeDetail> queryexchangeDetailsList(String exchangeNumber);

    /*
    * 新增换货单
    * */
    public int insertSalesOrderExchange(SalesOrderExchange salesOrderExchange);
    /*
    *
    * */
    public int insertSalesOrderExchangeDetails(SalesOrderExchangeDetails salesOrderExchangeDetails);

    /*
    * 修改换货单
    * */
    public int updateExchangeDetailsBatch(List<SalesOrderExchangeDetails> exchangeDetailsList);

    public int updateSalesOrderExchange(SalesOrderExchange salesOrderExchange);

    /*
    * 更新销售信息
    * */
    public AjaxResult updateExchangeQuantityBatch(List<SalesOrderExchangeDetail> details);

    /*
    * 审核换货单
    * */
    @Update("    update sales_order_exchange\n" +
            "    set status = #{action},approve_time=#{approveTime},approver=#{approver}\n" +
            "    where  exchange_number = #{exchangeNumber}")
    public int approve(@Param("exchangeNumber") String exchangeNumber,
                       @Param("approver")  String approver,
                       @Param("approveTime") Date approveTime,
                       @Param("action") String action);

    /*
    * 查看换货单
    * */
    public List<SalesOrderExchangeDetails> getExchangeDetailsByExchangeNumber(String exchangeBunber);

    /*
    * 最大换货编号
    *
    * */
    public String getMaxExchangeCode(String code);
}
