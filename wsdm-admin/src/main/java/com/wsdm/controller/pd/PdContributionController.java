package com.wsdm.controller.pd;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.pd.domain.PdContribution;
import com.wsdm.pd.domain.PdContributionVO;
import com.wsdm.pd.service.IPdContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 工绩单Controller
 *
 * @author wsdm
 * @date 2021-05-11
 */
@RestController
@RequestMapping("/pd/contribution")
public class PdContributionController extends BaseController {
    @Autowired
    private IPdContributionService pdContributionService;

    /**
     * 查询工绩单列表
     */
//    @PreAuthorize("@ss.hasPermi('pd:contribution:list')")
    @GetMapping("/list")
    public TableDataInfo list(PdContribution pdContribution) {
        startPage();
        List<PdContribution> list = pdContributionService.selectPdContributionList(pdContribution);
        return getDataTable(list);
    }

    /**
     * 导出工绩单列表
     */
//    @PreAuthorize("@ss.hasPermi('pd:contribution:export')")
    @Log(title = "工绩单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PdContribution pdContribution) {
        List<PdContribution> list = pdContributionService.selectPdContributionList(pdContribution);
        ExcelUtil<PdContribution> util = new ExcelUtil<PdContribution>(PdContribution.class);
        return util.exportExcel(list, "contribution");
    }

    /**
     * 获取工绩单详细信息
     */
//    @PreAuthorize("@ss.hasPermi('pd:contribution:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(pdContributionService.selectPdContributionById(id));
    }

    /**
     * 新增工绩单
     */
//    @PreAuthorize("@ss.hasPermi('pd:contribution:add')")
    @Log(title = "工绩单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PdContributionVO vo) {
        vo.getPdContribution().setCreateBy(SecurityUtils.getUsername());
        return toAjax(pdContributionService.insertPdContribution(vo));
    }

    /**
     * 修改工绩单
     */
//    @PreAuthorize("@ss.hasPermi('pd:contribution:edit')")
    @Log(title = "工绩单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PdContributionVO vo) {
        vo.getPdContribution().setUpdateBy(SecurityUtils.getUsername());
        return toAjax(pdContributionService.updatePdContribution(vo));
    }

    /**
     * 逻辑删除工绩单
     */
//    @PreAuthorize("@ss.hasPermi('pd:contribution:deleteSafe')")
    @Log(title = "安全删除工绩单", businessType = BusinessType.DELETE)
    @GetMapping("/deleteSafe/{id}")
    public AjaxResult deleteSafe(@PathVariable Long id) {
        return toAjax(pdContributionService.deleteSafe(id, SecurityUtils.getUsername()));
    }

    /**
     * 逻辑恢复工绩单
     */
//    @PreAuthorize("@ss.hasPermi('pd:contribution:recover')")
    @Log(title = "恢复工绩单", businessType = BusinessType.UPDATE)
    @GetMapping("/recover/{id}")
    public AjaxResult recover(@PathVariable Long id) {
        return toAjax(pdContributionService.recover(id, SecurityUtils.getUsername()));
    }

    @Log(title = "提交质检", businessType = BusinessType.UPDATE)
    @GetMapping("/submit/{id}")
    public AjaxResult submit(@PathVariable Long id) {
        return toAjax(pdContributionService.submit(id, SecurityUtils.getUsername()));
    }

    @Log(title = "审核通过", businessType = BusinessType.UPDATE)
    @GetMapping("/pass/{id}")
    public AjaxResult pass(@PathVariable Long id) {
        return toAjax(pdContributionService.pass(id, SecurityUtils.getUsername()));
    }

    @Log(title = "审核驳回", businessType = BusinessType.UPDATE)
    @GetMapping("/reject/{id}")
    public AjaxResult reject(@PathVariable Long id) {
        return toAjax(pdContributionService.reject(id, SecurityUtils.getUsername()));
    }
}
