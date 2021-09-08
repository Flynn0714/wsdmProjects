package com.wsdm.controller.pd;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.pd.domain.PdContributionTestDetail;
import com.wsdm.pd.service.IPdContributionTestDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 工绩单质检明细Controller
 *
 * @author wsdm
 * @date 2021-06-18
 */
@RestController
@RequestMapping("/pd/pdContributionTestDetail")
public class PdContributionTestDetailController extends BaseController {
    @Autowired
    private IPdContributionTestDetailService pdContributionTestDetailService;

    /**
     * 查询工绩单质检明细列表
     */
//@PreAuthorize("@ss.hasPermi('pd:pdContributionTestDetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(PdContributionTestDetail pdContributionTestDetail) {
        startPage();
        List<PdContributionTestDetail> list = pdContributionTestDetailService.selectPdContributionTestDetailList(pdContributionTestDetail);
        return getDataTable(list);
    }

    /**
     * 导出工绩单质检明细列表
     */
//    @PreAuthorize("@ss.hasPermi('pd:pdContributionTestDetail:export')")
    @Log(title = "工绩单质检明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PdContributionTestDetail pdContributionTestDetail) {
        List<PdContributionTestDetail> list = pdContributionTestDetailService.selectPdContributionTestDetailList(pdContributionTestDetail);
        ExcelUtil<PdContributionTestDetail> util = new ExcelUtil<PdContributionTestDetail>(PdContributionTestDetail.class);
        return util.exportExcel(list, "pdContributionTestDetail");
    }

    /**
     * 获取工绩单质检明细详细信息
     */
//    @PreAuthorize("@ss.hasPermi('pd:pdContributionTestDetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(pdContributionTestDetailService.selectPdContributionTestDetailById(id));
    }

    /**
     * 新增工绩单质检明细
     */
//    @PreAuthorize("@ss.hasPermi('pd:pdContributionTestDetail:add')")
    @Log(title = "工绩单质检明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PdContributionTestDetail pdContributionTestDetail) {
        return toAjax(pdContributionTestDetailService.insertPdContributionTestDetail(pdContributionTestDetail));
    }

    /**
     * 修改工绩单质检明细
     */
//    @PreAuthorize("@ss.hasPermi('pd:pdContributionTestDetail:edit')")
    @Log(title = "工绩单质检明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PdContributionTestDetail pdContributionTestDetail) {
        return toAjax(pdContributionTestDetailService.updatePdContributionTestDetail(pdContributionTestDetail));
    }

    /**
     * 删除工绩单质检明细
     */
//    @PreAuthorize("@ss.hasPermi('pd:pdContributionTestDetail:remove')")
    @Log(title = "工绩单质检明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(pdContributionTestDetailService.deletePdContributionTestDetailByIds(ids));
    }
}
