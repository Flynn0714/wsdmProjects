package com.wsdm.controller.produce;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.produce.domain.FittingsMange;
import com.wsdm.produce.service.IFittingsMangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 配件管理Controller
 *
 * @author wsdm
 * @date 2020-11-06
 */
@RestController
@RequestMapping("/produce/fittings")
public class FittingsMangeController extends BaseController {
    @Autowired
    private IFittingsMangeService fittingsMangeService;

    /**
     * 查询配件管理列表
     */
    @PreAuthorize("@ss.hasPermi('produce:fittings:list')")
    @GetMapping("/list")
    public TableDataInfo list(FittingsMange fittingsMange) {
        startPage();
        List<FittingsMange> list = fittingsMangeService.selectFittingsMangeList(fittingsMange);
        return getDataTable(list);
    }

    /**
     * 获取配件管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('produce:fittings:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(fittingsMangeService.selectFittingsMangeById(id));
    }

    /**
     * 新增配件管理
     */
    @PreAuthorize("@ss.hasPermi('produce:fittings:add')")
    @Log(title = "配件管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody FittingsMange fittingsMange) {
        return fittingsMangeService.insertFittingsMange(fittingsMange);
    }

    /**
     * 修改配件管理
     */
    @PreAuthorize("@ss.hasPermi('produce:fittings:edit')")
    @Log(title = "配件管理", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody FittingsMange fittingsMange) {
        return fittingsMangeService.updateFittingsMange(fittingsMange);
    }

    /**
     * 删除配件管理
     */
    @PreAuthorize("@ss.hasPermi('produce:fittings:remove')")
    @Log(title = "配件管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(fittingsMangeService.deleteFittingsMangeByIds(ids));
    }



    /**
     * 导出配件管理列表
    @PreAuthorize("@ss.hasPermi('produce:fittings:export')")
    @Log(title = "配件管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FittingsMange fittingsMange) {
        List<FittingsMange> list = fittingsMangeService.selectFittingsMangeList(fittingsMange);
        ExcelUtil<FittingsMange> util = new ExcelUtil<FittingsMange>(FittingsMange.class);
        return util.exportExcel(list, "fittings");
    }*/
}
