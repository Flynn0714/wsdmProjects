package com.wsdm.controller.materiel;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.materiel.domain.QualityInspectionInfo;
import com.wsdm.materiel.domain.QualityInspectionInfoVo;
import com.wsdm.materiel.service.QualityInspectionManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物料收货质检单信息Controller
 *
 * @author wanglei
 * @date 2021-06-20
 */
@RestController
@RequestMapping("/materiel/qualityInspection/manage")
public class QualityInspectionController extends BaseController {
    @Autowired
    private QualityInspectionManageService qualityInspectionManageService;

    /**
     * 查询物料收货质检单信息列表
     */
    @PreAuthorize("@ss.hasPermi('qualityCheck:manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(QualityInspectionInfoVo qualityInspectionInfo) {
        startPage();
        List<QualityInspectionInfo> list = qualityInspectionManageService.selectQualityInspectionInfoList(qualityInspectionInfo);
        return getDataTable(list);
    }

    /*
     */
/**
 * 导出物料收货质检单信息列表
 *//*

    @PreAuthorize("@ss.hasPermi('qualityCheck:info:export')")
    @Log(title = "物料收货质检单信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(QualityInspectionInfo qualityInspectionInfo) {
        List<QualityInspectionInfo> list = qualityInspectionManageService.selectQualityInspectionInfoList(qualityInspectionInfo);
        ExcelUtil<QualityInspectionInfo> util = new ExcelUtil<QualityInspectionInfo>(QualityInspectionInfo.class);
        return util.exportExcel(list, "info");
    }
*/

    /**
     * 获取物料收货质检单信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('qualityCheck:manage:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(qualityInspectionManageService.selectQualityInspectionInfoById(id));
    }

    @GetMapping(value = "/getDetailByCheckNumber")
    public AjaxResult getDetailByCheckNumber(String checkNumber) {
        return AjaxResult.success(qualityInspectionManageService.selectQualityInspectionInfoByCheckNumber(checkNumber));
    }

    /**
     * 修改物料收货质检单信息
     */
    @PreAuthorize("@ss.hasPermi('qualityCheck:manage:edit')")
    @Log(title = "物料收货质检单信息", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody QualityInspectionInfoVo qualityInspectionInfo) {
        return qualityInspectionManageService.updateQualityInspectionInfo(qualityInspectionInfo);
    }


    /**
     * 审核物料收货质检单信息
     */
    @PreAuthorize("@ss.hasPermi('qualityCheck:manage:approve')")
    @Log(title = "物料收货质检单信息", businessType = BusinessType.UPDATE)
    @PostMapping("/approve")
    public AjaxResult approve(@RequestParam("id") Long id, @RequestParam("action") String action, @RequestParam("approvedType") String approvedType) {
        return qualityInspectionManageService.updateApproveStatus(id, action, approvedType);
    }

    /**
     * 提交审核物料收货质检单信息
     */
    @PreAuthorize("@ss.hasPermi('qualityCheck:manage:submitApprove')")
    @Log(title = "物料收货质检单信息", businessType = BusinessType.UPDATE)
    @PostMapping("/submitApprove/{id}")
    public AjaxResult submitApprove(@PathVariable("id") Long id) {
        return qualityInspectionManageService.submitApprove(id);
    }

    /**
     * 删除物料收货质检单信息
     */
    @PreAuthorize("@ss.hasPermi('qualityCheck:manage:remove')")
    @Log(title = "物料收货质检单信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(qualityInspectionManageService.deleteQualityInspectionInfoByIds(ids));
    }
}
