package com.wsdm.stock.controller;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.stock.domain.SkStockChange;
import com.wsdm.stock.service.ISkStockChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存变动记录Controller
 *
 * @author wsdm
 * @date 2020-12-07
 */
@RestController
@RequestMapping("/stock/change")
public class SkStockChangeController extends BaseController {
    @Autowired
    private ISkStockChangeService skStockChangeService;

    /**
     * 查询库存变动记录列表
     */
    @PreAuthorize("@ss.hasPermi('stock:change:list')")
    @GetMapping("/list")
    public TableDataInfo list(SkStockChange skStockChange) {
        startPage();
        List<SkStockChange> list = skStockChangeService.selectSkStockChangeList(skStockChange);
        return getDataTable(list);
    }

    /**
     * 获取库存变动记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('stock:change:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(skStockChangeService.selectSkStockChangeById(id));
    }

    /**
     * 新增库存变动记录
     */
    @PreAuthorize("@ss.hasPermi('stock:change:add')")
    @Log(title = "库存变动记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SkStockChange skStockChange) {
        return toAjax(skStockChangeService.insertSkStockChange(skStockChange));
    }

    /**
     * 修改库存变动记录
     */
    @PreAuthorize("@ss.hasPermi('stock:change:edit')")
    @Log(title = "库存变动记录", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody SkStockChange skStockChange) {
        return toAjax(skStockChangeService.updateSkStockChange(skStockChange));
    }

    /**
     * 删除库存变动记录
     */
    @PreAuthorize("@ss.hasPermi('stock:change:remove')")
    @Log(title = "库存变动记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(skStockChangeService.deleteSkStockChangeByIds(ids));
    }
}
