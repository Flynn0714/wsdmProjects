package com.wsdm.controller.pd;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.pd.domain.PdAssetsManageRecord;
import com.wsdm.pd.service.IPdAssetsManageRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资产管理更改记录Controller
 *
 * @author wsdm
 * @date 2021-06-28
 */

@RestController
@RequestMapping("/pd/assetsManageRecord")
public class PdAssetsManageRecordController extends BaseController {
    @Autowired
    private IPdAssetsManageRecordService pdAssetsManageRecordService;

    /**
     * 查询资产管理更改记录列表
     */
    @PreAuthorize("@ss.hasPermi('pd:assetsManageRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(PdAssetsManageRecord pdAssetsManageRecord) {
        startPage();
        List<PdAssetsManageRecord> list = pdAssetsManageRecordService.selectPdAssetsManageRecordList(pdAssetsManageRecord);
        return getDataTable(list);
    }

    /**
     * 获取资产管理更改记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('pd:assetsManageRecord:query')")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(pdAssetsManageRecordService.selectPdAssetsManageRecordById(id));
    }

    /**
     * 新增资产管理更改记录
     */
   // @PreAuthorize("@ss.hasPermi('pd:assetsManageRecord:add')")
    @Log(title = "资产管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PdAssetsManageRecord pdAssetsManageRecord) {
        return toAjax(pdAssetsManageRecordService.insertPdAssetsManageRecord(pdAssetsManageRecord));
    }

    /**
     * 修改资产管理更改记录
     */
    @PreAuthorize("@ss.hasPermi('pd:assetsManageRecord:edit')")
    @Log(title = "资产管理", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody PdAssetsManageRecord pdAssetsManageRecord) {
        return toAjax(pdAssetsManageRecordService.updatePdAssetsManageRecord(pdAssetsManageRecord));
    }

    /**
     * 删除资产管理更改记录
     */
    @PreAuthorize("@ss.hasPermi('pd:assetsManageRecord:remove')")
    @Log(title = "资产管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(pdAssetsManageRecordService.deletePdAssetsManageRecordByIds(ids));
    }
}