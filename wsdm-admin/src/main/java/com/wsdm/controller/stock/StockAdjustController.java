package com.wsdm.controller.stock;


import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.stock.domain.SkStockBatch;
import com.wsdm.stock.domain.StockAdjustInfo;
import com.wsdm.stock.domain.StockAdjustInfoVo;
import com.wsdm.stock.service.ISkStockBatchService;
import com.wsdm.stock.service.IStockAdjustManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存调整信息Controller
 *
 * @author wsdm
 * @date 2021-01-20
 */
@RestController
@RequestMapping("/stock/adjust")
public class StockAdjustController extends BaseController {
    @Autowired
    private IStockAdjustManageService stockAdjustManageService;
    @Autowired
    private ISkStockBatchService skStockBatchService;

    /**
     * 查询库存调整信息列表
     */
//    @PreAuthorize("@ss.hasPermi('stock:adjust:list')")
    @GetMapping("/list")
    public TableDataInfo list(StockAdjustInfoVo stockAdjustInfo) {
        startPage();
        List<StockAdjustInfo> list = stockAdjustManageService.selectStockAdjustInfoList(stockAdjustInfo);
        return getDataTable(list);
    }

    /**
     * 查询库存批次信息列表
     */
//    @PreAuthorize("@ss.hasPermi('stock:adjust:batchList')")
    @GetMapping("/batchList")
    public TableDataInfo batchList(SkStockBatch skStockBatch) {
        startPage();
        List<SkStockBatch> list = skStockBatchService.selectSkStockBatchList(skStockBatch);
        return getDataTable(list);
    }

    /*    *//**
     * 导出库存调整信息列表
     *//*
    @PreAuthorize("@ss.hasPermi('stock:adjust:export')")
    @Log(title = "库存调整信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StockAdjustInfo StockAdjustInfo) {
        List<StockAdjustInfo> list = stockAdjustManageService.selectStockAdjustInfoList(stockAdjustInfo);
        ExcelUtil<StockAdjustInfo> utils = new ExcelUtil<StockAdjustInfo>(StockAdjustInfo.class);
        return utils.exportExcel(list, "info");
    }*/

    /**
     * 获取库存调整信息详细信息
     */
//    @PreAuthorize("@ss.hasPermi('stock:adjust:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(stockAdjustManageService.selectStockAdjustInfoById(id));
    }

    /**
     * 新增库存调整信息
     */
//    @PreAuthorize("@ss.hasPermi('stock:adjust:add')")
    @Log(title = "库存调整信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody StockAdjustInfoVo stockAdjustInfo) {
        return stockAdjustManageService.insertStockAdjustInfo(stockAdjustInfo);
    }

    /**
     * 修改库存调整信息
     */
//    @PreAuthorize("@ss.hasPermi('stock:adjust:edit')")
    @Log(title = "库存调整信息", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody StockAdjustInfoVo stockAdjustInfo) {
        return stockAdjustManageService.updateStockAdjustInfo(stockAdjustInfo);
    }

    /**
     * 删除库存调整信息
     */
//    @PreAuthorize("@ss.hasPermi('stock:adjust:remove')")
    @Log(title = "库存调整信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return stockAdjustManageService.deleteStockAdjustInfoByIds(ids);
    }
}
