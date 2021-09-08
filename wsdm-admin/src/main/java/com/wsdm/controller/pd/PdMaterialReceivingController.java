package com.wsdm.controller.pd;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.pd.domain.PdMaterialReceiving;
import com.wsdm.pd.domain.PdMaterialReceivingVO;
import com.wsdm.pd.service.IPdMaterialReceivingDetailService;
import com.wsdm.pd.service.IPdMaterialReceivingService;
import com.wsdm.stock.domain.SkStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物料领取单Controller
 *
 * @author wsdm
 * @date 2021-05-11
 */
@RestController
@RequestMapping("/pd/materialReceiving")
public class PdMaterialReceivingController extends BaseController
{
    @Autowired
    private IPdMaterialReceivingService pdMaterialReceivingService;

    @Autowired
    private IPdMaterialReceivingDetailService pdMaterialReceivingDetailService;

    /**
     * 查询物料领取单列表
     */
//    @PreAuthorize("@ss.hasPermi('pd:materialReceiving:list')")
    @GetMapping("/list")
    public TableDataInfo list(PdMaterialReceiving pdMaterialReceiving)
    {
        startPage();
        List<PdMaterialReceiving> list = pdMaterialReceivingService.selectPdMaterialReceivingList(pdMaterialReceiving);
        return getDataTable(list);
    }

    /**
     * 导出物料领取单列表
     */
//    @PreAuthorize("@ss.hasPermi('pd:materialReceiving:export')")
    @Log(title = "物料领取单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PdMaterialReceiving pdMaterialReceiving)
    {
        List<PdMaterialReceiving> list = pdMaterialReceivingService.selectPdMaterialReceivingList(pdMaterialReceiving);
        ExcelUtil<PdMaterialReceiving> util = new ExcelUtil<PdMaterialReceiving>(PdMaterialReceiving.class);
        return util.exportExcel(list, "receiving");
    }

    /**
     * 获取物料领取单详细信息
     */
//    @PreAuthorize("@ss.hasPermi('pd:materialReceiving:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(pdMaterialReceivingService.selectPdMaterialReceivingById(id));
    }

    /**
     * 新增物料领取单
     */
//    @PreAuthorize("@ss.hasPermi('pd:materialReceiving:add')")
    @Log(title = "物料领取单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PdMaterialReceivingVO vo)
    {
        vo.getPdMaterialReceiving().setCreateBy(SecurityUtils.getUsername());
        return toAjax(pdMaterialReceivingService.insertPdMaterialReceiving(vo));
    }

    /**
     * 修改物料领取单
     */
//    @PreAuthorize("@ss.hasPermi('pd:materialReceiving:edit')")
    @Log(title = "物料领取单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PdMaterialReceivingVO vo)
    {
        vo.getPdMaterialReceiving().setUpdateBy(SecurityUtils.getUsername());
        return toAjax(pdMaterialReceivingService.updatePdMaterialReceiving(vo));
    }

    /**
     * 删除物料领取单
     */
//    @PreAuthorize("@ss.hasPermi('pd:materialReceiving:remove')")
    @Log(title = "物料领取单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pdMaterialReceivingService.deletePdMaterialReceivingByIds(ids));
    }

    /**
     * 逻辑删除物料领取单
     */
//    @PreAuthorize("@ss.hasPermi('pd:materialReceiving:deleteSafe')")
    @Log(title = "安全删除物料领取单", businessType = BusinessType.DELETE)
    @GetMapping("/deleteSafe/{id}")
    public AjaxResult deleteSafe(@PathVariable Long id)
    {
        return toAjax(pdMaterialReceivingService.deleteSafe(id, SecurityUtils.getUsername()));
    }

//    /**
//     * 逻辑恢复物料领取单
//     */
////    @PreAuthorize("@ss.hasPermi('pd:materialReceiving:recover')")
//    @Log(title = "恢复物料领取单", businessType = BusinessType.UPDATE)
//    @GetMapping("/recover/{id}")
//    public AjaxResult recover(@PathVariable Long id)
//    {
//        return toAjax(pdMaterialReceivingService.recover(id, SecurityUtils.getUsername()));
//    }

    /**
     * 逻辑恢复物料领取单
     */
//    @PreAuthorize("@ss.hasPermi('pd:materialReceiving:recover')")
    @Log(title = "提交审核", businessType = BusinessType.UPDATE)
    @GetMapping("/submit/{id}")
    public AjaxResult submit(@PathVariable Long id)
    {
        return toAjax(pdMaterialReceivingService.submit(id, SecurityUtils.getUsername()));
    }

    @Log(title = "审核通过", businessType = BusinessType.UPDATE)
    @GetMapping("/pass/{id}")
    public AjaxResult pass(@PathVariable Long id)
    {
        return toAjax(pdMaterialReceivingService.pass(id, SecurityUtils.getUsername()));
    }

    @Log(title = "审核驳回", businessType = BusinessType.UPDATE)
    @GetMapping("/reject/{id}")
    public AjaxResult reject(@PathVariable Long id)
    {
        return toAjax(pdMaterialReceivingService.reject(id, SecurityUtils.getUsername()));
    }

    @GetMapping("/listAvailableMaterial")
    public TableDataInfo listAvailableMaterial(SkStock stock)
    {
        startPage();
        List<SkStock> list = pdMaterialReceivingService.selectAvailableMaterial(stock);
        return getDataTable(list);
    }
}
