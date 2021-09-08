package com.wsdm.controller.sale;

import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.sale.domain.SalesOrderExchange;
import com.wsdm.sale.domain.SalesOrderExchangeDetails;
import com.wsdm.sale.domain.SalesOrderExchangeQuery;
import com.wsdm.sale.domain.SalesOrderExchangeSelect;
import com.wsdm.sale.service.ISalesOrderExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 销售换货Controller
 *
 * @author 符方龙
 * @date 2021年7月13日23:11:24
 */

@RestController
@RequestMapping("/sale/order/exchange")
public class SalesOrderExchangeController extends BaseController {

    @Autowired
    private ISalesOrderExchangeService salesOrderExchangeService;

    /*
    * 查询换货单列表
    * return List<SalesOrderExchange>
    * */
    @GetMapping("/soelist")
    public TableDataInfo queryExchangeList(SalesOrderExchangeQuery salesOrderExchangeQuery){
//        System.out.println(salesOrderExchangeQuery);
        startPage();
        List<SalesOrderExchange> exchangeList = salesOrderExchangeService.queryExchangeList(salesOrderExchangeQuery);
        return getDataTable(exchangeList);
    }

    /*查询已发货或者部分发货的销售单
    *
    * return List<SalesOrderManesge >
    * */
    @GetMapping("/somlist")
    public TableDataInfo queryDeliverySalesOrders(SalesOrderExchangeQuery salesOrderExchangeQuery){
        startPage();
        List<SalesOrderExchangeSelect> salesOrderManages = salesOrderExchangeService.queryDeliverySalesOrders(salesOrderExchangeQuery);
        return getDataTable(salesOrderManages);
    }

    /*
    * 获取销售单发货详细信息
    * */
    @GetMapping(value = "/add/info")
    public AjaxResult getAddInfo(@RequestParam("salesNumber") String salesNumber){
//        System.out.println(">>>>>>>>>>>>>>>>>>>>"+salesNumber);
        return AjaxResult.success(salesOrderExchangeService.getAddInfo(salesNumber));
    }

    /*
    *
    * 查看换货单
    * */
    @GetMapping("/check")
    public AjaxResult getExchangeDetailsByExchangeNumber(@RequestParam("exchangeNumber") String exchangeNumber){
        List<SalesOrderExchangeDetails> list = salesOrderExchangeService.getExchangeDetailsByExchangeNumber(exchangeNumber);
        return AjaxResult.success(list);
    }
    /*
     *
     * 查看换货单
     * */
    @GetMapping("/checkTwo")
    public AjaxResult getExchangeDetailsById(@RequestParam("exchangeNumber") String exchangeNumber){
        SalesOrderExchange salesOrderManage = salesOrderExchangeService.queryExchangeList(new SalesOrderExchangeQuery(exchangeNumber)).get(0);
        salesOrderManage.setExchangeDetailsList( salesOrderExchangeService.getExchangeDetailsByExchangeNumber(exchangeNumber));
        return AjaxResult.success(salesOrderManage);
    }

    /*
    * 新增销售换货
    * */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody  SalesOrderExchange salesOrderExchange){
        return toAjax(salesOrderExchangeService.insertSalesOrderExchange(salesOrderExchange));
    }

    /*
    * 修改销售换货
    * */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody  SalesOrderExchange salesOrderExchange) {
        System.out.println("+++++++++++++++++++++++" + salesOrderExchange);
        return toAjax(salesOrderExchangeService.updateSalesOrderExchange(salesOrderExchange));
    }


    /*
     * 换货单审核
     * */
    @PostMapping("/approve")
    public AjaxResult approve(String exchangeNumber,String action){
        return toAjax(salesOrderExchangeService.approve(exchangeNumber,action));
    }
}
