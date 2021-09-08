package com.wsdm.sale.service.impl;

import com.wsdm.common.CodeUtils;
import com.wsdm.common.constant.Constants;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.sale.domain.SalesOrderBilling;
import com.wsdm.sale.domain.SalesOrderBillingQuery;
import com.wsdm.sale.domain.SalesOrderGathering;
import com.wsdm.sale.domain.SalesOrderGatheringDetails;
import com.wsdm.sale.mapper.SalesOrderBillingMapper;
import com.wsdm.sale.mapper.SalesOrderGatheringMapper;
import com.wsdm.sale.service.ISalesOrderBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 销售单开票Service业务层处理
 *
 * @author wsdm
 * @date 2021-04-27
 */
@Service
public class SalesOrderBillingServiceImpl implements ISalesOrderBillingService {
    @Autowired
    private SalesOrderBillingMapper salesOrderBillingMapper;

    @Autowired
    private SalesOrderGatheringMapper salesOrderGatheringMapper;

    /**
     * 查询销售单开票
     *
     * @param id 销售单开票ID
     * @return 销售单开票
     */
    @Override
    public SalesOrderBilling selectSalesOrderBillingById(Long id) {
        SalesOrderBilling salesOrderBilling = salesOrderBillingMapper.selectSalesOrderBillingById(id);
        if (salesOrderBilling == null) {
            return null;
        }
        List<SalesOrderGatheringDetails> list = salesOrderGatheringMapper.getGatheringDetailsByNumber(salesOrderBilling.getGatheringNumber());
        salesOrderBilling.setGatheringDetailsList(list);
        return salesOrderBilling;
    }

    /**
     * 查询销售单开票列表
     *
     * @param salesOrderBilling 销售单开票
     * @return 销售单开票
     */
    @Override
    public List<SalesOrderBilling> selectSalesOrderBillingList(SalesOrderBillingQuery salesOrderBilling) {
        return salesOrderBillingMapper.selectSalesOrderBillingList(salesOrderBilling);
    }

    @Override
    public List<SalesOrderGatheringDetails> getGatheringDetailsList(String gatheringNumber) {
        return salesOrderGatheringMapper.getGatheringDetailsByNumber(gatheringNumber);
    }

    /**
     * 新增销售单开票
     *
     * @param salesOrderBilling 销售单开票
     * @return 结果
     */
    @Override
    public int insertSalesOrderBilling(SalesOrderBilling salesOrderBilling) {
        String code = Constants.SALES_BILLING_KP + DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date());
        String maxCode = salesOrderBillingMapper.getMaxBillingCode(code);
        String billingCode = CodeUtils.getOneCode(code, maxCode, 3);

        salesOrderBilling.setBillingNumber(billingCode);
        salesOrderBilling.setCreateTime(DateUtils.getNowDate());
        salesOrderBilling.setCreateBy(SecurityUtils.getUsername());
        return salesOrderBillingMapper.insertSalesOrderBilling(salesOrderBilling);
    }

    /**
     * 修改销售单开票
     *
     * @param salesOrderBilling 销售单开票
     * @return 结果
     */
    @Override
    public int updateSalesOrderBilling(SalesOrderBilling salesOrderBilling) {
        salesOrderBilling.setUpdateTime(DateUtils.getNowDate());
        salesOrderBilling.setUpdateBy(SecurityUtils.getUsername());
        return salesOrderBillingMapper.updateSalesOrderBilling(salesOrderBilling);
    }

    /**
     * 删除销售单开票信息
     *
     * @param id 销售单开票ID
     * @return 结果
     */
    @Override
    public int deleteSalesOrderBillingById(Long id) {
        return salesOrderBillingMapper.deleteSalesOrderBillingById(id);
    }

    @Override
    public AjaxResult approve(Long id, String action) {
        return null;
    }
}
