package com.wsdm.controller.pd;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.pd.domain.PdMaterialReceivingDetail;
import com.wsdm.pd.service.IPdMaterialReceivingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物料领取单明细Controller
 *
 * @author wsdm
 * @date 2021-05-11
 */
@RestController
@RequestMapping("/pd/materialReceivingDetail")
public class PdMaterialReceivingDetailController extends BaseController
{
    @Autowired
    private IPdMaterialReceivingDetailService pdMaterialReceivingDetailService;

    /**
     * 查询物料领取单明细列表
     */
//    @PreAuthorize("@ss.hasPermi('pd:materialReceivingDetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(PdMaterialReceivingDetail pdMaterialReceivingDetail)
    {
        startPage();
        List<PdMaterialReceivingDetail> list = pdMaterialReceivingDetailService.selectPdMaterialReceivingDetailList(
                pdMaterialReceivingDetail);
        return getDataTable(list);
    }

    /**
     * 导出物料领取单明细列表
     */
//    @PreAuthorize("@ss.hasPermi('pd:materialReceivingDetail:export')")
    @Log(title = "物料领取单明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PdMaterialReceivingDetail pdMaterialReceivingDetail)
    {
        List<PdMaterialReceivingDetail> list = pdMaterialReceivingDetailService.selectPdMaterialReceivingDetailList(
                pdMaterialReceivingDetail);
        ExcelUtil<PdMaterialReceivingDetail> util = new ExcelUtil<PdMaterialReceivingDetail>(
                PdMaterialReceivingDetail.class);
        return util.exportExcel(list, "detail");
    }

    /**
     * 获取物料领取单明细详细信息
     */
//    @PreAuthorize("@ss.hasPermi('pd:materialReceivingDetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(pdMaterialReceivingDetailService.selectPdMaterialReceivingDetailById(id));
    }

    /**
     * 新增物料领取单明细
     */
//    @PreAuthorize("@ss.hasPermi('pd:materialReceivingDetail:add')")
    @Log(title = "物料领取单明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PdMaterialReceivingDetail pdMaterialReceivingDetail)
    {
        return toAjax(pdMaterialReceivingDetailService.insertPdMaterialReceivingDetail(pdMaterialReceivingDetail));
    }

    /**
     * 修改物料领取单明细
     */
//    @PreAuthorize("@ss.hasPermi('pd:materialReceivingDetail:edit')")
    @Log(title = "物料领取单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PdMaterialReceivingDetail pdMaterialReceivingDetail)
    {
        return toAjax(pdMaterialReceivingDetailService.updatePdMaterialReceivingDetail(pdMaterialReceivingDetail));
    }

    /**
     * 删除物料领取单明细
     */
//    @PreAuthorize("@ss.hasPermi('pd:materialReceivingDetail:remove')")
    @Log(title = "物料领取单明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pdMaterialReceivingDetailService.deletePdMaterialReceivingDetailByIds(ids));
    }
}
