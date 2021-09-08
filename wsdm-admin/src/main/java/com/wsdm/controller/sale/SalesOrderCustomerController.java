package com.wsdm.controller.sale;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.sale.domain.SalesOrderCustomer;
import com.wsdm.sale.service.ISalesOrderCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户管理Controller
 *
 * @author wsdm
 * @date 2020-11-06
 */
@RestController
@RequestMapping("/sale/order/customer")
public class SalesOrderCustomerController extends BaseController {
    @Autowired
    private ISalesOrderCustomerService salesOrderCustomerService;

    /**
     * 查询销售列表
     */
    @PreAuthorize("@ss.hasPermi('order:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(SalesOrderCustomer salesOrderCustomer) {
        startPage();
        List<SalesOrderCustomer> list = salesOrderCustomerService.selectSalesOrderCustomerList(salesOrderCustomer);
        return getDataTable(list);
    }


    /**
     * 查询客户下拉列表
     * @param salesOrderCustomer
     * @return
     */
    @GetMapping("/queryCustomerList")
    public AjaxResult queryCustomerList(SalesOrderCustomer salesOrderCustomer) {
        salesOrderCustomer.setStatus("0");
        return AjaxResult.success(salesOrderCustomerService.selectSalesOrderCustomerList(salesOrderCustomer));
    }

    /**
     * 获取客户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:customer:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(salesOrderCustomerService.selectSalesOrderCustomerById(id));
    }

    /**
     * 新增客户管理
     */
    @PreAuthorize("@ss.hasPermi('order:customer:add')")
    @Log(title = "客户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SalesOrderCustomer salesOrderCustomer) throws Exception {
        return toAjax(salesOrderCustomerService.insertSalesOrderCustomer(salesOrderCustomer));
    }

    /**
     * 修改客户管理
     */
    @PreAuthorize("@ss.hasPermi('order:customer:edit')")
    @Log(title = "客户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody SalesOrderCustomer salesOrderCustomer) {
        return toAjax(salesOrderCustomerService.updateSalesOrderCustomer(salesOrderCustomer));
    }

    /**
     * 删除客户管理
     */
    @PreAuthorize("@ss.hasPermi('order:customer:remove')")
    @Log(title = "客户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(salesOrderCustomerService.deleteSalesOrderCustomerByIds(ids));
    }

    /**
     * 导出客户管理列表

     @PreAuthorize("@ss.hasPermi('order:customer:export')")
     @Log(title = "客户管理", businessType = BusinessType.EXPORT)
     @GetMapping("/export") public AjaxResult export(SalesOrderCustomer salesOrderCustomer) {
     List<SalesOrderCustomer> list = salesOrderCustomerService.selectSalesOrderCustomerList(salesOrderCustomer);
     ExcelUtil<SalesOrderCustomer> util = new ExcelUtil<SalesOrderCustomer>(SalesOrderCustomer.class);
     return util.exportExcel(list, "customer");
     }*/
}
