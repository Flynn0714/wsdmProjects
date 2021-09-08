package com.wsdm.controller.materiel;


import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.materiel.domain.DefaultSupplierConfig;
import com.wsdm.materiel.service.IDefaultSupplierConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 默认供应商配置Controller
 *
 * @author wsdm
 * @date 2020-11-29
 */
@RestController
@RequestMapping("/materiel/purchase/defaultsupplier")
public class DefaultSupplierConfigController extends BaseController {
    @Autowired
    private IDefaultSupplierConfigService defaultSupplierConfigService;

    /**
     * 查询默认供应商配置列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:defaultsupplier:list')")
    @GetMapping("/list")
    public TableDataInfo list(DefaultSupplierConfig defaultSupplierConfig) {
        startPage();
        List<DefaultSupplierConfig> list = defaultSupplierConfigService.selectDefaultSupplierConfigList(defaultSupplierConfig);
        return getDataTable(list);
    }


    /**
     * 获取默认供应商配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:defaultsupplier:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(defaultSupplierConfigService.selectDefaultSupplierConfigById(id));
    }

    /**
     * 新增默认供应商配置
     */
    @PreAuthorize("@ss.hasPermi('purchase:defaultsupplier:add')")
    @Log(title = "默认供应商配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody DefaultSupplierConfig defaultSupplierConfig) {
        return toAjax(defaultSupplierConfigService.insertDefaultSupplierConfig(defaultSupplierConfig));
    }

    /**
     * 修改默认供应商配置
     */
    @PreAuthorize("@ss.hasPermi('purchase:defaultsupplier:edit')")
    @Log(title = "默认供应商配置", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody DefaultSupplierConfig defaultSupplierConfig) {
        return defaultSupplierConfigService.updateDefaultSupplierConfig(defaultSupplierConfig);
    }

    /**
     * 删除默认供应商配置
     */
    @PreAuthorize("@ss.hasPermi('purchase:defaultsupplier:remove')")
    @Log(title = "默认供应商配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(defaultSupplierConfigService.deleteDefaultSupplierConfigByIds(ids));
    }
}
