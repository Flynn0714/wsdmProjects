package com.wsdm.controller.materiel;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.materiel.domain.AutomaticPurchaseInfo;
import com.wsdm.materiel.domain.PurchaseOrderInfo;
import com.wsdm.materiel.domain.PurchaseOrderInfoVo;
import com.wsdm.materiel.service.IPurchaseOrderManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 物料采购订单信息Controller
 *
 * @author wsdm
 * @date 2020-11-26
 */
@RestController
@RequestMapping("/materiel/purchase/manage")
public class PurchaseOrderManageController extends BaseController {
    @Autowired
    private IPurchaseOrderManageService purchaseOrderManageService;

    /**
     * 查询物料采购订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(PurchaseOrderInfoVo purchaseOrderInfo) {
        startPage();
        List<PurchaseOrderInfo> list = purchaseOrderManageService.selectPurchaseOrderInfoList(purchaseOrderInfo);
        return getDataTable(list);
    }

    @GetMapping("/listForReceive")
    public TableDataInfo listForReceive(PurchaseOrderInfo purchaseOrderInfo) {
        startPage();
        List<PurchaseOrderInfoVo> list = purchaseOrderManageService.selectPurchaseListForReceive(purchaseOrderInfo);
        return getDataTable(list);
    }

    /*    *//**
     * 导出物料采购订单信息列表
     *//*
    @PreAuthorize("@ss.hasPermi('purchase:manage:export')")
    @Log(title = "物料采购订单信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PurchaseOrderInfo PurchaseOrderInfo) {
        List<PurchaseOrderInfo> list = purchaseOrderManageService.selectPurchaseOrderInfoList(PurchaseOrderInfo);
        ExcelUtil<PurchaseOrderInfo> util = new ExcelUtil<PurchaseOrderInfo>(PurchaseOrderInfo.class);
        return util.exportExcel(list, "info");
    }*/

    /**
     * 获取自动采购信息
     */
    @PostMapping(value = "/automaticPurchaseInfo")
    public AjaxResult automaticPurchaseInfo(@RequestBody List<String> salesNumberList) {
        return AjaxResult.success(purchaseOrderManageService.automaticPurchaseInfo(salesNumberList));
    }

    /**
     * 获取物料采购订单信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:manage:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(purchaseOrderManageService.selectPurchaseOrderInfoById(id));
    }

    /**
     *
     * 批次信息
     * @param purchaseOrderInfo
     * @author wangr
     * @date 2021/7/7 10:15
     * @return com.wsdm.common.core.domain.AjaxResult
     */
    @GetMapping("/batch_list")
    public AjaxResult batchList(String batchNumber) {
        PurchaseOrderInfoVo purchaseOrderInfo = new PurchaseOrderInfoVo();
        purchaseOrderInfo.setBatchNumber(batchNumber);
        List<PurchaseOrderInfo> list = purchaseOrderManageService.selectPurchaseOrderInfoList(purchaseOrderInfo);
        return AjaxResult.success(list);
    }

    /**
     * 新增物料采购订单信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:manage:add')")
    @Log(title = "物料采购订单信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PurchaseOrderInfoVo PurchaseOrderInfo) {
        return purchaseOrderManageService.insertPurchaseOrderInfo(PurchaseOrderInfo);
    }

    /**
     * 新增物料采购订单信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:manage:automaticAdd')")
    @Log(title = "物料采购订单信息", businessType = BusinessType.INSERT)
    @PostMapping("/automaticAdd")
    public AjaxResult automaticAdd(@RequestBody List<AutomaticPurchaseInfo> automaticPurchaseInfos) {
        return purchaseOrderManageService.insertAutomaticPurchaseInfo(automaticPurchaseInfos);
    }


    /**
     * 修改物料采购订单信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:manage:edit')")
    @Log(title = "物料采购订单信息", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody PurchaseOrderInfoVo purchaseOrderInfoVo) {
        return purchaseOrderManageService.updatePurchaseOrderInfo(purchaseOrderInfoVo);
    }

    /**
     * 审核物料采购订单
     */
    @PreAuthorize("@ss.hasPermi('purchase:manage:approve')")
    @Log(title = "物料采购订单信息", businessType = BusinessType.UPDATE)
    @PostMapping("/approve")
    public AjaxResult approve(@RequestParam("id") Long id, @RequestParam("action") String action) {
        return purchaseOrderManageService.updateApproveStatus(id, action);
    }



    /**
     * 审核物料采购订单
     */
    @GetMapping("/oneTouch")
    public AjaxResult oneTouch() {
        //一键生成采购单
        //查询已审核未结束的销售单，已发货的去除已发数量
        //返回产品列表：成品，半成品 （MAP）
        purchaseOrderManageService.selectMobilePurchaseOneTouch();
        return new AjaxResult(200,"生成完毕，请刷新页面！");
    }





}
