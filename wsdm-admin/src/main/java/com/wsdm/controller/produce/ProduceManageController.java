package com.wsdm.controller.produce;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.produce.domain.ProduceManage;
import com.wsdm.produce.domain.ProduceManageVO;
import com.wsdm.produce.service.IProduceManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 产品管理Controller
 *
 * @author wsdm
 * @date 2020-11-06
 */
@RestController
@RequestMapping("/produce/manage")
public class ProduceManageController extends BaseController {
    @Autowired
    private IProduceManageService produceManageService;

    /**
     * 查询产品管理列表
     */
    @PreAuthorize("@ss.hasPermi('produce:manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProduceManage produceManage) {
        startPage();
        List<ProduceManage> list = produceManageService.selectProduceManageList(produceManage);
        return getDataTable(list);
    }

    /**
     *
     * 引入标准产品
     * @author wangr
     * @date 2020/11/19 19:11
     * @return com.wsdm.common.core.domain.AjaxResult
     */
    @GetMapping("/introduce")
    public AjaxResult introduceProduce(ProduceManage produceManage) {
        return produceManageService.introduceProduce(produceManage);
    }

    /**
     * 获取产品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('produce:manage:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(produceManageService.selectProduceManageVoById(id));
    }

    /**
     * 新增产品管理
     */
    @PreAuthorize("@ss.hasPermi('produce:manage:add')")
    @Log(title = "产品管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody ProduceManageVO produceManage) {
        return produceManageService.insertProduceManage(produceManage);
    }

    /**
     * 修改产品管理
     */
    @PreAuthorize("@ss.hasPermi('produce:manage:edit')")
    @Log(title = "产品管理", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody ProduceManageVO produceManage) {
        return produceManageService.updateProduceManage(produceManage);
    }

    /**
     * 删除产品管理
     */
    @PreAuthorize("@ss.hasPermi('produce:manage:remove')")
    @Log(title = "产品管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(produceManageService.deleteProduceManageByIds(ids));
    }

    /**
     * 导出产品管理列表
    @PreAuthorize("@ss.hasPermi('produce:manage:export')")
    @Log(title = "产品管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProduceManage produceManage) {
        List<ProduceManage> list = produceManageService.selectProduceManageList(produceManage);
        ExcelUtil<ProduceManage> util = new ExcelUtil<ProduceManage>(ProduceManage.class);
        return util.exportExcel(list, "manage");
    }*/
}
