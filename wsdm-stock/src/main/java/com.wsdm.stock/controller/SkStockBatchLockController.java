package com.wsdm.stock.controller;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.stock.domain.SkStockBatchLock;
import com.wsdm.stock.service.ISkStockBatchLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存批次锁定记录Controller
 *
 * @author wsdm
 * @date 2020-12-07
 */
@RestController
@RequestMapping("/stock/lock")
public class SkStockBatchLockController extends BaseController {
    @Autowired
    private ISkStockBatchLockService skStockBatchLockService;

    /**
     * 查询库存批次锁定记录列表
     */
    @PreAuthorize("@ss.hasPermi('stock:lock:list')")
    @GetMapping("/list")
    public TableDataInfo list(SkStockBatchLock skStockBatchLock) {
        startPage();
        List<SkStockBatchLock> list = skStockBatchLockService.selectSkStockBatchLockList(skStockBatchLock);
        return getDataTable(list);
    }

    /**
     * 获取库存批次锁定记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('stock:lock:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(skStockBatchLockService.selectSkStockBatchLockById(id));
    }

    /**
     * 新增库存批次锁定记录
     */
    @PreAuthorize("@ss.hasPermi('stock:lock:add')")
    @Log(title = "库存批次锁定记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SkStockBatchLock skStockBatchLock) {
        return toAjax(skStockBatchLockService.insertSkStockBatchLock(skStockBatchLock));
    }

    /**
     * 修改库存批次锁定记录
     */
    @PreAuthorize("@ss.hasPermi('stock:lock:edit')")
    @Log(title = "库存批次锁定记录", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody SkStockBatchLock skStockBatchLock) {
        return toAjax(skStockBatchLockService.updateSkStockBatchLock(skStockBatchLock));
    }

    /**
     * 删除库存批次锁定记录
     */
    @PreAuthorize("@ss.hasPermi('stock:lock:remove')")
    @Log(title = "库存批次锁定记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(skStockBatchLockService.deleteSkStockBatchLockByIds(ids));
    }
}
