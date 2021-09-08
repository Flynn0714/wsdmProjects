package com.wsdm.controller.stock;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.stock.domain.SalesDelivery;
import com.wsdm.stock.domain.SalesDeliveryRequest;
import com.wsdm.stock.service.ISalesDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 发货订单信息Controller
 *
 * @author wsdm
 * @date 2021-01-12
 */
@RestController
@RequestMapping("/stock/sales")
public class SalesDeliveryController extends BaseController {
    @Autowired
    private ISalesDeliveryService salesDeliveryService;

    /**
     * 查询发货订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('stock:sales:list')")
    @GetMapping("/list")
    public TableDataInfo list(SalesDeliveryRequest salesDelivery) {
        startPage();
        List<SalesDelivery> list = salesDeliveryService.selectSalesDeliveryList(salesDelivery);
        return getDataTable(list);
    }

    /**
     * 获取发货订单信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('stock:sales:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(salesDeliveryService.selectSalesDeliveryById(id));
    }

    /**
     * 新增发货订单信息
     */
    @PreAuthorize("@ss.hasPermi('stock:sales:add')")
    @Log(title = "发货订单信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SalesDelivery salesDelivery) {
        return toAjax(salesDeliveryService.insertSalesDelivery(salesDelivery));
    }

    /**
     * 修改发货订单信息
     */
    @PreAuthorize("@ss.hasPermi('stock:sales:edit')")
    @Log(title = "发货订单信息", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody SalesDelivery salesDelivery) {
        return toAjax(salesDeliveryService.updateSalesDelivery(salesDelivery));
    }

    @PreAuthorize("@ss.hasPermi('stock:material:approved')")
    @Log(title = "发货单信息", businessType = BusinessType.UPDATE)
    @PutMapping("/approved")
    public AjaxResult approved(@RequestBody SalesDelivery salesDelivery) {
        return salesDeliveryService.approved(salesDelivery);
    }

    /**
     * 删除发货订单信息
     */
    @PreAuthorize("@ss.hasPermi('stock:sales:remove')")
    @Log(title = "发货订单信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(salesDeliveryService.deleteSalesDeliveryByIds(ids));
    }

    /**
     * 导出发货订单信息列表

     @PreAuthorize("@ss.hasPermi('stock:sales:export')")
     @Log(title = "发货订单信息", businessType = BusinessType.EXPORT)
     @GetMapping("/export") public AjaxResult export(SalesDelivery salesDelivery) {
     List<SalesDelivery> list = salesDeliveryService.selectSalesDeliveryList(salesDelivery);
     ExcelUtil<SalesDelivery> util = new ExcelUtil<SalesDelivery>(SalesDelivery. class);
     return util.exportExcel(list, "sales");
     }*/

}
