package com.wsdm.controller.sale;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.sale.domain.SalesOrderCollection;
import com.wsdm.sale.service.ISalesOrderCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 收款汇总Controller
 *
 * @author wsdm
 * @date 2021-07-17
 */
@RestController
@RequestMapping("/sales/collection")
public class SalesOrderCollectionController extends BaseController {
    @Autowired
    private ISalesOrderCollectionService salesOrderCollectionService;

    /**
     * 查询收款汇总列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SalesOrderCollection salesOrderCollection) {
        startPage();
        List<SalesOrderCollection> list = salesOrderCollectionService.selectSalesOrderCollectionList(salesOrderCollection);
        return getDataTable(list);
    }

    /**
     * 导出收款汇总列表
     */
    @Log(title = "收款汇总", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(SalesOrderCollection salesOrderCollection, HttpServletResponse response) {
        List<SalesOrderCollection> list = salesOrderCollectionService.selectSalesOrderCollectionList(salesOrderCollection);
        ExcelUtil<SalesOrderCollection> util = new ExcelUtil<SalesOrderCollection>(SalesOrderCollection.class);
        util.exportExcel(list, "收款汇总列表", response);
    }

    /**
     * 获取收款汇总详细信息
     */
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(salesOrderCollectionService.selectSalesOrderCollectionById(id));
    }

}
