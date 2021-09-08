package com.wsdm.sale.service.impl;

import com.wsdm.common.CodeUtils;
import com.wsdm.common.constant.Constants;
import com.wsdm.common.enums.TransactionType;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.sale.domain.*;
import com.wsdm.sale.mapper.SalesGoodsRejectMapper;
import com.wsdm.sale.mapper.SalesOrderCollectionMapper;
import com.wsdm.sale.mapper.SalesOrderCustomerMapper;
import com.wsdm.sale.mapper.SalesOrderTransactionDetailsMapper;
import com.wsdm.sale.service.ISalesGoodsRejectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.ParameterOutOfBoundsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.*;

/**
 * 销售退货Service业务层处理
 *
 * @author Lu
 * @date 2021-7-16
 */
@Service
public class ISalesGoodsRejectServiceImpl implements ISalesGoodsRejectService {

    @Autowired
    private SalesGoodsRejectMapper salesGoodsRejectMapper;

    @Autowired
    private SalesOrderTransactionDetailsMapper salesOrderTransactionDetailsMapper;

    @Autowired
    private SalesOrderCustomerMapper salesOrderCustomerMapper;

    @Autowired
    private SalesOrderCollectionServiceImpl salesOrderCollectionService;


    private final static Map<String, String> map = new HashMap<>();

    static {
        map.put("0", "未审核");
        map.put("1", "待审核");
        map.put("2", "已审核");
    }

    @Override
    public List<SalesGoodsReject> selectSalesGoodsRejectAll(SalesGoodsRejectQuery salesGoodsRejectQuery) {
        List<SalesGoodsReject> salesGoodsRejects = salesGoodsRejectMapper.selectSalesGoodsRejectAll(salesGoodsRejectQuery);
        for (SalesGoodsReject salesGoodsReject : salesGoodsRejects) {
            Double aDouble = salesGoodsRejectMapper.countReturnQuantity(salesGoodsReject.getSalesNumber());
            if (Objects.isNull(aDouble)) aDouble = 0.0;
            salesGoodsReject.setReturnNumber(String.valueOf(aDouble));
        }

        List<SalesGoodsReject> salesGoodsRejectFilter = new ArrayList<>();
        salesGoodsRejects.forEach(
                item -> {
                    if (item.getDeliveredQuantity().doubleValue() > Double.parseDouble(item.getReturnNumber())) {
                        salesGoodsRejectFilter.add(item);
                    }
                }
        );
        return salesGoodsRejectFilter;
    }

    @Override
    public List<SalesGoodsRejectInfo> selectSalesGoodsRejectInfo(SalesGoodsRejectInfoQuery salesGoodsRejectInfoQuery) {
        List<SalesGoodsRejectInfo> salesGoodsRejectInfos = salesGoodsRejectMapper.selectSalesGoodsRejectInfo(salesGoodsRejectInfoQuery);
        salesGoodsRejectInfos.forEach(
                item -> {
                    item.setApprove(item.getAuditor());
                    item.setApproveTime(item.getAudit_time());
                    item.setReturnStatusName(map.get(item.getReturnStatus()));
                }
        );

        return salesGoodsRejectInfos;
    }

    @Override
    public SalesGoodsReject selectSalesGoodsRejectDetailAllBySaleName(String saleName, String customer) {
        SalesGoodsReject salesGoodsReject = new SalesGoodsReject();
        salesGoodsReject.setSalesNumber(saleName);
        salesGoodsReject.setCustomer(customer);
        List<SalesGoodsRejectDetail> salesGoodsRejectDetails = salesGoodsRejectMapper.selectSalesGoodsRejectDetailBySaleName(saleName);
        System.out.println("salesGoodsRejectDetails = " + salesGoodsRejectDetails);
        if (!Objects.isNull(salesGoodsRejectDetails)) {
            salesGoodsRejectDetails.forEach(
                    item -> {
                        Double sumQuantity = salesGoodsRejectMapper.countReturnQuantityDetail(
                                item.getMaterialNumber(),
                                item.getMaterialName(),
                                item.getDetailsType()
                        );
                        if (Objects.isNull(sumQuantity)) sumQuantity = 0.0;
                        BigDecimal bigDecimal = new BigDecimal(sumQuantity);
                        BigDecimal sum = bigDecimal.setScale(2, BigDecimal.ROUND_DOWN);
                        item.setQuantityReturned(sum);//计算已退货数量

                        BigDecimal price = item.getPrice();
                        BigDecimal decimal = price.setScale(2, BigDecimal.ROUND_DOWN);
                        item.setPrice(decimal);//获取单价
                    }
            );
        }
        salesGoodsReject.setSalesGoodsRejectDetailList(salesGoodsRejectDetails);
        return salesGoodsReject;
    }

    @Transactional
    @Override
    public int saveOrSubmitSalesReturn(int type, SalesGoodsReject salesGoodsReject) {
        switch (type) {
            case 0:
                SalesReturn("0", salesGoodsReject);
                break;
            case 1:
                SalesReturn("1", salesGoodsReject);
                break;
        }
        return 1;
    }

    @Transactional
    @Override
    public int updateSalesReturn(SalesGoodsReject salesGoodsReject) {

        SalesGoodsRejectInfo salesGoodsRejectInfo = new SalesGoodsRejectInfo();

        salesGoodsRejectInfo.setReturnQuantity(salesGoodsReject.getReturnQuantity());
        salesGoodsRejectInfo.setReturnAmount(salesGoodsReject.getReturnAmount());
        salesGoodsRejectInfo.setReturnDate(salesGoodsReject.getReturnDate());
        salesGoodsRejectInfo.setRemark(salesGoodsReject.getRemark());
        salesGoodsRejectInfo.setReturnNumber(salesGoodsReject.getReturnNumber());
        salesGoodsRejectInfo.setReturnStatus(salesGoodsReject.getReturnStatus());

        if (salesGoodsRejectInfo.getReturnStatus().trim().equals("1")) {
            Date date = DateUtils.getNowDate();
            String userName = SecurityUtils.getUsername();
            salesGoodsRejectInfo.setAuditor(userName);
            salesGoodsRejectInfo.setAudit_time(date);
        }

        for (SalesGoodsRejectDetail salesGoodsRejectDetail : salesGoodsReject.getSalesGoodsRejectDetailList()) {
            salesGoodsRejectMapper.updateSalesGoodsReturnDetail(salesGoodsRejectDetail);
        }
        salesGoodsRejectMapper.updateSalesGoodsReturnDate(salesGoodsRejectInfo);
        return 1;
    }

    @Override
    public SalesGoodsReject getSalesGoodsRejectDetailByReturnNumber(String returnNumber) {
        if (Objects.isNull(returnNumber)) return null;
        SalesGoodsReject salesGoodsRejectDetailByReturnNumber = salesGoodsRejectMapper.getSalesGoodsRejectDetailByReturnNumber(returnNumber);
        salesGoodsRejectDetailByReturnNumber.setCreateBy(salesGoodsRejectDetailByReturnNumber.getAuditor());
        salesGoodsRejectDetailByReturnNumber.setCreateTime(salesGoodsRejectDetailByReturnNumber.getAudit_time());

        List<SalesGoodsRejectDetail> salesGoodsRejectDetails = salesGoodsRejectMapper.selectSalesGoodsRejectDetailByReturnNumber(returnNumber);

        Date returnDate = salesGoodsRejectDetailByReturnNumber.getReturnDate();

        String status = map.get(salesGoodsRejectDetailByReturnNumber.getReturnStatus());
        salesGoodsRejectDetailByReturnNumber.setReturnStatusName(status);

        if (!Objects.isNull(salesGoodsRejectDetails)) {
            salesGoodsRejectDetails.forEach(
                    item -> {
                        Double sumQuantity = salesGoodsRejectMapper.countReturnQuantityDetail(
                                item.getMaterialNumber(),
                                item.getMaterialName(),
                                item.getDetailsType()
                        );
                        if (Objects.isNull(sumQuantity)) sumQuantity = 0.00;
                        BigDecimal bigDecimal = new BigDecimal(sumQuantity);
                        BigDecimal sum = bigDecimal.setScale(2, BigDecimal.ROUND_DOWN);
                        item.setQuantityReturned(sum);
                        item.setReturnDate(returnDate);
                        item.setReturnStatusName(status);
                    }
            );

            salesGoodsRejectDetailByReturnNumber.setSalesGoodsRejectDetailList(salesGoodsRejectDetails);
        }

        return salesGoodsRejectDetailByReturnNumber;
    }

    @Override
    public List<String> selectCustomerList() {
        return salesGoodsRejectMapper.selectCustomerList();
    }

    @Transactional
    @Override
    public int updateSalesGoodsReturn(String returnNumber, String action) {
        if (Objects.isNull(returnNumber) || Objects.isNull(action)) return 0;
        Date date = DateUtils.getNowDate();
        String userName = SecurityUtils.getUsername();
        //修改审核状态
        int result = salesGoodsRejectMapper.updateSalesGoodsReturn(returnNumber, date, userName, action);
            //审核
        if (action.trim().equals("2")) {
            SalesGoodsReject salesGoodsReject = salesGoodsRejectMapper.getSalesGoodsRejectDetailByReturnNumber(returnNumber);
            String[] split = salesGoodsReject.getCustomer().split("\\.");
            String customer = split[0];//获取客户
            SalesOrderCustomer salesOrderCustomer = salesOrderCustomerMapper.selectSalesOrderCustomerByCustomerNumber(customer);

            List<SalesOrderTransactionDetails> list = new ArrayList<>();
            List<SalesGoodsRejectDetail> salesGoodsRejectDetails = salesGoodsRejectMapper.selectSalesGoodsRejectDetailByReturnNumber(returnNumber);
            for (SalesGoodsRejectDetail salesGoodsRejectDetail : salesGoodsRejectDetails) {
                //修改详情状态
                salesGoodsRejectMapper.updateStatus(salesGoodsRejectDetail.getId());
                Map<String,Object> map = salesGoodsRejectMapper.getMaterial(salesGoodsRejectDetail.getMaterialNumber());
                String materialName = map.get("name")==null||"".equals(map.get("name"))?null:map.get("name").toString();//名称
                String materialNumber = map.get("number").toString();//编号
                String detailsType = salesGoodsRejectDetail.getDetailsType();//物料类型
                BigDecimal returnQuantity = salesGoodsRejectDetail.getReturnQuantity();//退货数量
                salesGoodsRejectMapper.updateSkStock(
                        materialName,
                        detailsType,
                        materialNumber,
                        returnQuantity
                );

                BigDecimal quantity = salesGoodsRejectDetail.getReturnQuantity();
                if (quantity.doubleValue() != 0.0) {
                    SalesOrderTransactionDetails salesOrderTransactionDetails = new SalesOrderTransactionDetails();

                    salesOrderTransactionDetails.setCustomer(customer);
                    salesOrderTransactionDetails.setTransactionType(TransactionType.RETURN.name());
                    salesOrderTransactionDetails.setTransactionNumber(salesGoodsReject.getReturnNumber());
                    salesOrderTransactionDetails.setMaterialNumber(salesGoodsRejectDetail.getMaterialNumber());
                    salesOrderTransactionDetails.setQuantity(quantity);

                    BigDecimal total = new BigDecimal("-"+salesGoodsRejectDetail.getSubtotal().toString());
                    salesOrderTransactionDetails.setPrice(salesGoodsRejectDetail.getPrice());
                    salesOrderTransactionDetails.setAmount(total);


                    salesOrderTransactionDetails.setTransactionDate(DateUtils.getNowDate());
                    String remark = salesGoodsReject.getRemark();
                    if (Objects.isNull(remark)) remark = "";
                    salesOrderTransactionDetails.setRemark(remark);
                    salesOrderTransactionDetails.setCreateBy(userName);
                    salesOrderTransactionDetails.setCreateTime(DateUtils.getNowDate());

                    list.add(salesOrderTransactionDetails);
                }
            }
            salesOrderTransactionDetailsMapper.insertSalesOrderTransactionDetailsBatch(list);

            BigDecimal total = new BigDecimal(-salesGoodsReject.getReturnAmount().longValue());
            return salesOrderCollectionService.insertSalesOrderCollection(
                    salesOrderCustomer.getCustomerNumber(),
                    new BigDecimal(0),
                    total,
                    DateUtils.getNowDate(),
                    null
            );
        }

        return result;
    }

    @Transactional
    @Override
    public int deleteSalesGoodsReturn(String[] returnNumbers) {
        if (Objects.isNull(returnNumbers)) return 0;
        for (String returnNumber : returnNumbers) {
            salesGoodsRejectMapper.deleteSalesGoodsReturn(returnNumber);
            List<SalesGoodsRejectDetail> salesGoodsRejectDetails = salesGoodsRejectMapper.selectSalesGoodsRejectDetailByReturnNumber(returnNumber);
            for (SalesGoodsRejectDetail salesGoodsRejectDetail : salesGoodsRejectDetails) {
                salesGoodsRejectMapper.deleteSalesGoodsReturnDetail(salesGoodsRejectDetail.getId());
            }
        }

        return 1;
    }

    private void SalesReturn(String returnStatus, SalesGoodsReject salesGoodsReject) {
        Date date = DateUtils.getNowDate();
        String userName = SecurityUtils.getUsername();
        String code = "TH" + DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date());
        String maxCode = salesGoodsRejectMapper.getMaxBillingCode(code);
        String salesGoodsRejectCode = CodeUtils.getOneCode(code, maxCode, 3);


        SalesGoodsRejectInfo salesGoodsRejectInfo = new SalesGoodsRejectInfo();
        salesGoodsRejectInfo.setReturnNumber(salesGoodsRejectCode);
        salesGoodsRejectInfo.setSalesNumber(salesGoodsReject.getSalesNumber());
        salesGoodsRejectInfo.setCustomerName(salesGoodsReject.getCustomer());
        salesGoodsRejectInfo.setId(null);
        salesGoodsRejectInfo.setCreateBy(userName);
        salesGoodsRejectInfo.setCreateTime(date);
        salesGoodsRejectInfo.setReturnQuantity(salesGoodsReject.getReturnQuantity());

        salesGoodsRejectInfo.setReturnDate(salesGoodsReject.getReturnDate());
        salesGoodsRejectInfo.setReturnStatus(returnStatus);
        salesGoodsRejectInfo.setRemark(salesGoodsReject.getRemark());

        if (returnStatus.trim().equals("1")) {
            salesGoodsRejectInfo.setAuditor(userName);
            salesGoodsRejectInfo.setAudit_time(date);
        }

        BigDecimal sumTotal = new BigDecimal("0.00");
        for (SalesGoodsRejectDetail salesGoodsRejectDetail : salesGoodsReject.getSalesGoodsRejectDetailList()) {
            salesGoodsRejectDetail.setReturnNumber(salesGoodsRejectCode);

            if (salesGoodsRejectDetail.getPrice() != null && salesGoodsRejectDetail.getReturnQuantity() != null) {
                BigDecimal price = salesGoodsRejectDetail.getPrice();
                BigDecimal returnQuantity = salesGoodsRejectDetail.getReturnQuantity();
                BigDecimal multiply = new BigDecimal(price.toString()).multiply(new BigDecimal(returnQuantity.toString()));
                sumTotal = sumTotal.add(new BigDecimal(multiply.toString()));
                salesGoodsRejectDetail.setSubtotal(multiply);
            }
            salesGoodsRejectMapper.insertSalesGoodsReturnDetail(salesGoodsRejectDetail);
        }
        salesGoodsRejectInfo.setReturnAmount(sumTotal);
        salesGoodsRejectMapper.insertSalesGoodsReturn(salesGoodsRejectInfo);
    }
}
