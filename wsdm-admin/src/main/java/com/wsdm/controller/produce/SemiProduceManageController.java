package com.wsdm.controller.produce;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.produce.domain.SemiProduceManage;
import com.wsdm.produce.service.ISemiProduceManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 半成品管理Controller
 *
 * @author wsdm
 * @date 2020-11-06
 */
@RestController
@RequestMapping("/produce/semi")
public class SemiProduceManageController extends BaseController {
    @Autowired
    private ISemiProduceManageService semiProduceManageService;

    /**
     * 查询半成品管理列表
     */
    @PreAuthorize("@ss.hasPermi('produce:semi:list')")
    @GetMapping("/list")
    public TableDataInfo list(SemiProduceManage semiProduceManage) {
        startPage();
        List<SemiProduceManage> list = semiProduceManageService.selectSemiProduceManageList(semiProduceManage);
        return getDataTable(list);
    }

    /**
     * 获取半成品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('produce:semi:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(semiProduceManageService.selectSemiProduceManageById(id));
    }

    /**
     * 新增半成品管理
     */
    @PreAuthorize("@ss.hasPermi('produce:semi:add')")
    @Log(title = "半成品管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SemiProduceManage semiProduceManage) {
        return semiProduceManageService.insertSemiProduceManage(semiProduceManage);
    }

    /**
     * 修改半成品管理
     */
    @PreAuthorize("@ss.hasPermi('produce:semi:edit')")
    @Log(title = "半成品管理", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody SemiProduceManage semiProduceManage) {
        return semiProduceManageService.updateSemiProduceManage(semiProduceManage);
    }

    /**
     * 删除半成品管理
     */
    @PreAuthorize("@ss.hasPermi('produce:semi:remove')")
    @Log(title = "半成品管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(semiProduceManageService.deleteSemiProduceManageByIds(ids));
    }

    /**
     * 导出半成品管理列表

    @PreAuthorize("@ss.hasPermi('produce:semi:export')")
    @Log(title = "半成品管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SemiProduceManage semiProduceManage) {
        List<SemiProduceManage> list = semiProduceManageService.selectSemiProduceManageList(semiProduceManage);
        ExcelUtil<SemiProduceManage> util = new ExcelUtil<SemiProduceManage>(SemiProduceManage.class);
        return util.exportExcel(list, "semi");
    }*/
}
