package com.wsdm.controller.pd;


import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.pd.domain.PdAssetsManage;
import com.wsdm.pd.service.IPdAssetsManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资产管理Controller
 *
 * @author wsdm
 * @date 2021-06-28
 */

@RestController
@RequestMapping("/pd/assetsManage")
public class PdAssetsManageController extends BaseController {
    @Autowired
    private IPdAssetsManageService pdAssetsManageService;

    /**
     * 查询资产管理列表
     */
    @PreAuthorize("@ss.hasPermi('pd:assetsManage:list')")
    @GetMapping("/list")
    public TableDataInfo list(PdAssetsManage pdAssetsManage) {
        startPage();
        List<PdAssetsManage> list = pdAssetsManageService.selectPdAssetsManageList(pdAssetsManage);
        return getDataTable(list);
    }

    /**
     * 导出资产管理列表
     */
    @PreAuthorize("@ss.hasPermi('pd:assetsManage:export')")
    @Log(title = "资产管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PdAssetsManage pdAssetsManage) {
        List<PdAssetsManage> list = pdAssetsManageService.selectPdAssetsManageList(pdAssetsManage);
        ExcelUtil<PdAssetsManage> util = new ExcelUtil<PdAssetsManage>(PdAssetsManage. class);
        return util.exportExcel(list, "manage");
    }

    /**
     * 获取资产管理详细信息
     */
    //@PreAuthorize("@ss.hasPermi('pd:assetsManage:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(pdAssetsManageService.selectPdAssetsManageById(id));
    }

    /**
     * 新增资产管理
     */
   //@PreAuthorize("@ss.hasPermi('pd:assetsManage:add')")
    @Log(title = "资产管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PdAssetsManage pdAssetsManage) {
        return toAjax(pdAssetsManageService.insertPdAssetsManage(pdAssetsManage));
    }

    /**
     * 修改资产管理
     */
  //  @PreAuthorize("@ss.hasPermi('pd:assetsManage:edit')")
    @Log(title = "资产管理", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody PdAssetsManage pdAssetsManage) {
        return toAjax(pdAssetsManageService.updatePdAssetsManage(pdAssetsManage));
    }

    /**
     * 删除资产管理
     */
    @PreAuthorize("@ss.hasPermi('pd:assetsManage:remove')")
    @Log(title = "资产管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(pdAssetsManageService.deletePdAssetsManageByIds(ids));
    }
}