package com.wsdm.controller.sale;

import com.wsdm.common.annotation.Log;
import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.page.TableDataCountInfo;
import com.wsdm.common.enums.BusinessType;
import com.wsdm.common.utils.poi.ExcelUtil;
import com.wsdm.sale.domain.SalesOrderTransactionDetails;
import com.wsdm.sale.domain.SalesOrderTransactionQuery;
import com.wsdm.sale.service.ISalesOrderTransactionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;


/**
 * 销售交易记录Controller
 *
 * @author wsdm
 * @date 2021-07-17
 */
@RestController
@RequestMapping("/sales/transaction")
public class SalesOrderTransactionDetailsController extends BaseController {
    @Autowired
    private ISalesOrderTransactionDetailsService salesOrderTransactionDetailsService;

    /**
     * 查询销售交易记录列表
     */
    @GetMapping("/list")
    public TableDataCountInfo list(SalesOrderTransactionQuery salesOrderTransactionDetails) {
        startPage();
        List<SalesOrderTransactionDetails> list = salesOrderTransactionDetailsService.selectSalesOrderTransactionDetailsList(salesOrderTransactionDetails);
        BigDecimal quantity = salesOrderTransactionDetailsService.getTransactionQuantity(salesOrderTransactionDetails);
        BigDecimal amount = salesOrderTransactionDetailsService.getTransactionAmount(salesOrderTransactionDetails);
        TableDataCountInfo info = getDataTableCount(list);
        info.setQuantity(quantity);
        info.setAmount(amount);
        return info;
    }

    /**
     * 导出销售交易记录列表
     */
    @Log(title = "销售交易记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(SalesOrderTransactionQuery salesOrderTransactionDetails, HttpServletResponse response) {
        List<SalesOrderTransactionDetails> list = salesOrderTransactionDetailsService.selectSalesOrderTransactionDetailsList(salesOrderTransactionDetails);
        ExcelUtil<SalesOrderTransactionDetails> util = new ExcelUtil<SalesOrderTransactionDetails>(SalesOrderTransactionDetails.class);
        util.exportExcel(list, "销售交易记录列表", response);
    }

}
