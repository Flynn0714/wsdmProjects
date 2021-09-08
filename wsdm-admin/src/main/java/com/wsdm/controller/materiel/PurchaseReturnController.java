package com.wsdm.controller.materiel;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.materiel.domain.PurchaseReturnInfo;
import com.wsdm.materiel.domain.PurchaseReturnInfoVo;
import com.wsdm.materiel.domain.ReceiveOrderInfo;
import com.wsdm.materiel.service.IReceiveOrderManageService;
import com.wsdm.materiel.service.PurchaseReturnInfoManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * @Auther: wjj
 * @Date: 2021/7/14 15:36
 * @Description: 采购退货信息列表controller
 */
@RestController
@RequestMapping("/materiel/purchase/return")
public class PurchaseReturnController extends BaseController {
    @Autowired
    private PurchaseReturnInfoManageService purchaseReturnInfoService;


    /**
     * 查询采购退货单信息列表
     *
     * @return
     */
    @PreAuthorize("@ss.hasPermi('purchase:return:list')")
    @GetMapping("/list")
    public TableDataInfo list(PurchaseReturnInfoVo purchaseReturnInfo) {
        startPage();
        List<PurchaseReturnInfo> list = purchaseReturnInfoService.selectPurchaseReturnInfoList(purchaseReturnInfo);
        return getDataTable(list);
    }


    /**
     * 选择采购收获单
     *
     * @return 结果
     */
    @GetMapping("/queryByReceive")
    public TableDataInfo queryByReceive(PurchaseReturnInfo purchaseReturnInfo) {
        startPage();
        List<PurchaseReturnInfo> purchaseReturnInfoVos = purchaseReturnInfoService.selectByReturnForReceive(purchaseReturnInfo);
        return getDataTable(purchaseReturnInfoVos);
    }

    /**
     * @Description: 新增退货单信息
     * @Param: purchaseReturnInfo  退货单信息
     * @return:
     */
    @PreAuthorize("@ss.hasPermi('purchase:return:add')")
    @Log(title = "退货订单信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PurchaseReturnInfo purchaseReturnInfo) {
        return purchaseReturnInfoService.insertPurchaseReturnInfo(purchaseReturnInfo);
    }


    /**
     * @Description: 查看退货订单详细信息
     * @Param:
     * @return:
     */
    @PreAuthorize("@ss.hasPermi('purchase:return:query')")
    @GetMapping("/query/{id}")
    public AjaxResult query(@PathVariable("id") Long id) {
        PurchaseReturnInfo purchaseReturnInfo = purchaseReturnInfoService.selectReturnOrderInfoById(id);
        return AjaxResult.success(purchaseReturnInfo);
    }


    /**
     * @Description: 修改退货单信息
     * @Param:
     * @return:
     */
//    @PreAuthorize("@ss.hasPermi('purchase:return:edit')")
    @Log(title = "退货订单信息", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody PurchaseReturnInfo returnOrderInfo) {
        return purchaseReturnInfoService.updatePurchaseReturnInfo(returnOrderInfo);
    }

    /**
     * @Description: 审核采购退货单
     * @Param:
     * @return:
     */
    @PreAuthorize("@ss.hasPermi('purchase:return:approve')")
    @Log(title = "退货订单信息", businessType = BusinessType.UPDATE)
    @PostMapping("/approve")
    public AjaxResult approve(@RequestParam("id") Long id, @RequestParam("action") String action) {
        return purchaseReturnInfoService.updateApproveStatus(id, action);
    }


    /**
     * @Description: 删除退货单信息
     * @Param:
     * @return:
     */
    @PreAuthorize("@ss.hasPermi('purchase:return:delete')")
    @Log(title = "退货订单信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id) {
        purchaseReturnInfoService.deleteReturnOrderInfoById(id);
        return AjaxResult.success();
    }


    /**
     * @Description: 批量删除退货订单信息
     * @Param:
     * @return:
     */
    @PreAuthorize("@ss.hasPermi('purchase:return:remove')")
    @Log(title = "退货订单信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable("ids") Long[] ids) {
        return purchaseReturnInfoService.deleteReturnOrderInfoByIds(ids);
    }
}
