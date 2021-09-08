package com.wsdm.controller.sale;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.sale.domain.SalesOrderGathering;
import com.wsdm.sale.domain.SalesOrderGatheringQuery;
import com.wsdm.sale.service.ISalesOrderGatheringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


/**
 * 销售单收款Controller
 *
 * @author wsdm
 * @date 2021-04-27
 */
@Validated
@RestController
@RequestMapping("/sales/gathering")
public class SalesOrderGatheringController extends BaseController {
    @Autowired
    private ISalesOrderGatheringService salesOrderGatheringService;

    /**
     * 查询销售单收款列表
     */
    @PreAuthorize("@ss.hasPermi('sales:gathering:list')")
    @GetMapping("/list")
    public TableDataInfo list(SalesOrderGatheringQuery salesOrderGathering) {
        startPage();
        List<SalesOrderGathering> list = salesOrderGatheringService.selectSalesOrderGatheringList(salesOrderGathering);
        return getDataTable(list);
    }

    @GetMapping("/getSalesGatheringOrder")
    public TableDataInfo getSalesGatheringOrder(
            @RequestParam(value = "salesNumber", required = false) String salesNumber,
            @RequestParam(value = "customer", required = false) String customer) {
        startPage();
        List<Map> list = salesOrderGatheringService.getSalesGatheringOrder(salesNumber, customer);
        return getDataTable(list);
    }

    /**
     * 获取销售单收款详细信息
     */
    @PreAuthorize("@ss.hasPermi('sales:gathering:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(salesOrderGatheringService.selectSalesOrderGatheringById(id));
    }

    /**
     * 新增销售单收款
     */
    @PreAuthorize("@ss.hasPermi('sales:gathering:add')")
    @Log(title = "销售单收款", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody @Valid SalesOrderGathering salesOrderGathering) {
        return salesOrderGatheringService.insertSalesOrderGathering(salesOrderGathering);
    }

    /**
     * 修改销售单收款
     */
    @PreAuthorize("@ss.hasPermi('sales:gathering:edit')")
    @Log(title = "销售单收款", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody @Valid SalesOrderGathering salesOrderGathering) {
        return salesOrderGatheringService.updateSalesOrderGathering(salesOrderGathering);
    }

    /**
     * 删除销售单收款
     */
    @PreAuthorize("@ss.hasPermi('sales:gathering:remove')")
    @Log(title = "销售单收款", businessType = BusinessType.DELETE)
    @PostMapping("/delete/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return toAjax(salesOrderGatheringService.deleteSalesOrderGatheringById(id));
    }

    /**
     * 销售单收款审核
     */
    @PreAuthorize("@ss.hasPermi('sale:gathering:approve')")
    @Log(title = "销售单收款", businessType = BusinessType.APPROVE)
    @PostMapping("/approve")
    public AjaxResult approve(Long id, String action) {
        return salesOrderGatheringService.approve(id, action);
    }
}
