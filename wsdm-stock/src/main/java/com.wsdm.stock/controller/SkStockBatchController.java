package com.wsdm.stock.controller;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.stock.domain.SkStock;
import com.wsdm.stock.domain.SkStockBatch;
import com.wsdm.stock.service.ISkStockBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存批次Controller
 *
 * @author wsdm
 * @date 2020-12-07
 */
@RestController
@RequestMapping("/stock/batch")
public class SkStockBatchController extends BaseController {
    @Autowired
    private ISkStockBatchService skStockBatchService;

    /**
     * 查询库存批次列表
     */
//    @PreAuthorize("@ss.hasPermi('stock:batch:list')")
    @GetMapping("/list")
    public TableDataInfo list(SkStockBatch skStockBatch) {
        startPage();
        List<SkStockBatch> list = skStockBatchService.selectSkStockBatchList(skStockBatch);
        return getDataTable(list);
    }

    /**
     * 获取库存批次详细信息
     */
//    @PreAuthorize("@ss.hasPermi('stock:batch:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(skStockBatchService.selectSkStockBatchById(id));
    }

    /**
     * 新增库存批次
     */
//    @PreAuthorize("@ss.hasPermi('stock:batch:add')")
    @Log(title = "库存批次", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SkStockBatch skStockBatch) {
        return toAjax(skStockBatchService.insertSkStockBatch(skStockBatch));
    }

    /**
     * 修改库存批次
     */
//    @PreAuthorize("@ss.hasPermi('stock:batch:edit')")
    @Log(title = "库存批次", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody SkStockBatch skStockBatch) {
        return toAjax(skStockBatchService.updateSkStockBatch(skStockBatch));
    }

    /**
     * 删除库存批次
     */
//    @PreAuthorize("@ss.hasPermi('stock:batch:remove')")
    @Log(title = "库存批次", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(skStockBatchService.deleteSkStockBatchByIds(ids));
    }

    /**
     * 导出库存批次列表
     */
//    @PreAuthorize("@ss.hasPermi('stock:batch:export')")
    @Log(title = "库存批次", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SkStockBatch skStockBatch)
    {
        List<SkStockBatch> list = skStockBatchService.selectSkStockBatchList(skStockBatch);
        ExcelUtil<SkStockBatch> util = new ExcelUtil<SkStockBatch>(SkStockBatch.class);
        return util.exportExcel(list, "SkStockBatch");
    }
}
