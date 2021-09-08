package com.wsdm.controller.pd;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.pd.domain.PdContributionTest;
import com.wsdm.pd.domain.PdContributionTestVO;
import com.wsdm.pd.service.IPdContributionTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 加工质检单Controller
 *
 * @author wsdm
 * @date 2021-06-18
 */
@RestController
@RequestMapping("/pd/pdContributionTest")
public class PdContributionTestController extends BaseController {
    @Autowired
    private IPdContributionTestService pdContributionTestService;

    /**
     * 查询加工质检单列表
     */
//@PreAuthorize("@ss.hasPermi('pd:pdContributionTest:list')")
    @GetMapping("/list")
    public TableDataInfo list(PdContributionTest pdContributionTest) {
        startPage();
        List<PdContributionTest> list = pdContributionTestService.selectPdContributionTestList(pdContributionTest);
        return getDataTable(list);
    }

    /**
     * 导出加工质检单列表
     */
//    @PreAuthorize("@ss.hasPermi('pd:pdContributionTest:export')")
    @Log(title = "加工质检单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PdContributionTest pdContributionTest) {
        List<PdContributionTest> list = pdContributionTestService.selectPdContributionTestList(pdContributionTest);
        ExcelUtil<PdContributionTest> util = new ExcelUtil<PdContributionTest>(PdContributionTest.class);
        return util.exportExcel(list, "pdContributionTest");
    }

    /**
     * 获取加工质检单详细信息
     */
//    @PreAuthorize("@ss.hasPermi('pd:pdContributionTest:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(pdContributionTestService.selectPdContributionTestById(id));
    }

    /**
     * 修改加工质检单
     */
//    @PreAuthorize("@ss.hasPermi('pd:pdContributionTest:edit')")
    @Log(title = "加工质检单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PdContributionTestVO pdContributionTestVO) {
        pdContributionTestVO.getPdContributionTest().setUpdateBy(SecurityUtils.getUsername());
        return toAjax(pdContributionTestService.updatePdContributionTest(pdContributionTestVO));
    }

    @Log(title = "提交质检审核", businessType = BusinessType.UPDATE)
    @GetMapping("/submit/{id}")
    public AjaxResult submit(@PathVariable Long id) {
        return toAjax(pdContributionTestService.submit(id, SecurityUtils.getUsername()));
    }

    @Log(title = "驳回质检审核", businessType = BusinessType.UPDATE)
    @GetMapping("/reject/{id}")
    public AjaxResult reject(@PathVariable Long id) {
        return toAjax(pdContributionTestService.reject(id, SecurityUtils.getUsername()));
    }

    @Log(title = "通过质检审核", businessType = BusinessType.UPDATE)
    @GetMapping("/pass/{id}")
    public AjaxResult pass(@PathVariable Long id) {
        return toAjax(pdContributionTestService.pass(id, SecurityUtils.getUsername()));
    }
}
