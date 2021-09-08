package com.wsdm.controller.sale;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.sale.domain.SalesOrderDelivery;
import com.wsdm.sale.domain.SalesOrderDeliveryDetails;
import com.wsdm.sale.domain.SalesOrderDeliveryInfo;
import com.wsdm.sale.domain.SalesOrderDeliveryQuery;
import com.wsdm.sale.service.ISalesOrderDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 销售单发货Controller
 *
 * @author wsdm
 * @date 2021-04-27
 */
@Validated
@RestController
@RequestMapping("/sales/delivery")
public class SalesOrderDeliveryController extends BaseController {
    @Autowired
    private ISalesOrderDeliveryService salesOrderDeliveryService;

    /**
     * 查询销售单发货列表
     */
    @PreAuthorize("@ss.hasPermi('sales:delivery:list')")
    @GetMapping("/list")
    public TableDataInfo list(SalesOrderDeliveryQuery salesOrderDeliveryQuery) {
        startPage();
        List<SalesOrderDeliveryInfo> list = salesOrderDeliveryService.selectSalesOrderDeliveryList(salesOrderDeliveryQuery);
        return getDataTable(list);
    }

    /**
     * 获取销售单发货详细信息
     */
    @PreAuthorize("@ss.hasPermi('sales:delivery:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(salesOrderDeliveryService.selectSalesOrderDeliveryById(id));
    }

    /**
     * 获取销售单发货详细信息
     */
    @PreAuthorize("@ss.hasPermi('sales:delivery:query')")
    @GetMapping(value = "/infoNumber")
    public AjaxResult getInfoNumber(@RequestParam("deliveryNumber") String deliveryNumber) {
        return AjaxResult.success(salesOrderDeliveryService.selectSalesOrderDeliveryById(deliveryNumber));
    }

    /**
     * 获取销售单发货详细信息
     */
    @GetMapping(value = "/add/info")
    public AjaxResult getAddInfo(@RequestParam("salesNumber") String salesNumber) {
        return AjaxResult.success(salesOrderDeliveryService.getAddInfo(salesNumber));
    }

    @GetMapping("/getSalesDeliveryOrder")
    public TableDataInfo getSalesDeliveryOrder(
            @RequestParam(value = "deliveryNumber", required = false) String deliveryNumber,
            @RequestParam(value = "customer", required = false) String customer) {
        startPage();
        List<Map> list = salesOrderDeliveryService.getSalesDeliveryOrder(deliveryNumber, customer);
        return getDataTable(list);
    }

    @GetMapping("/getDeliverDetailsByDeliveryNumber")
    public AjaxResult getDeliverDetailsByDeliveryNumber(@RequestParam("deliveryNumber") String deliveryNumber) {
        List<SalesOrderDeliveryDetails> list = salesOrderDeliveryService.getDeliverDetailsByDeliveryNumber(deliveryNumber);
        return AjaxResult.success(list);
    }

    /**
     * 新增销售单发货
     */
    @PreAuthorize("@ss.hasPermi('sales:delivery:add')")
    @Log(title = "销售单发货", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody @Valid SalesOrderDelivery salesOrderDelivery) {
        return salesOrderDeliveryService.insertSalesOrderDelivery(salesOrderDelivery);
    }

    /**
     * 修改销售单发货
     */
    @PreAuthorize("@ss.hasPermi('sales:delivery:edit')")
    @Log(title = "销售单发货", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody @Valid SalesOrderDelivery salesOrderDelivery) {
        return salesOrderDeliveryService.updateSalesOrderDelivery(salesOrderDelivery);
    }

    /**
     * 删除销售单发货
     */
    @PreAuthorize("@ss.hasPermi('sales:delivery:remove')")
    @Log(title = "销售单发货", businessType = BusinessType.DELETE)
    @PostMapping("/delete/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return salesOrderDeliveryService.deleteSalesOrderDeliveryById(id);
    }

    /**
     * 销售单发货审核
     */
    @PreAuthorize("@ss.hasPermi('sale:delivery:approve')")
    @Log(title = "销售单发货", businessType = BusinessType.APPROVE)
    @PostMapping("/approve")
    public AjaxResult approve(Long id, String action) {
        return salesOrderDeliveryService.approve(id, action);
    }
}
