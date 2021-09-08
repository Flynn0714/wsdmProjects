package com.wsdm.controller.stock;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.stock.domain.SkStockInventoryDetail;
import com.wsdm.stock.service.ISkStockInventoryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存盘点详情Controller
 *
 * @author wsdm
 * @date 2021-05-25
 */
@RestController
@RequestMapping("/stock/inventoryDetail")
public class SkStockInventoryDetailController extends BaseController
{
    @Autowired
    private ISkStockInventoryDetailService skStockInventoryDetailService;

    /**
     * 查询库存盘点详情列表
     */
//    @PreAuthorize("@ss.hasPermi('stock:inventoryDetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(SkStockInventoryDetail skStockInventoryDetail)
    {
        startPage();
        List<SkStockInventoryDetail> list = skStockInventoryDetailService.selectSkStockInventoryDetailList(
                skStockInventoryDetail);
        return getDataTable(list);
    }

    /**
     * 导出库存盘点详情列表
     */
//    @PreAuthorize("@ss.hasPermi('stock:inventoryDetail:export')")
    @Log(title = "库存盘点详情", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SkStockInventoryDetail skStockInventoryDetail)
    {
        List<SkStockInventoryDetail> list = skStockInventoryDetailService.selectSkStockInventoryDetailList(
                skStockInventoryDetail);
        ExcelUtil<SkStockInventoryDetail> util = new ExcelUtil<SkStockInventoryDetail>(SkStockInventoryDetail.class);
        return util.exportExcel(list, "inventoryDetail");
    }

    /**
     * 获取库存盘点详情详细信息
     */
//    @PreAuthorize("@ss.hasPermi('stock:inventoryDetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(skStockInventoryDetailService.selectSkStockInventoryDetailById(id));
    }

    /**
     * 新增库存盘点详情
     */
//    @PreAuthorize("@ss.hasPermi('stock:inventoryDetail:add')")
    @Log(title = "库存盘点详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SkStockInventoryDetail skStockInventoryDetail)
    {
        return toAjax(skStockInventoryDetailService.insertSkStockInventoryDetail(skStockInventoryDetail));
    }

    /**
     * 修改库存盘点详情
     */
//    @PreAuthorize("@ss.hasPermi('stock:inventoryDetail:edit')")
    @Log(title = "库存盘点详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SkStockInventoryDetail skStockInventoryDetail)
    {
        return toAjax(skStockInventoryDetailService.updateSkStockInventoryDetail(skStockInventoryDetail));
    }

    /**
     * 删除库存盘点详情
     */
//    @PreAuthorize("@ss.hasPermi('stock:inventoryDetail:remove')")
    @Log(title = "库存盘点详情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(skStockInventoryDetailService.deleteSkStockInventoryDetailByIds(ids));
    }
}
