package com.wsdm.controller.pd;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.pd.domain.PdProduceManage;
import com.wsdm.pd.domain.PdProduceManageVO;
import com.wsdm.pd.service.IPdProduceManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 产品管理Controller
 *
 * @author wsdm
 * @date 2021-06-21
 */
@RestController
@RequestMapping("/pd/pdProduceManage")
public class PdProduceManageController extends BaseController {
    @Autowired
    private IPdProduceManageService pdProduceManageService;

    /**
     * 查询产品管理列表
     */
//@PreAuthorize("@ss.hasPermi('system:manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(PdProduceManage pdProduceManage) {
        startPage();
        List<PdProduceManage> list = pdProduceManageService.selectPdProduceManageList(pdProduceManage);
        return getDataTable(list);
    }

    /**
     * 导出产品管理列表
     */
//    @PreAuthorize("@ss.hasPermi('system:manage:export')")
    @Log(title = "产品管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PdProduceManage pdProduceManage) {
        List<PdProduceManage> list = pdProduceManageService.selectPdProduceManageList(pdProduceManage);
        ExcelUtil<PdProduceManage> util = new ExcelUtil<PdProduceManage>(PdProduceManage.class);
        return util.exportExcel(list, "manage");
    }

    /**
     * 获取产品管理详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:manage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(pdProduceManageService.selectPdProduceManageById(id));
    }

    /**
     * 新增产品管理
     */
//    @PreAuthorize("@ss.hasPermi('system:manage:add')")
    @Log(title = "产品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PdProduceManageVO pdProduceManage) {
        pdProduceManage.getPdProduceManage().setCreateBy(SecurityUtils.getUsername());
        return AjaxResult.success(pdProduceManageService.insertPdProduceManage(pdProduceManage));
    }

    /**
     * 修改产品管理
     */
//    @PreAuthorize("@ss.hasPermi('system:manage:edit')")
    @Log(title = "产品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PdProduceManageVO pdProduceManage) {
        pdProduceManage.getPdProduceManage().setCreateBy(SecurityUtils.getUsername());
        return toAjax(pdProduceManageService.updatePdProduceManage(pdProduceManage));
    }

    /**
     * 删除产品管理
     */
//    @PreAuthorize("@ss.hasPermi('system:manage:remove')")
    @Log(title = "产品管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(pdProduceManageService.deletePdProduceManageByIds(ids));
    }

    //    @PreAuthorize("@ss.hasPermi('pd:materialReceiving:deleteSafe')")
    @Log(title = "安全删除产品", businessType = BusinessType.DELETE)
    @DeleteMapping("/deleteSafe/{id}")
    public AjaxResult deleteSafe(@PathVariable Long id) {
        return toAjax(pdProduceManageService.deleteSafe(id, SecurityUtils.getUsername()));
    }

    //    @PreAuthorize("@ss.hasPermi('pd:materialReceiving:recover')")
    @Log(title = "恢复产品", businessType = BusinessType.UPDATE)
    @GetMapping("/recover/{id}")
    public AjaxResult recover(@PathVariable Long id) {
        return toAjax(pdProduceManageService.recover(id, SecurityUtils.getUsername()));
    }
}
