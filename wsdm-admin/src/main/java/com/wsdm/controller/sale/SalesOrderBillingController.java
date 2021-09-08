package com.wsdm.controller.sale;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.sale.domain.SalesOrderBilling;
import com.wsdm.sale.domain.SalesOrderBillingQuery;
import com.wsdm.sale.domain.SalesOrderGatheringDetails;
import com.wsdm.sale.service.ISalesOrderBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 销售单开票Controller
 *
 * @author wsdm
 * @date 2021-04-27
 */
@Validated
@RestController
@RequestMapping("/sales/billing")
public class SalesOrderBillingController extends BaseController {
    @Autowired
    private ISalesOrderBillingService salesOrderBillingService;

    /**
     * 查询销售单开票列表
     */
    @PreAuthorize("@ss.hasPermi('sales:billing:list')")
    @GetMapping("/list")
    public TableDataInfo list(SalesOrderBillingQuery salesOrderBilling) {
        startPage();
        List<SalesOrderBilling> list = salesOrderBillingService.selectSalesOrderBillingList(salesOrderBilling);
        return getDataTable(list);
    }
    
    /**
     * 
     * 开票单--新增--收款单详情
     * @param gatheringNumber 
     * @author wangr 
     * @date 2021/6/15 10:45
     * @return com.wsdm.common.core.domain.AjaxResult
     */
    @GetMapping("/getGatheringDetailsList")
    public AjaxResult getGatheringDetailsList(@RequestParam("gatheringNumber") String gatheringNumber) {
        List<SalesOrderGatheringDetails> list = salesOrderBillingService.getGatheringDetailsList(gatheringNumber);
        return AjaxResult.success(list);
    }

    /**
     * 获取销售单开票详细信息
     */
    @PreAuthorize("@ss.hasPermi('sales:billing:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(salesOrderBillingService.selectSalesOrderBillingById(id));
    }

    /**
     * 新增销售单开票
     */
    @PreAuthorize("@ss.hasPermi('sales:billing:add')")
    @Log(title = "销售单开票", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody @Valid SalesOrderBilling salesOrderBilling) {
        return toAjax(salesOrderBillingService.insertSalesOrderBilling(salesOrderBilling));
    }

    /**
     * 修改销售单开票
     */
    @PreAuthorize("@ss.hasPermi('sales:billing:edit')")
    @Log(title = "销售单开票", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody @Valid  SalesOrderBilling salesOrderBilling) {
        return toAjax(salesOrderBillingService.updateSalesOrderBilling(salesOrderBilling));
    }

    /**
     * 删除销售单开票
     */
    @PreAuthorize("@ss.hasPermi('sales:billing:remove')")
    @Log(title = "销售单开票", businessType = BusinessType.DELETE)
    @PostMapping("/delete/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return toAjax(salesOrderBillingService.deleteSalesOrderBillingById(id));
    }

    /**
     * 销售单开票审核
     */
    @PreAuthorize("@ss.hasPermi('sale:billing:approve')")
    @Log(title = "销售单开票", businessType = BusinessType.APPROVE)
    @PostMapping("/approve")
    public AjaxResult approve(Long id, String action) {
        return salesOrderBillingService.approve(id,action);
    }
}
