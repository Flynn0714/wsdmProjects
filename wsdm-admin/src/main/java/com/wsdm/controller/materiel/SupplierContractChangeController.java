package com.wsdm.controller.materiel;


import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.enums.ResultEnums;
import com.wsdm.materiel.domain.SupplierContractChange;
import com.wsdm.materiel.domain.SupplierContractChangeVo;
import com.wsdm.materiel.service.SupplierContractChangeManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 供应商合同变更Controller
 *
 * @author wanglei
 * @date 2021-04-29
 */
@RestController
@RequestMapping("/purchase/contractChange")
public class SupplierContractChangeController extends BaseController {
    @Autowired
    private SupplierContractChangeManageService supplierContractChangeManageService;

    /**
     * 查询供应商合同变更列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:contractChange:list')")
    @GetMapping("/list")
    public TableDataInfo list(SupplierContractChange supplierContractChange) {
        startPage();
        List<SupplierContractChange> list = supplierContractChangeManageService.selectSupplierContractChangeList(supplierContractChange);
        return getDataTable(list);
    }

    /**
     * 获取供应商合同变更详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:contractChange:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        if (null == id) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        return AjaxResult.success(supplierContractChangeManageService.selectSupplierContractChangeById(id));
    }

    /**
     * 新增供应商合同变更
     */
    @PreAuthorize("@ss.hasPermi('purchase:contractChange:add')")
    @Log(title = "供应商合同变更", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SupplierContractChangeVo supplierContractChange) {
        return supplierContractChangeManageService.insertSupplierContractChange(supplierContractChange);
    }

    /**
     * 修改供应商合同变更
     */
    @PreAuthorize("@ss.hasPermi('purchase:contractChange:edit')")
    @Log(title = "供应商合同变更", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody SupplierContractChangeVo supplierContractChange) {
        return supplierContractChangeManageService.updateSupplierContractChange(supplierContractChange);
    }

    /**
     * 审核供应商合同变更
     */
    @PreAuthorize("@ss.hasPermi('purchase:contractChange:approve')")
    @Log(title = "供应商合同变更", businessType = BusinessType.UPDATE)
    @PostMapping("/approve")
    public AjaxResult approve(@RequestParam("id") Long id, @RequestParam("action") String action) {
        return supplierContractChangeManageService.updateApproveStatus(id, action);
    }

    /**
     * 删除供应商合同变更
     */
    @PreAuthorize("@ss.hasPermi('purchase:contractChange:remove')")
    @Log(title = "供应商合同变更", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return supplierContractChangeManageService.deleteSupplierContractChangeById(id);
    }

    /*    *//**
     * 导出供应商合同变更列表
     *//*
        @PreAuthorize("@ss.hasPermi('purchase:contractChange:export')")
        @Log(title = "供应商合同变更", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SupplierContractChange supplierContractChange) {
        List<SupplierContractChange> list = supplierContractChangeManageService.selectSupplierContractChangeList(supplierContractChange);
        ExcelUtil<SupplierContractChange> utils = new ExcelUtil<SupplierContractChange>(SupplierContractChange.class);
        return utils.exportExcel(list, "supplierContractChange");
    }*/
}
