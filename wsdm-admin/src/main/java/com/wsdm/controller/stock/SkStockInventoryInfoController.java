package com.wsdm.controller.stock;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.stock.domain.SkStockInventoryDetail;
import com.wsdm.stock.domain.SkStockInventoryInfo;
import com.wsdm.stock.domain.SkStockInventoryInfoVO;
import com.wsdm.stock.service.ISkStockInventoryInfoService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * 库存盘点信息Controller
 *
 * @author wsdm
 * @date 2021-05-25
 */
@RestController
@RequestMapping("/stock/inventory")
public class SkStockInventoryInfoController extends BaseController
{
    @Autowired
    private ISkStockInventoryInfoService skStockInventoryInfoService;

    /**
     * 查询库存盘点信息列表
     */
//    @PreAuthorize("@ss.hasPermi('stock:inventory:list')")
    @GetMapping("/list")
    public TableDataInfo list(SkStockInventoryInfo skStockInventoryInfo)
    {
        startPage();
        List<SkStockInventoryInfo> list = skStockInventoryInfoService.selectSkStockInventoryInfoList(
                skStockInventoryInfo);
        return getDataTable(list);
    }

    /**
     * 导出库存盘点信息列表
     */
//    @PreAuthorize("@ss.hasPermi('stock:inventory:export')")
    @Log(title = "库存盘点信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SkStockInventoryInfo skStockInventoryInfo)
    {
        List<SkStockInventoryInfo> list = skStockInventoryInfoService.selectSkStockInventoryInfoList(
                skStockInventoryInfo);
        ExcelUtil<SkStockInventoryInfo> util = new ExcelUtil<SkStockInventoryInfo>(SkStockInventoryInfo.class);
        return util.exportExcel(list, "inventory");
    }

    /**
     * 获取库存盘点信息详细信息
     */
//    @PreAuthorize("@ss.hasPermi('stock:inventory:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(skStockInventoryInfoService.selectSkStockInventoryInfoById(id));
    }

    /**
     * 新增库存盘点信息
     */
//    @PreAuthorize("@ss.hasPermi('stock:inventory:add')")
    @Log(title = "库存盘点信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SkStockInventoryInfoVO vo)
    {
        vo.getSkStockInventoryInfo().setCreateBy(SecurityUtils.getUsername());
        return toAjax(skStockInventoryInfoService.insertSkStockInventoryInfo(vo));
    }

    /**
     * 修改库存盘点信息
     */
//    @PreAuthorize("@ss.hasPermi('stock:inventory:edit')")
    @Log(title = "库存盘点信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SkStockInventoryInfoVO vo)
    {
        vo.getSkStockInventoryInfo().setUpdateBy(SecurityUtils.getUsername());
        return toAjax(skStockInventoryInfoService.updateSkStockInventoryInfo(vo));
    }

    /**
     * 删除库存盘点信息
     */
//    @PreAuthorize("@ss.hasPermi('stock:inventory:remove')")
    @Log(title = "库存盘点信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(skStockInventoryInfoService.deleteSkStockInventoryInfoByIds(ids));
    }

    /**
     * 逻辑删除库存盘点
     */
//    @PreAuthorize("@ss.hasPermi('pd:contribution:deleteSafe')")
    @Log(title = "安全删除库存盘点", businessType = BusinessType.DELETE)
    @GetMapping("/deleteSafe/{id}")
    public AjaxResult deleteSafe(@PathVariable Long id)
    {
        return toAjax(skStockInventoryInfoService.deleteSafe(id, SecurityUtils.getUsername()));
    }

    /**
     * 上传物料
     */
    @PostMapping("/uploadProduce")
    public AjaxResult uploadProduce(MultipartFile file) throws Exception
    {
        InputStream stream = file.getInputStream();
        try
        {
            return AjaxResult.success(new ExcelUtil<>(SkStockInventoryDetail.class).importExcel(stream));
        }
        finally
        {
            IOUtils.closeQuietly(stream);
        }
    }
}
