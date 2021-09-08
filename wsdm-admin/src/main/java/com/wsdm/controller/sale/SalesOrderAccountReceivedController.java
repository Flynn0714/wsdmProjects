package com.wsdm.controller.sale;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.sale.domain.SalesOrderAccountReceived;
import com.wsdm.sale.domain.SalesOrderAccountReceivedQuery;
import com.wsdm.sale.service.ISalesOrderAccountReceivedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 收款记录Controller
 *
 * @author wsdm
 * @date 2021-07-17
 */
@RestController
@RequestMapping("/sales/received")
public class SalesOrderAccountReceivedController extends BaseController {
    @Autowired
    private ISalesOrderAccountReceivedService salesOrderAccountReceivedService;

    /**
     * 查询收款记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SalesOrderAccountReceivedQuery salesOrderAccountReceived) {
        startPage();
        List<SalesOrderAccountReceived> list = salesOrderAccountReceivedService.selectSalesOrderAccountReceivedList(salesOrderAccountReceived);
        return getDataTable(list);
    }

    /**
     * 导出收款记录列表
     */
    @Log(title = "收款记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(SalesOrderAccountReceivedQuery salesOrderAccountReceived, HttpServletResponse response) {
        List<SalesOrderAccountReceived> list = salesOrderAccountReceivedService.selectSalesOrderAccountReceivedList(salesOrderAccountReceived);
        ExcelUtil<SalesOrderAccountReceived> util = new ExcelUtil<SalesOrderAccountReceived>(SalesOrderAccountReceived.class);
        util.exportExcel(list, "销售收款记录列表", response);
    }


    /**
     * 新增收款记录
     */
    @Log(title = "收款记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody List<SalesOrderAccountReceived> salesOrderAccountReceivedList) {
        for (SalesOrderAccountReceived accountReceived : salesOrderAccountReceivedList) {
            accountReceived.setGatheringUser(SecurityUtils.getUsername());
        }
        return toAjax(salesOrderAccountReceivedService.insertSalesOrderAccountReceivedBatch(salesOrderAccountReceivedList));
    }
}
