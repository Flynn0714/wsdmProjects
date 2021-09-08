package com.wsdm.stock.controller;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.stock.domain.SkStock;
import com.wsdm.stock.service.ISkStockService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 库存主Controller
 *
 * @author wsdm
 * @date 2020-12-07
 */
@RestController
@RequestMapping("/stock/manage")
public class SkStockController extends BaseController {
    @Autowired
    private ISkStockService skStockService;

    /**
     * 查询库存主列表
     */
//    @PreAuthorize("@ss.hasPermi('stock:manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(SkStock skStock) {
        startPage();
        List<SkStock> list = skStockService.selectSkStockList(skStock);
        return getDataTable(list);
    }

    /**
     * 获取库存主详细信息
     */
//    @PreAuthorize("@ss.hasPermi('stock:manage:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(skStockService.selectSkStockById(id));
    }

    /**
     * 新增库存主
     */
//    @PreAuthorize("@ss.hasPermi('stock:manage:add')")
    @Log(title = "库存主", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SkStock skStock) {
        return toAjax(skStockService.insertSkStock(skStock));
    }

    /**
     * 修改库存主
     */
//    @PreAuthorize("@ss.hasPermi('stock:manage:edit')")
    @Log(title = "库存主", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody SkStock skStock) {
        return toAjax(skStockService.updateSkStock(skStock));
    }

    /**
     * 删除库存主
     */
//    @PreAuthorize("@ss.hasPermi('stock:manage:remove')")
    @Log(title = "库存主", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(skStockService.deleteSkStockByIds(ids));
    }

    /**
     * 导出库存列表
     */
//    @PreAuthorize("@ss.hasPermi('stock:manage:export')")
    @Log(title = "库存主", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SkStock skStock) {
        List<SkStock> list = skStockService.selectSkStockList(skStock);
        ExcelUtil<SkStock> util = new ExcelUtil<SkStock>(SkStock.class);
        return util.exportExcel(list, "skStock");
    }

    @PostMapping("/batchQueryByProduce")
    public AjaxResult batchQueryByProduce(@RequestBody List<String> produceList) {
        List<SkStock> stockList = skStockService.batchQueryByProduce(produceList);
        Map<String, SkStock> map = new HashMap<>();
        if (CollectionUtils.isNotEmpty(stockList)) {
            for (SkStock stock : stockList) {
                map.put(stock.getProduce(), stock);
            }
        }
        return AjaxResult.success(map);
    }
}
