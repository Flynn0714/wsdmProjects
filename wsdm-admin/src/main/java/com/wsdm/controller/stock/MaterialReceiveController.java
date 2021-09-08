package com.wsdm.controller.stock;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.stock.domain.MaterialReceive;
import com.wsdm.stock.domain.MaterialReceiveRequest;
import com.wsdm.stock.service.IMaterialReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物料收货订单信息Controller
 *
 * @author wsdm
 * @date 2021-01-12
 */
@RestController
@RequestMapping("/stock/material")
public class MaterialReceiveController extends BaseController {
    @Autowired
    private IMaterialReceiveService materialReceiveService;

    /**
     * 查询物料收货订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('stock:material:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaterialReceiveRequest materialReceive) {
        startPage();
        List<MaterialReceive> list = materialReceiveService.selectMaterialReceiveList(materialReceive);
        return getDataTable(list);
    }

    /**
     * 导出物料收货订单信息列表
    @PreAuthorize("@ss.hasPermi('stock:material:export')")
    @Log(title = "物料收货订单信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MaterialReceive materialReceive) {
        List<MaterialReceive> list = materialReceiveService.selectMaterialReceiveList(materialReceive);
        ExcelUtil<MaterialReceive> util = new ExcelUtil<MaterialReceive>(MaterialReceive.class);
        return util.exportExcel(list, "material");
    }*/

    /**
     * 获取物料收货订单信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('stock:material:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(materialReceiveService.selectMaterialReceiveById(id));
    }

    /**
     * 新增物料收货订单信息
     */
    @PreAuthorize("@ss.hasPermi('stock:material:add')")
    @Log(title = "物料收货订单信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody MaterialReceive materialReceive) {
        return toAjax(materialReceiveService.insertMaterialReceive(materialReceive));
    }

    /**
     * 修改物料收货订单信息
     */
    @PreAuthorize("@ss.hasPermi('stock:material:edit')")
    @Log(title = "物料收货订单信息", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody MaterialReceive materialReceive) {
        return toAjax(materialReceiveService.updateMaterialReceive(materialReceive));
    }

    /**
     * 修改物料收货订单信息
     */
    @PreAuthorize("@ss.hasPermi('stock:material:approved')")
    @Log(title = "物料收货订单信息", businessType = BusinessType.UPDATE)
    @PutMapping("/approved")
    public AjaxResult approved(@RequestBody MaterialReceive materialReceive) {
        return materialReceiveService.approved(materialReceive);
    }


    /**
     * 删除物料收货订单信息
     */
    @PreAuthorize("@ss.hasPermi('stock:material:remove')")
    @Log(title = "物料收货订单信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(materialReceiveService.deleteMaterialReceiveByIds(ids));
    }

}
