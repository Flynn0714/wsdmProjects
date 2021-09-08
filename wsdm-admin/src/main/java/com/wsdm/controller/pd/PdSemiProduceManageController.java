package com.wsdm.controller.pd;

import com.wsdm.common.annotation.Log;
import com.wsdm.pd.domain.PdSemiProduceManage;
import com.wsdm.pd.service.IPdSemiProduceManageService;
import org.springframework.web.bind.annotation.RestController;

import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 半成品管理Controller
 *
 * @author wsdm
 * @date 2021-06-21
 */
@RestController
@RequestMapping("/pd/pdSemiProduceManage")
public class PdSemiProduceManageController extends BaseController {
    @Autowired
    private IPdSemiProduceManageService pdSemiProduceManageService;

/**
 * 查询半成品管理列表
 */
//@PreAuthorize("@ss.hasPermi('system:manage:list')")
@GetMapping("/list")
        public TableDataInfo list(PdSemiProduceManage pdSemiProduceManage) {
        startPage();
        List<PdSemiProduceManage> list = pdSemiProduceManageService.selectPdSemiProduceManageList(pdSemiProduceManage);
        return getDataTable(list);
    }
    
    /**
     * 导出半成品管理列表
     */
//    @PreAuthorize("@ss.hasPermi('system:manage:export')")
    @Log(title = "半成品管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PdSemiProduceManage pdSemiProduceManage) {
        List<PdSemiProduceManage> list = pdSemiProduceManageService.selectPdSemiProduceManageList(pdSemiProduceManage);
        ExcelUtil<PdSemiProduceManage> util = new ExcelUtil<PdSemiProduceManage>(PdSemiProduceManage. class);
        return util.exportExcel(list, "manage");
    }

    /**
     * 获取半成品管理详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:manage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(pdSemiProduceManageService.selectPdSemiProduceManageById(id));
    }

    /**
     * 新增半成品管理
     */
//    @PreAuthorize("@ss.hasPermi('system:manage:add')")
    @Log(title = "半成品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PdSemiProduceManage pdSemiProduceManage) {
        return toAjax(pdSemiProduceManageService.insertPdSemiProduceManage(pdSemiProduceManage));
    }

    /**
     * 修改半成品管理
     */
//    @PreAuthorize("@ss.hasPermi('system:manage:edit')")
    @Log(title = "半成品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PdSemiProduceManage pdSemiProduceManage) {
        return toAjax(pdSemiProduceManageService.updatePdSemiProduceManage(pdSemiProduceManage));
    }

    /**
     * 删除半成品管理
     */
//    @PreAuthorize("@ss.hasPermi('system:manage:remove')")
    @Log(title = "半成品管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(pdSemiProduceManageService.deletePdSemiProduceManageByIds(ids));
    }
}
