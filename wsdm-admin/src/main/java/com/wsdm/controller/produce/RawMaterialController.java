package com.wsdm.controller.produce;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.produce.domain.RawMaterial;
import com.wsdm.produce.service.IRawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 原材料Controller
 *
 * @author wsdm
 * @date 2020-11-06
 */
@RestController
@RequestMapping("/produce/material")
public class RawMaterialController extends BaseController {
    @Autowired
    private IRawMaterialService rawMaterialService;

    /**
     * 查询原材料列表
     */
    @PreAuthorize("@ss.hasPermi('produce:material:list')")
    @GetMapping("/list")
    public TableDataInfo list(RawMaterial rawMaterial) {
        startPage();
        List<RawMaterial> list = rawMaterialService.selectRawMaterialList(rawMaterial);
        return getDataTable(list);
    }

    /**
     * 获取原材料详细信息
     */
    @PreAuthorize("@ss.hasPermi('produce:material:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(rawMaterialService.selectRawMaterialById(id));
    }

    /**
     * 新增原材料
     */
    @PreAuthorize("@ss.hasPermi('produce:material:add')")
    @Log(title = "原材料", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RawMaterial rawMaterial) {
        return toAjax(rawMaterialService.insertRawMaterial(rawMaterial));
    }

    /**
     * 修改原材料
     */
    @PreAuthorize("@ss.hasPermi('produce:material:edit')")
    @Log(title = "原材料", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RawMaterial rawMaterial) {
        return toAjax(rawMaterialService.updateRawMaterial(rawMaterial));
    }

    /**
     * 删除原材料
     */
    @PreAuthorize("@ss.hasPermi('produce:material:remove')")
    @Log(title = "原材料", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(rawMaterialService.deleteRawMaterialByIds(ids));
    }

    /**
     * 导出原材料列表

    @PreAuthorize("@ss.hasPermi('produce:material:export')")
    @Log(title = "原材料", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(RawMaterial rawMaterial) {
        List<RawMaterial> list = rawMaterialService.selectRawMaterialList(rawMaterial);
        ExcelUtil<RawMaterial> util = new ExcelUtil<RawMaterial>(RawMaterial.class);
        return util.exportExcel(list, "material");
    }*/
}
