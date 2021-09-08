package com.wsdm.controller.stock;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.stock.domain.SkStock;
import com.wsdm.stock.domain.StockInventoryInfo;
import com.wsdm.stock.domain.StockInventoryInfoVo;
import com.wsdm.stock.service.ISkStockService;
import com.wsdm.stock.service.IStockInventoryManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 库存盘点信息Controller
 *
 * @author wsdm
 * @date 2021-01-22
 */
@RestController
@RequestMapping("/stock/inventory1")
public class StockInventoryController extends BaseController {
    @Autowired
    private IStockInventoryManageService stockInventoryManageService;
    @Autowired
    private ISkStockService skStockService;
    /**
     * 查询库存盘点信息列表
     */
    @PreAuthorize("@ss.hasPermi('stock:inventory:list')")
    @GetMapping("/list")
    public TableDataInfo list(StockInventoryInfoVo stockInventoryInfo) {
        startPage();
        List<StockInventoryInfo> list = stockInventoryManageService.selectStockInventoryInfoList(stockInventoryInfo);
        return getDataTable(list);
    }


    /**
     * 查询库存主列表
     */
    @PreAuthorize("@ss.hasPermi('stock:inventory:stockList')")
    @GetMapping("/stockList")
    public TableDataInfo stockList(SkStock skStock) {
        startPage();
        List<SkStock> list = skStockService.selectSkStockList(skStock);
        return getDataTable(list);
    }

    /*    *//**
     * 导出库存盘点信息列表
     *//*
    @PreAuthorize("@ss.hasPermi('stock:inventory:export')")
    @Log(title = "库存盘点信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StockInventoryInfo stockInventoryInfo) {
        List<StockInventoryInfo> list = stockInventoryManageService.selectStockInventoryInfoList(stockInventoryInfo);
        ExcelUtil<StockInventoryInfo> util = new ExcelUtil<StockInventoryInfo>(StockInventoryInfo.class);
        return util.exportExcel(list, "info");
    }*/

    /**
     * 获取库存盘点信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('stock:inventory:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(stockInventoryManageService.selectStockInventoryInfoById(id));
    }

    /**
     * 新增库存盘点信息
     */
    @PreAuthorize("@ss.hasPermi('stock:inventory:add')")
    @Log(title = "库存盘点信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody StockInventoryInfoVo stockInventoryInfo) {
        return stockInventoryManageService.insertStockInventoryInfo(stockInventoryInfo);
    }

    /**
     * 修改库存盘点信息
     */
    @PreAuthorize("@ss.hasPermi('stock:inventory:edit')")
    @Log(title = "库存盘点信息", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody StockInventoryInfoVo stockInventoryInfo) {
        return stockInventoryManageService.updateStockInventoryInfo(stockInventoryInfo);
    }

    /**
     * 删除库存盘点信息
     */
    @PreAuthorize("@ss.hasPermi('stock:inventory:remove')")
    @Log(title = "库存盘点信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return stockInventoryManageService.deleteStockInventoryInfoByIds(ids);
    }
}
