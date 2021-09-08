package com.wsdm.controller.pd;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.pd.domain.PdRawMaterial;
import com.wsdm.pd.service.IPdRawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 原材料Controller
 *
 * @author wsdm
 * @date 2021-06-21
 */
@RestController
@RequestMapping("/pd/pdRawMaterial")
public class PdRawMaterialController extends BaseController {
    @Autowired
    private IPdRawMaterialService pdRawMaterialService;

    /**
     * 查询原材料列表
     */
//@PreAuthorize("@ss.hasPermi('system:material:list')")
    @GetMapping("/list")
    public TableDataInfo list(PdRawMaterial pdRawMaterial) {
        startPage();
        List<PdRawMaterial> list = pdRawMaterialService.selectPdRawMaterialList(pdRawMaterial);
        return getDataTable(list);
    }

    /**
     * 导出原材料列表
     */
//    @PreAuthorize("@ss.hasPermi('system:material:export')")
    @Log(title = "原材料", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PdRawMaterial pdRawMaterial) {
        List<PdRawMaterial> list = pdRawMaterialService.selectPdRawMaterialList(pdRawMaterial);
        ExcelUtil<PdRawMaterial> util = new ExcelUtil<PdRawMaterial>(PdRawMaterial.class);
        return util.exportExcel(list, "material");
    }

    /**
     * 获取原材料详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:material:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(pdRawMaterialService.selectPdRawMaterialById(id));
    }

    /**
     * 新增原材料
     */
//    @PreAuthorize("@ss.hasPermi('system:material:add')")
    @Log(title = "原材料", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PdRawMaterial pdRawMaterial) {
        return toAjax(pdRawMaterialService.insertPdRawMaterial(pdRawMaterial));
    }

    /**
     * 修改原材料
     */
//    @PreAuthorize("@ss.hasPermi('system:material:edit')")
    @Log(title = "原材料", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PdRawMaterial pdRawMaterial) {
        return toAjax(pdRawMaterialService.updatePdRawMaterial(pdRawMaterial));
    }

    /**
     * 删除原材料
     */
//    @PreAuthorize("@ss.hasPermi('system:material:remove')")
    @Log(title = "原材料", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(pdRawMaterialService.deletePdRawMaterialByIds(ids));
    }
}
