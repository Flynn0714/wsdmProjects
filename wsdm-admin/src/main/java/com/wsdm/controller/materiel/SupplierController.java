package com.wsdm.controller.materiel;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.materiel.domain.Supplier;
import com.wsdm.materiel.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 供应商Controller
 *
 * @author wsdm
 * @date 2020-11-06
 */
@RestController
@RequestMapping("/materiel/purchase/supplier")
public class SupplierController extends BaseController {
    @Autowired
    private ISupplierService supplierService;

    /**
     * 查询供应商列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:list')")
    @GetMapping("/list")
    public TableDataInfo list(Supplier supplier) {
        startPage();
        List<Supplier> list = supplierService.selectSupplierList(supplier);
        return getDataTable(list);
    }

    /**
     * 查询供应商列表-下拉列表用
     */
    @GetMapping("/querySupplierList")
    public AjaxResult querySupplierList(Supplier supplier) {
        supplier.setStatus("0");
        return AjaxResult.success(supplierService.selectSupplierList(supplier));
    }

    /**
     * 获取供应商详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(supplierService.selectSupplierById(id));
    }

    /**
     * 新增供应商
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:add')")
    @Log(title = "供应商", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Supplier supplier) throws Exception {
        return toAjax(supplierService.insertSupplier(supplier));
    }

    /**
     * 修改供应商
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:edit')")
    @Log(title = "供应商", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody Supplier supplier) {
        return toAjax(supplierService.updateSupplier(supplier));
    }

    /**
     * 删除供应商
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:remove')")
    @Log(title = "供应商", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return toAjax(supplierService.deleteSupplierById(id));
    }

    /**
     * 恢复供应商
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:recovery')")
    @Log(title = "供应商", businessType = BusinessType.UPDATE)
    @PutMapping("/recovery/{id}")
    public AjaxResult recovery(@PathVariable Long id) {
        return toAjax(supplierService.recovery(id));
    }


    /**
     * 导出供应商列表

     @PreAuthorize("@ss.hasPermi('purchase:supplier:export')")
     @Log(title = "供应商", businessType = BusinessType.EXPORT)
     @GetMapping("/export") public AjaxResult export(Supplier supplier) {
     List<Supplier> list = supplierService.selectSupplierList(supplier);
     ExcelUtil<Supplier> util = new ExcelUtil<Supplier>(Supplier.class);
     return util.exportExcel(list, "supplier");
     }*/
}
