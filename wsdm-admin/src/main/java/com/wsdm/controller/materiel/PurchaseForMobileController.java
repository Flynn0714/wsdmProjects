package com.wsdm.controller.materiel;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.materiel.service.impl.PurchaseOrderManageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 物料采购移动端Controller
 *
 * @author wsdm
 * @date 2021-06-22
 */
@RestController
@RequestMapping("/materiel/purchase/mobile")
public class PurchaseForMobileController extends BaseController {

    @Autowired
    private PurchaseOrderManageServiceImpl purchaseManageService;

    /**
     * 查询移动端采购审核列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:mobile:approveList')")
    @GetMapping("/approve/list")
    public TableDataInfo approveList(@RequestParam("orderType") String orderType, @RequestParam("approveStatus") String approveStatus) {
        startPage();
        return getDataTable(purchaseManageService.selectMobilePurchaseApproveList(orderType, approveStatus));
    }

    /**
     * 采购移动端审核
     */
    @PreAuthorize("@ss.hasPermi('purchase:mobile:approve')")
    @Log(title = "物料采购订单信息", businessType = BusinessType.UPDATE)
    @PostMapping("/approve")
    public AjaxResult approve(@RequestParam("id") Long id, @RequestParam("orderType") String orderType, @RequestParam("action") String action, @RequestParam("action") String approvedType) {
        return purchaseManageService.updateMobilePurchaseApproveStatus(id, orderType, action, approvedType);
    }


    /**
     * 获取物料采购订单信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:mobile:query')")
    @GetMapping(value = "/approve/info")
    public AjaxResult getApproveInfo(@RequestParam("id") Long id, @RequestParam("orderType") String orderType) {
        return AjaxResult.success(purchaseManageService.selectMobilePurchaseApproveInfoById(id, orderType));
    }

}
