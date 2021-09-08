package com.wsdm.sale.service.impl;

import com.wsdm.common.enums.AccountReceivedType;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.sale.domain.SalesOrderAccountReceived;
import com.wsdm.sale.domain.SalesOrderAccountReceivedQuery;
import com.wsdm.sale.mapper.SalesOrderAccountReceivedMapper;
import com.wsdm.sale.service.ISalesOrderAccountReceivedService;
import com.wsdm.sale.service.ISalesOrderCollectionService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 收款记录Service业务层处理
 *
 * @author wsdm
 * @date 2021-07-17
 */
@Service
public class SalesOrderAccountReceivedServiceImpl implements ISalesOrderAccountReceivedService {
    @Autowired
    private SalesOrderAccountReceivedMapper salesOrderAccountReceivedMapper;

    @Autowired
    private ISalesOrderCollectionService collectionService;

    /**
     * 查询收款记录
     *
     * @param id 收款记录ID
     * @return 收款记录
     */
    @Override
    public SalesOrderAccountReceived selectSalesOrderAccountReceivedById(Long id) {
        return salesOrderAccountReceivedMapper.selectSalesOrderAccountReceivedById(id);
    }

    /**
     * 查询收款记录列表
     *
     * @param salesOrderAccountReceived 收款记录
     * @return 收款记录
     */
    @Override
    public List<SalesOrderAccountReceived> selectSalesOrderAccountReceivedList(SalesOrderAccountReceivedQuery salesOrderAccountReceived) {
        List<SalesOrderAccountReceived> list = salesOrderAccountReceivedMapper.selectSalesOrderAccountReceivedList(salesOrderAccountReceived);
        list.stream().forEach(a -> a.setGatheringType(AccountReceivedType.codeToValue(a.getGatheringType())));
        return list;
    }

    /**
     * 新增收款记录
     *
     * @param salesOrderAccountReceived 收款记录
     * @return 结果
     */
    @Override
    public int insertSalesOrderAccountReceived(SalesOrderAccountReceived salesOrderAccountReceived) {
        String userName = SecurityUtils.getUsername();
        Date date = DateUtils.getNowDate();
        salesOrderAccountReceived.setCreateBy(userName);
        salesOrderAccountReceived.setCreateTime(date);
        return salesOrderAccountReceivedMapper.insertSalesOrderAccountReceived(salesOrderAccountReceived);
    }

    /**
     * 新增收款记录
     *
     * @param list 收款记录
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSalesOrderAccountReceivedBatch(List<SalesOrderAccountReceived> list) {

        if (CollectionUtils.isNotEmpty(list)) {
            String userName = SecurityUtils.getUsername();
            Date date = DateUtils.getNowDate();

            BigDecimal gatherings = new BigDecimal(0);

            for (SalesOrderAccountReceived accountReceived : list) {
                accountReceived.setCreateBy(userName);
                accountReceived.setCreateTime(date);

                gatherings = gatherings.add(accountReceived.getPayeeAmount());
            }

            int result = collectionService.insertSalesOrderCollection(list.get(0).getCustomer(), new BigDecimal(0), gatherings, null, date);
            if (result <= 0) {
                throw new BaseException("收款汇总失败");
            }
            return salesOrderAccountReceivedMapper.insertSalesOrderAccountReceivedBatch(list);
        }

        return 0;
    }



    /**
     * 修改收款记录
     *
     * @param salesOrderAccountReceived 收款记录
     * @return 结果
     */
    @Override
    public int updateSalesOrderAccountReceived(SalesOrderAccountReceived salesOrderAccountReceived) {
        salesOrderAccountReceived.setUpdateTime(DateUtils.getNowDate());
        return salesOrderAccountReceivedMapper.updateSalesOrderAccountReceived(salesOrderAccountReceived);
    }

    /**
     * 批量删除收款记录
     *
     * @param ids 需要删除的收款记录ID
     * @return 结果
     */
    @Override
    public int deleteSalesOrderAccountReceivedByIds(Long[] ids) {
        return salesOrderAccountReceivedMapper.deleteSalesOrderAccountReceivedByIds(ids);
    }

    /**
     * 删除收款记录信息
     *
     * @param id 收款记录ID
     * @return 结果
     */
    @Override
    public int deleteSalesOrderAccountReceivedById(Long id) {
        return salesOrderAccountReceivedMapper.deleteSalesOrderAccountReceivedById(id);
    }
}
