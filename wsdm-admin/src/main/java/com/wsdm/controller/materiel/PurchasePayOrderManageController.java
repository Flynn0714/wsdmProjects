package com.wsdm.controller.materiel;

import com.github.pagehelper.PageInfo;
import com.wsdm.common.annotation.Log;
import com.wsdm.common.constant.HttpStatus;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.materiel.domain.*;
import com.wsdm.materiel.service.PurchasePayOrderManageService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * 物料采购付款单信息Controller
 *
 * @author wanglei
 * @date 2021-06-26
 */
@RestController
@RequestMapping("/materiel/payment")
public class PurchasePayOrderManageController extends BaseController {
    @Autowired
    private PurchasePayOrderManageService payOrderManageService;

    /**
     * 查询物料采购付款单信息列表
     */
    @PreAuthorize("@ss.hasPermi('payment:manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(PurchasePayOrderInfo purchasePayOrderInfo) {
        startPage();
        List<PurchasePayOrderInfo> list = payOrderManageService.selectPurchasePayOrderInfoList(purchasePayOrderInfo);
        return getDataTable(list);
    }

    /**
     * 获取采购交易记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('payment:manage:query')")
    @GetMapping(value = "/info")
    public AjaxResult getInfo(String dealType, String dealNumber) {
        return payOrderManageService.selectPurchasePayDetailInfo(dealType, dealNumber);
    }

    /**
     * 导出物料采购付款记录列表
     */
    @PreAuthorize("@ss.hasPermi('payment:payRecord:export')")
    @Log(title = "物料采购付款单信息", businessType = BusinessType.EXPORT)
    @GetMapping("/exportPayRecord")
    public void exportPayRecord(PurchasePayOrderInfoVo purchasePayOrderInfo, HttpServletResponse response) {
        List<PurchasePayOrderDetail> list = payOrderManageService.selectPurchasePayRecordList(purchasePayOrderInfo);
        ExcelUtil<PurchasePayOrderDetail> util = new ExcelUtil<PurchasePayOrderDetail>(PurchasePayOrderDetail.class);
        util.exportExcel(list, "采购付款记录", response);
    }

    /**
     * 新增物料采购付款记录
     */
    @PreAuthorize("@ss.hasPermi('payment:payRecord:add')")
    @Log(title = "采购应付货款记录新增", businessType = BusinessType.INSERT)
    @PostMapping("/addPayRecord")
    public AjaxResult addPayRecord(@RequestBody PurchasePayOrderInfoVo purchasePayOrderInfo) {
        return payOrderManageService.insertPurchasePayRecord(purchasePayOrderInfo);
    }

    /**
     * 查询物料采购付款记录列表
     */
    @PreAuthorize("@ss.hasPermi('payment:payRecord:list')")
    @GetMapping("/payRecordList")
    public TableDataInfo payRecordList(PurchasePayOrderInfoVo purchasePayOrderInfo) {
        startPage();
        List<PurchasePayOrderDetail> list = payOrderManageService.selectPurchasePayRecordList(purchasePayOrderInfo);
        return getDataTable(list);
    }

    /**
     * 查询物料采购交易明细列表
     */
    @PreAuthorize("@ss.hasPermi('payment:dealDetail:list')")
    @GetMapping("/dealDetailList")
    public TableDataInfo dealDetailList(PurchasePayOrderInfoVo purchasePayOrderInfo) {
        startPage();
        List<PurchaseDealDetail> list = payOrderManageService.selectPurchaseDealDetailList(purchasePayOrderInfo);
        PurchaseDealTableDataInfo rspData = payOrderManageService.getTotalDealNumAndAmount(purchasePayOrderInfo);
        if (rspData == null) {
            rspData = new PurchaseDealTableDataInfo();
        }
        BigDecimal totalDealAmount = rspData.getTotalDealAmount() == null ? BigDecimal.ZERO : rspData.getTotalDealAmount();
        BigDecimal totalDealNum = rspData.getTotalDealNum() == null ? BigDecimal.ZERO : rspData.getTotalDealNum();
        rspData.setTotalDealAmount(totalDealAmount);
        rspData.setTotalDealNum(totalDealNum);
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 查询物料采购交易列表
     */
    @PreAuthorize("@ss.hasPermi('payment:dealRecord:list')")
    @GetMapping("/dealRecordList")
    public TableDataInfo dealRecord(PurchasePayOrderInfoVo purchasePayOrderInfo) {
        List<PurchaseDealDetail> list = payOrderManageService.selectPurchaseDealRecordList(purchasePayOrderInfo);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 导出物料采购交易明细列表
     */
    @PreAuthorize("@ss.hasPermi('payment:dealDetail:export')")
    @Log(title = "物料采购付款单信息", businessType = BusinessType.EXPORT)
    @GetMapping("/exportDealDetail")
    public void exportDealDetail(PurchasePayOrderInfoVo purchasePayOrderInfo, HttpServletResponse response) {
        List<PurchaseDealDetail> list = payOrderManageService.selectPurchaseDealDetailList(purchasePayOrderInfo);
        ExcelUtil<PurchaseDealDetail> util = new ExcelUtil<PurchaseDealDetail>(PurchaseDealDetail.class);
        util.exportExcel(list, "采购交易明细", response);
    }

    /**
     * 新增物料采购付款单信息
     */
    @PreAuthorize("@ss.hasPermi('payment:manage:add')")
    @Log(title = "物料采购付款单信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PurchasePayOrderInfoVo purchasePayOrderInfo) {
        return toAjax(payOrderManageService.insertPurchasePayOrderInfo(purchasePayOrderInfo));
    }

    /**
     * 修改物料采购付款单信息
     */
    //@PreAuthorize("@ss.hasPermi('payment:manage:edit')")
    @Log(title = "物料采购付款单信息", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody PurchasePayOrderInfo purchasePayOrderInfo) {
        return toAjax(payOrderManageService.updatePurchasePayOrderInfo(purchasePayOrderInfo));
    }


    /**
     * 删除物料采购付款单信息
     */
    @PreAuthorize("@ss.hasPermi('payment:manage:remove')")
    @Log(title = "物料采购付款单信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(payOrderManageService.deletePurchasePayOrderInfoByIds(ids));
    }
}
