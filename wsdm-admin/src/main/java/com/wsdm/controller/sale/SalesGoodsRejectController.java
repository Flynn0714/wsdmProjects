package com.wsdm.controller.sale;

import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.sale.domain.SalesGoodsReject;
import com.wsdm.sale.domain.SalesGoodsRejectInfo;
import com.wsdm.sale.domain.SalesGoodsRejectInfoQuery;
import com.wsdm.sale.domain.SalesGoodsRejectQuery;
import com.wsdm.sale.service.impl.ISalesGoodsRejectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


/**
 * 销售退货Controller
 *
 * @author Lu
 * @date 2021-07-16
 */
@RestController
@RequestMapping("/sales/goods/reject")
public class SalesGoodsRejectController extends BaseController {

    @Autowired
    private ISalesGoodsRejectServiceImpl salesGoodsRejectService;

    /**
     * 获取销售退货列表
     */
    @PreAuthorize("@ss.hasPermi('sales:goods:list')")
    @GetMapping("/list")
    public TableDataInfo list(SalesGoodsRejectInfoQuery salesGoodsRejectInfoQuery) {
        startPage();
        List<SalesGoodsRejectInfo> list = salesGoodsRejectService.selectSalesGoodsRejectInfo(salesGoodsRejectInfoQuery);
        return getDataTable(list);
    }

    /**
     * 获取销售退货详细信息
     */
    @PreAuthorize("@ss.hasPermi('sales:goods:detail')")
    @GetMapping("/list/detail")
    public AjaxResult list(String returnNumber) {
        if(Objects.isNull(returnNumber)) return AjaxResult.error();
        return AjaxResult.success(salesGoodsRejectService.getSalesGoodsRejectDetailByReturnNumber(returnNumber));
    }

    /**
     * 新增销售退货，获取销售单
     */
    @PreAuthorize("@ss.hasPermi('sales:goods:add')")
    @GetMapping("/addsalelist")
    public TableDataInfo addSalesList(SalesGoodsRejectQuery salesGoodsRejectQuery) {
        startPage();
        List<SalesGoodsReject> list = salesGoodsRejectService.selectSalesGoodsRejectAll(salesGoodsRejectQuery);
        return getDataTable(list);
    }

    /**
     * 获新增销售退货，获取销售单详细单
     */
    @GetMapping("/addsalesreturn")
    public AjaxResult addSalesReturn(String saleName,String customer) {
        if(Objects.isNull(saleName) || Objects.isNull(customer)) return AjaxResult.error();
        return AjaxResult.success(salesGoodsRejectService.selectSalesGoodsRejectDetailAllBySaleName(saleName,customer));
    }

    /**
     * 保存销售信息
     */
    @PostMapping("/save")
    public AjaxResult saveSalesReturn(@RequestBody SalesGoodsReject salesGoodsReject) {
        return toAjax(salesGoodsRejectService.saveOrSubmitSalesReturn(0,salesGoodsReject));
    }

    /**
     * 提交销售信息
     */
    @PostMapping("/submit")
    public AjaxResult submitSalesReturn(@RequestBody SalesGoodsReject salesGoodsReject) {
        return toAjax(salesGoodsRejectService.saveOrSubmitSalesReturn(1,salesGoodsReject));
    }

    /**
     * 审核人审核
     */
//    @PreAuthorize("@ss.hasPermi('sales:goods:submit')")
    @PostMapping("/list/detail/submit")
    public AjaxResult save(String returnNumber,String action) {
        return toAjax(salesGoodsRejectService.updateSalesGoodsReturn(returnNumber,action));
    }

    /**
     * 销售退货编辑
     */
    @PreAuthorize("@ss.hasPermi('sales:goods:edit')")
    @PostMapping("/list/detail/edit")
    public AjaxResult save(@RequestBody SalesGoodsReject salesGoodsReject) {
        return toAjax(salesGoodsRejectService.updateSalesReturn(salesGoodsReject));
    }

    /**
     * 销售退货编辑
     */
    @PreAuthorize("@ss.hasPermi('sales:goods:delete')")
    @PostMapping("/list/delete")
    public AjaxResult delete(String[] returnNumbers) {
        return toAjax(salesGoodsRejectService.deleteSalesGoodsReturn(returnNumbers));
    }

    /**
     * 获取客户列表
     */
    @GetMapping("/customer/list")
    public AjaxResult save() {
        return AjaxResult.success(salesGoodsRejectService.selectCustomerList());
    }
}
