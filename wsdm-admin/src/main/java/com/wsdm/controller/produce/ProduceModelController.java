package com.wsdm.controller.produce;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.produce.domain.ProduceModel;
import com.wsdm.produce.service.IProduceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 产品型号Controller
 *
 * @author wsdm
 * @date 2020-11-06
 */
@RestController
@RequestMapping("/produce/model")
public class ProduceModelController extends BaseController {
    @Autowired
    private IProduceModelService produceModelService;

    /**
     * 查询产品型号列表
     */
    @PreAuthorize("@ss.hasPermi('produce:model:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProduceModel produceModel) {
        startPage();
        List<ProduceModel> list = produceModelService.selectProduceModelList(produceModel);
        return getDataTable(list);
    }

    /**
     * 获取产品型号详细信息
     */
    @PreAuthorize("@ss.hasPermi('produce:model:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(produceModelService.selectProduceModelById(id));
    }

    /**
     * 新增产品型号
     */
    @PreAuthorize("@ss.hasPermi('produce:model:add')")
    @Log(title = "产品型号", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody ProduceModel produceModel) {
        return toAjax(produceModelService.insertProduceModel(produceModel));
    }

    /**
     * 修改产品型号
     */
    @PreAuthorize("@ss.hasPermi('produce:model:edit')")
    @Log(title = "产品型号", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody ProduceModel produceModel) {
        return toAjax(produceModelService.updateProduceModel(produceModel));
    }

    /**
     * 删除产品型号
     */
    @PreAuthorize("@ss.hasPermi('produce:model:remove')")
    @Log(title = "产品型号", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(produceModelService.deleteProduceModelByIds(ids));
    }


    /**
     * 导出产品型号列表

    @PreAuthorize("@ss.hasPermi('produce:model:export')")
    @Log(title = "产品型号", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProduceModel produceModel) {
        List<ProduceModel> list = produceModelService.selectProduceModelList(produceModel);
        ExcelUtil<ProduceModel> util = new ExcelUtil<ProduceModel>(ProduceModel.class);
        return util.exportExcel(list, "model");
    }*/
}
