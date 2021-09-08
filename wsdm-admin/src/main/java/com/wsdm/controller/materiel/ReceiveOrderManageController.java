package com.wsdm.controller.materiel;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.materiel.domain.ReceiveOrderInfo;
import com.wsdm.materiel.domain.ReceiveOrderInfoVo;
import com.wsdm.materiel.service.IReceiveOrderManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 物料收货订单信息Controller
 *
 * @author wsdm
 * @date 2021-01-09
 */
@RestController
@RequestMapping("/materiel/receive/manage")
public class ReceiveOrderManageController extends BaseController {
    @Autowired
    private IReceiveOrderManageService receiveOrderManageService;

    /**
     * 查询物料收货订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('receive:manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(ReceiveOrderInfoVo receiveOrderInfo) {
        startPage();
        List<ReceiveOrderInfo> list = receiveOrderManageService.selectReceiveOrderInfoList(receiveOrderInfo);
        return getDataTable(list);
    }

    /**
     * 查询物料收货订单信息列表(采购付款用)
     */
    @GetMapping("/listForPay")
    public TableDataInfo listForPay(ReceiveOrderInfo receiveOrderInfo) {
        startPage();
        return getDataTable(receiveOrderManageService.selectListForPay(receiveOrderInfo));
    }

    /**
     * 查询物料收货订单详情列表(采购付款用)
     */
    @GetMapping("/detailListForPay")
    public TableDataInfo detailListForPay(ReceiveOrderInfo receiveOrderInfo) {
        startPage();
        return getDataTable(receiveOrderManageService.selectDetailListForPay(receiveOrderInfo));
    }

    /*    *//**
     * 导出物料收货订单信息列表
     *//*
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "物料收货订单信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ReceiveOrderInfo receiveOrderInfo) {
        List<ReceiveOrderInfo> list = receiveOrderManageService.selectReceiveOrderInfoList(receiveOrderInfo);
        ExcelUtil<ReceiveOrderInfo> util = new ExcelUtil<ReceiveOrderInfo>(ReceiveOrderInfo. class);
        return util.exportExcel(list, "info");
    }*/

    /**
     * 获取物料收货订单信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('receive:manage:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(receiveOrderManageService.selectReceiveOrderInfoById(id));
    }

    /**
     * 新增物料收货订单信息
     */
    @PreAuthorize("@ss.hasPermi('receive:manage:add')")
    @Log(title = "物料收货订单信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody ReceiveOrderInfoVo receiveOrderInfo) {
        return receiveOrderManageService.insertReceiveOrderInfo(receiveOrderInfo);
    }

    /**
     * 修改物料收货订单信息
     */
    @PreAuthorize("@ss.hasPermi('receive:manage:edit')")
    @Log(title = "物料收货订单信息", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody ReceiveOrderInfoVo receiveOrderInfo) {
        return receiveOrderManageService.updateReceiveOrderInfo(receiveOrderInfo);
    }

    /**
     * 删除物料收货订单信息
     */
    @PreAuthorize("@ss.hasPermi('receive:manage:remove')")
    @Log(title = "物料收货订单信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(receiveOrderManageService.deleteReceiveOrderInfoByIds(ids));
    }

    /**
     * 审核物料采购订单
     */
    @PreAuthorize("@ss.hasPermi('receive:manage:approve')")
    @Log(title = "物料收货订单信息", businessType = BusinessType.UPDATE)
    @PostMapping("/approve")
    public AjaxResult approve(@RequestParam("id") Long id, @RequestParam("action") String action) {
        return receiveOrderManageService.updateApproveStatus(id, action);
    }
}
