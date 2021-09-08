package com.wsdm.controller.sale;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.sale.domain.SalesOrderManage;
import com.wsdm.sale.domain.SalesOrderManageInfo;
import com.wsdm.sale.domain.SalesOrderManageRequest;
import com.wsdm.sale.service.ISalesOrderManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 销售订单Controller
 *
 * @author wsdm
 * @date 2020-11-06
 */
@Validated
@RestController
@RequestMapping("/sale/order/manage")
public class SalesOrderManageController extends BaseController {
    @Autowired
    private ISalesOrderManageService salesOrderManageService;

    /**
     * 查询销售订单列表
     */
    @PreAuthorize("@ss.hasPermi('sale:manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(SalesOrderManageRequest salesOrderManage) {
        startPage();
        List<SalesOrderManageInfo> list = salesOrderManageService.selectSalesOrderManageList(salesOrderManage);
        return getDataTable(list);
    }

    /**
     * 查询需要发货的销售单
     */
    @GetMapping("/getSalesOrderByDelivery")
    public TableDataInfo getSalesOrderByDelivery(SalesOrderManageRequest salesOrderManage) {
        startPage();
        List<Map> list = salesOrderManageService.getSalesOrderByDelivery(salesOrderManage);
        return getDataTable(list);
    }

    /**
     * 获取销售订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('sale:manage:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(salesOrderManageService.selectSalesOrderManageById(id));
    }

    /**
     * 自动采购--销售单查询展示
     */
    @GetMapping(value = "/salesOrderAutomaticPurchase")
    public AjaxResult salesOrderAutomaticPurchase() {
        return AjaxResult.success(salesOrderManageService.salesOrderAutomaticPurchase());
    }

    /**
     * 新增销售订单
     */
    @PreAuthorize("@ss.hasPermi('sale:manage:add')")
    @Log(title = "销售订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody @Valid SalesOrderManage salesOrderManage) {
        return salesOrderManageService.insertSalesOrderManage(salesOrderManage);
    }

    /**
     * 修改销售订单
     */
    @PreAuthorize("@ss.hasPermi('sale:manage:edit')")
    @Log(title = "销售订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody @Valid SalesOrderManage salesOrderManage) {
        return salesOrderManageService.updateSalesOrderManage(salesOrderManage);
    }

    /**
     * 删除销售订单
     */
    @PreAuthorize("@ss.hasPermi('sale:manage:remove')")
    @Log(title = "销售订单", businessType = BusinessType.DELETE)
    @PostMapping("/remove/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return salesOrderManageService.deleteSalesOrderManageById(id);
    }

    /**
     * 销售订单审核
     */
    @PreAuthorize("@ss.hasPermi('sale:manage:approve')")
    @Log(title = "销售订单", businessType = BusinessType.APPROVE)
    @PostMapping("/approve")
    public AjaxResult approve(Long id, String action) {
        return salesOrderManageService.approve(id, action);
    }
}
