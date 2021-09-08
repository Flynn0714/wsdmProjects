package com.wsdm.controller.pd;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.materiel.domain.PurchasePayOrderDetail;
import com.wsdm.materiel.domain.PurchasePayOrderInfoVo;
import com.wsdm.pd.domain.*;
import com.wsdm.pd.service.ProcessPayOrderDetailService;
import com.wsdm.pd.service.ProcessPayOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Auther: wjj
 * @Date: 2021/8/24 16:58
 * @Description: 加工付款Controller接口
 */
@RestController
@RequestMapping("/process/pay")
public class ProcessPayOrderController extends BaseController {

    @Autowired
    private ProcessPayOrderInfoService payOrderInfoService;
    @Autowired
    private ProcessPayOrderDetailService payOrderDetailService;


    /**
     * 查询加工单信息列表
     * @param payOrderInfo
     * @return
     */
    @PreAuthorize("@ss.hasPermi('process:pay:list')")
    @GetMapping("/list")
    public TableDataInfo selectByList(ProcessPayOrderVo payOrderInfo){
        startPage();
        List<ProcessPayOrderInfo> processPayOrderInfos = payOrderInfoService.selectByProcessList(payOrderInfo);
        return getDataTable(processPayOrderInfos);
    }

    /**
     * 根据员工查看付款单详情信息
     * @param id
     * @return
     */
    @PreAuthorize("@ss.hasPermi('process:pay:query')")
    @GetMapping("/query/{id}")
    public AjaxResult selectByName(@PathVariable("id") Long id){
        ProcessPayOrderInfo processPayOrderInfo = payOrderInfoService.selectById(id);
        return AjaxResult.success(processPayOrderInfo);
    }

    /**
     * 新增加工应付款汇总信息
     * @param id
     * @return
     */
    @PreAuthorize("@ss.hasPermi('process:pay:add')")
    @PostMapping("/add")
    public AjaxResult add(Long id){
        return AjaxResult.success(payOrderInfoService.add(id));
    }

    /**
     * 去付款
     * @param payOrderInfo
     * @return
     */
    @PreAuthorize("@ss.hasPermi('process:pay:purchase')")
    @PostMapping("/purchase")
    public AjaxResult payFor(@RequestBody ProcessPayOrderInfo payOrderInfo){
        AjaxResult ajaxResult = payOrderInfoService.insertProcessPayRecord(payOrderInfo);
        return ajaxResult;
    }


    /**
    * @Description: 删除付款单详情信息
    * @Param:
    * @return:
    */
    @PreAuthorize("@ss.hasPermi('process:pay:delete')")
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return AjaxResult.success(payOrderDetailService.deleteByDetails(id));
    }


    /**
    * @Description: 查询付款记录列表
    * @Param:
    * @return:
    */
    @PreAuthorize("@ss.hasPermi('process:pay:selectDetail')")
    @GetMapping("/selectDetail")
    public TableDataInfo selectDetails(ProcessPayOrderDetail processPayOrderDetail){
        startPage();
        List<ProcessPayOrderDetail> processPayOrderDetails = payOrderDetailService.selectByPayDetails(processPayOrderDetail);
        return getDataTable(processPayOrderDetails);
    }

    /**
     * 查询加工明细列表
     * @param pdContribution
     * @return
     */
    @PreAuthorize("@ss.hasPermi('process:pay:selectPd')")
    @GetMapping("/selectPd")
    public AjaxResult selectPd(PdContribution pdContribution){
        List<PdContribution> pdContributions = payOrderInfoService.selectPdList(pdContribution);
        return AjaxResult.success(pdContributions);
    }


    /**
    * 导出加工付款记录列表
    * @Param:
    * @return:
    */
    @PreAuthorize("@ss.hasPermi('process:pay:export')")
    @Log(title = "物料采购付款单信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void exportPay(ProcessPayOrderDetail payOrderInfo, HttpServletResponse response) {
        List<ProcessPayOrderDetail> details = payOrderDetailService.selectByPayDetails(payOrderInfo);
        ExcelUtil<ProcessPayOrderDetail> excelUtil = new ExcelUtil<>(ProcessPayOrderDetail.class);
        excelUtil.exportExcel(details, "加工付款记录", response);
    }
}
