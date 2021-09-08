package com.wsdm.controller.pd;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.pd.domain.PdContributionDetail;
import com.wsdm.pd.service.IPdContributionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 工绩单明细Controller
 *
 * @author wsdm
 * @date 2021-05-11
 */
@RestController
@RequestMapping("/pd/contributionDetail")
public class PdContributionDetailController extends BaseController
{
    @Autowired
    private IPdContributionDetailService pdContributionDetailService;

    /**
     * 查询工绩单明细列表
     */
//    @PreAuthorize("@ss.hasPermi('pd:contributionDetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(PdContributionDetail pdContributionDetail)
    {
        startPage();
        List<PdContributionDetail> list = pdContributionDetailService.selectPdContributionDetailList(
                pdContributionDetail);
        return getDataTable(list);
    }

    /**
     * 导出工绩单明细列表
     */
//    @PreAuthorize("@ss.hasPermi('pd:contributionDetail:export')")
    @Log(title = "工绩单明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PdContributionDetail pdContributionDetail)
    {
        List<PdContributionDetail> list = pdContributionDetailService.selectPdContributionDetailList(
                pdContributionDetail);
        ExcelUtil<PdContributionDetail> util = new ExcelUtil<PdContributionDetail>(PdContributionDetail.class);
        return util.exportExcel(list, "detail");
    }

    /**
     * 获取工绩单明细详细信息
     */
//    @PreAuthorize("@ss.hasPermi('pd:contributionDetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(pdContributionDetailService.selectPdContributionDetailById(id));
    }

    /**
     * 新增工绩单明细
     */
//    @PreAuthorize("@ss.hasPermi('pd:contributionDetail:add')")
    @Log(title = "工绩单明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PdContributionDetail pdContributionDetail)
    {
        return toAjax(pdContributionDetailService.insertPdContributionDetail(pdContributionDetail));
    }

    /**
     * 修改工绩单明细
     */
//    @PreAuthorize("@ss.hasPermi('pd:contributionDetail:edit')")
    @Log(title = "工绩单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PdContributionDetail pdContributionDetail)
    {
        return toAjax(pdContributionDetailService.updatePdContributionDetail(pdContributionDetail));
    }

    /**
     * 删除工绩单明细
     */
//    @PreAuthorize("@ss.hasPermi('pd:contributionDetail:remove')")
    @Log(title = "工绩单明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pdContributionDetailService.deletePdContributionDetailByIds(ids));
    }
}
