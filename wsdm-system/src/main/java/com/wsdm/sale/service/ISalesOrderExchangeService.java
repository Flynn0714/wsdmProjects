package com.wsdm.sale.service;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.sale.domain.SalesOrderExchange;
import com.wsdm.sale.domain.SalesOrderExchangeDetails;
import com.wsdm.sale.domain.SalesOrderExchangeQuery;
import com.wsdm.sale.domain.SalesOrderExchangeSelect;

import java.util.List;
import java.util.Map;

/**
 * 销售换货Service接口
 *
 * @author 符方龙
 * @date 2021年7月13日23:40:04
 */
public interface ISalesOrderExchangeService {
    /*
    * 查询所有的换货单
    * */
    public List<SalesOrderExchange> queryExchangeList(SalesOrderExchangeQuery salesOrderExchangeQuery);

    /*
    * 查询到所有已发货和部分发货的销售单
    * */
    public List<SalesOrderExchangeSelect> queryDeliverySalesOrders(SalesOrderExchangeQuery salesOrderExchangeQuery);

    /*
    * 查询发货详细信息
    * */
    public Map getAddInfo(String salesNumber);

    /*
    * 新增销售换货
    * */
    public int insertSalesOrderExchange(SalesOrderExchange salesOrderExchange);

    /*
    * 修改销售换货
    * */
    public int updateSalesOrderExchange(SalesOrderExchange salesOrderExchange);

    /*
    * 换货单审核
    * */
    public int approve(String exchangeNumber,String action);

    /*
    * 查看换货单
    * */
    public List<SalesOrderExchangeDetails> getExchangeDetailsByExchangeNumber(String exchangeBunber);
}
