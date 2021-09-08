package com.wsdm.controller.materiel;


import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.materiel.domain.SupplierContractVo;
import com.wsdm.materiel.service.SupplierContractManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 供应商合同Controller
 *
 * @author wanglei
 * @date 2021-04-25
 */
@RestController
@RequestMapping("/purchase/contract")
public class SupplierContractController extends BaseController {
    @Autowired
    private SupplierContractManageService supplierContractManageService;

    /**
     * 查询采购物料列表
     */
    @GetMapping("/listForPurchase")
    public TableDataInfo listForPurchase(@RequestParam Map<String, Object> sqlMap) {
        startPage();
        return getDataTable(supplierContractManageService.queryPurchaseMaterielList(sqlMap));
    }

    /**
     * 查询采购物料列表（自动采购更换供应商）
     */
    @GetMapping("/listForAutoPurchase")
    public AjaxResult listForAutoPurchase(@RequestParam Map<String, Object> sqlMap) {
        return AjaxResult.success(supplierContractManageService.queryListForAutoPurchase(sqlMap));
    }

    /**
     * 查询供应商合同列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:contract:list')")
    @GetMapping("/list")
    public TableDataInfo list(SupplierContractVo supplierContract) {
        startPage();
        List<SupplierContractVo> list = supplierContractManageService.selectSupplierContractList(supplierContract);
        return getDataTable(list);
    }

    /**
     * 获取供应商合同详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:contract:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(supplierContractManageService.selectSupplierContractById(id));
    }

    /**
     * 新增供应商合同
     */
    @PreAuthorize("@ss.hasPermi('purchase:contract:add')")
    @Log(title = "供应商合同", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SupplierContractVo supplierContract) throws Exception {
        return supplierContractManageService.insertSupplierContract(supplierContract);
    }

    /**
     * 审核供应商合同
     */
    @PreAuthorize("@ss.hasPermi('purchase:contract:approve')")
    @Log(title = "供应商合同", businessType = BusinessType.UPDATE)
    @PostMapping("/approve")
    public AjaxResult approve(@RequestParam("id") Long id, @RequestParam("action") String action) {
        return supplierContractManageService.updateApproveStatus(id, action);
    }

    /**
     * 修改供应商合同
     */
    @PreAuthorize("@ss.hasPermi('purchase:contract:edit')")
    @Log(title = "供应商合同", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody SupplierContractVo supplierContract) {
        return supplierContractManageService.updateSupplierContract(supplierContract);
    }

    /**
     * 删除供应商合同
     */
    @PreAuthorize("@ss.hasPermi('purchase:contract:remove')")
    @Log(title = "供应商合同", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return supplierContractManageService.deleteSupplierContractById(id);
    }

    /**
     * 导出供应商合同列表
     */
/*    @PreAuthorize("@ss.hasPermi('purchase:contract:export')")
    @Log(title = "供应商合同", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SupplierContract supplierContract) {
        List<SupplierContract> list = supplierContractManageService.selectSupplierContractList(supplierContract);
        ExcelUtil<SupplierContract> utils = new ExcelUtil<SupplierContract>(SupplierContract.class);
        return utils.exportExcel(list, "supplierContract");
    }*/
}
