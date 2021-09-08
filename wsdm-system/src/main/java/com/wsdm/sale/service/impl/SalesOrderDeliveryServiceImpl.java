package com.wsdm.sale.service.impl;

import com.wsdm.common.CodeUtils;
import com.wsdm.common.constant.Constants;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.SalesOrderStatus;
import com.wsdm.common.enums.TransactionType;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.StringUtils;
import com.wsdm.pd.mapper.PdAssociationMapper;
import com.wsdm.pd.service.IPdAssociationService;
import com.wsdm.sale.domain.*;
import com.wsdm.sale.mapper.SalesOrderDeliveryMapper;
import com.wsdm.sale.mapper.SalesOrderManageMapper;
import com.wsdm.sale.service.ISalesOrderDeliveryService;
import com.wsdm.sale.service.ISalesOrderTransactionDetailsService;
import com.wsdm.stock.service.ISkStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 销售单发货Service业务层处理
 *
 * @author wsdm
 * @date 2021-04-27
 */
@Service
public class SalesOrderDeliveryServiceImpl implements ISalesOrderDeliveryService {

    @Autowired
    private SalesOrderDeliveryMapper salesOrderDeliveryMapper;

    @Autowired
    private SalesOrderManageMapper salesOrderManageMapper;

    @Autowired
    private ISkStockService skStockService;

    @Autowired
    private ISalesOrderTransactionDetailsService transactionDetailsService;

    @Autowired
    private PdAssociationMapper pdAssociationMapper;

    @Autowired
    private IPdAssociationService pdAssociationService;

    /**
     * 查询销售单发货列表
     *
     * @param salesOrderDeliveryQuery 销售单发货
     * @return 销售单发货
     */
    @Override
    public List<SalesOrderDeliveryInfo> selectSalesOrderDeliveryList(SalesOrderDeliveryQuery salesOrderDeliveryQuery) {
        return salesOrderDeliveryMapper.selectSalesOrderDeliveryList(salesOrderDeliveryQuery);
    }

    /**
     * 查询销售单发货
     *
     * @param id 销售单发货ID
     * @return 销售单发货
     */
    @Override
    public SalesOrderDeliveryInfo selectSalesOrderDeliveryById(Long id) {
        SalesOrderDeliveryInfo info = salesOrderDeliveryMapper.selectSalesOrderDeliveryById(id);
        List<SalesOrderDeliveryDetails> detailsList = salesOrderDeliveryMapper.getDeliverDetailsByDeliveryNumber(info.getDeliveryNumber());
        info.setDeliveryDetailsList(detailsList);
        return info;
    }

    @Override
    public SalesOrderDeliveryInfo selectSalesOrderDeliveryById(String deliveryNumber) {
        SalesOrderDeliveryInfo info = salesOrderDeliveryMapper.selectSalesOrderDeliveryByNumber(deliveryNumber);
        List<SalesOrderDeliveryDetails> detailsList = salesOrderDeliveryMapper.getDeliverDetailsByDeliveryNumber(info.getDeliveryNumber());
        info.setDeliveryDetailsList(detailsList);
        return info;
    }

    @Override
    public Map getAddInfo(String salesNumber) {
        Map info = salesOrderDeliveryMapper.getAddDeliverInfoBySalesNumber(salesNumber);
        List<SalesOrderDeliveryDetails> detailsList = salesOrderDeliveryMapper.getDeliverDetailsBySalesNumber(salesNumber);
        info.put("deliveryDetailsList", detailsList);
        return info;
    }

    @Override
    public List<Map> getSalesDeliveryOrder(String deliveryNumber, String customer) {
        List<Map> list = salesOrderDeliveryMapper.getSalesDeliveryOrder(deliveryNumber, customer);
        list.stream().forEach(map -> {
            map.put("deliveryAmount", StringUtils.format4(map.get("deliveryAmount") != null ? map.get("deliveryAmount") : 0));
            map.put("gatheringAmount", StringUtils.format4(map.get("gatheringAmount") != null ? map.get("gatheringAmount") : 0));
        });
        return list;
    }

    @Override
    public List<SalesOrderDeliveryDetails> getDeliverDetailsByDeliveryNumber(String deliveryNumber) {
        return salesOrderDeliveryMapper.getDeliverDetailsByDeliveryNumber(deliveryNumber);
    }

    /**
     * 新增销售单发货
     *
     * @param salesOrderDelivery 销售单发货
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult insertSalesOrderDelivery(SalesOrderDelivery salesOrderDelivery) {
        List<SalesOrderDeliveryDetails> detailList = salesOrderDelivery.getDeliveryDetailsList();

        String code = Constants.SALES_DELIVERY_FH + DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date());
        String maxCode = salesOrderDeliveryMapper.getMaxDeliveryCode(code);
        String deliveryCode = CodeUtils.getOneCode(code, maxCode, 3);
        Date date = DateUtils.getNowDate();
        String userName = SecurityUtils.getUsername();
        for (int i = 0; i < detailList.size(); i++) {
            String orderDetailsId = detailList.get(i).getOrderDetailsId();
            Map map = salesOrderDeliveryMapper.getDeliverQuantity(orderDetailsId, null);

            BigDecimal orderQuantity = new BigDecimal(map.get("quantity") != null ? map.get("quantity").toString() : "0.0");
            BigDecimal deliverQuantity = new BigDecimal(map.get("deliverQuantity") != null ? map.get("deliverQuantity").toString() : "0.0");

            deliverQuantity = deliverQuantity.add(detailList.get(i).getDeliverQuantity());
            if (deliverQuantity.compareTo(orderQuantity) == 1) {
                return AjaxResult.error("序号" + (i + 1) + "的产品发货数量大于销售数量,不可新增");
            }
            detailList.get(i).setCreateTime(date);
            detailList.get(i).setCreateBy(userName);
            detailList.get(i).setDeliveryNumber(deliveryCode);
            detailList.get(i).setSalesNumber(salesOrderDelivery.getSalesNumber());
        }
        salesOrderDelivery.setDeliveryNumber(deliveryCode);
        salesOrderDelivery.setCreateTime(date);
        salesOrderDelivery.setCreateBy(userName);

        salesOrderDeliveryMapper.insertDeliveryDetailsBatch(detailList);
        if (salesOrderDeliveryMapper.insertSalesOrderDelivery(salesOrderDelivery) <= 0) {
            throw new BaseException("发货单新增失败！");
        }

        if (SalesOrderStatus.TO_BE_REVIEWED.getCode().equals(salesOrderDelivery.getDeliveryStatus())) {
            try {
                detailList.stream().forEach(details -> {
                    /**
                     * 反射关联内部产品
                     */
                    field(details);

                    skStockService.lockStock(details.getDetailsType(), details.getMaterialNumber(), details.getDeliverQuantity(), SecurityUtils.getUsername());
                });
            } catch (BaseException e) {
                throw new BaseException(e.getMessage());
            }
        }
        return AjaxResult.success();
    }

    /**
     * 修改销售单发货
     *
     * @param salesOrderDelivery 销售单发货
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateSalesOrderDelivery(SalesOrderDelivery salesOrderDelivery) {
        SalesOrderDeliveryInfo salesOrderDeliveryInfo = salesOrderDeliveryMapper.selectSalesOrderDeliveryById(salesOrderDelivery.getId());
        if (salesOrderDeliveryInfo == null || !salesOrderDelivery.getSalesNumber().equals(salesOrderDeliveryInfo.getSalesNumber())) {
            AjaxResult.error("参数有误");
        }

        if (Integer.parseInt(salesOrderDeliveryInfo.getDeliveryStatus()) > 0) {
            AjaxResult.error("该状态不可修改");
        }

        List<SalesOrderDeliveryDetails> detailList = salesOrderDelivery.getDeliveryDetailsList();
        Date date = DateUtils.getNowDate();
        String userName = SecurityUtils.getUsername();
        for (int i = 0; i < detailList.size(); i++) {

            String orderDetailsId = detailList.get(i).getOrderDetailsId();
            Map map = salesOrderDeliveryMapper.getDeliverQuantity(orderDetailsId, String.valueOf(detailList.get(i).getId()));

            BigDecimal orderQuantity = new BigDecimal(map.get("quantity") != null ? map.get("quantity").toString() : "0.0");
            BigDecimal deliverQuantity = new BigDecimal(map.get("deliverQuantity") != null ? map.get("deliverQuantity").toString() : "0.0");

            deliverQuantity = deliverQuantity.add(detailList.get(i).getDeliverQuantity());
            if (deliverQuantity.compareTo(orderQuantity) == 1) {
                return AjaxResult.error("序号" + (i + 1) + "的产品发货数量大于销售数量不可修改");
            }

            detailList.get(i).setUpdateTime(date);
            detailList.get(i).setUpdateBy(userName);
        }

        salesOrderDelivery.setUpdateTime(date);
        salesOrderDelivery.setUpdateBy(userName);

        salesOrderDeliveryMapper.updateDeliveryDetailsBatch(detailList);
        if (salesOrderDeliveryMapper.updateSalesOrderDelivery(salesOrderDelivery) == 0) {
            throw new BaseException("发货单修改失败！");
        }
        if (SalesOrderStatus.TO_BE_REVIEWED.getCode().equals(salesOrderDelivery.getDeliveryStatus())) {
            try {
//                List<SalesOrderDeliveryDetails> detailList = salesOrderDelivery.getDeliveryDetailsList();

                detailList.stream().forEach(details -> {
                    /**
                     * 反射关联内部产品
                     */
                    field(details);

                    skStockService.lockStock(details.getDetailsType(), details.getMaterialNumber(), details.getDeliverQuantity(), SecurityUtils.getUsername());
                });
            } catch (BaseException e) {
                throw new BaseException(e.getMessage());
            }
        }
        return AjaxResult.success();
    }

    /**
     * 删除销售单发货信息
     *
     * @param id 销售单发货ID
     * @return 结果
     */
    @Override
    public AjaxResult deleteSalesOrderDeliveryById(Long id) {
        SalesOrderDelivery salesOrderDelivery = salesOrderDeliveryMapper.selectSalesOrderDeliveryById(id);
        if (Integer.parseInt(salesOrderDelivery.getDeliveryStatus()) > 0) {
            return AjaxResult.error("该状态不可删除！");
        }
        return salesOrderDeliveryMapper.deleteSalesOrderDeliveryById(id) > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult approve(Long id, String action) {
        SalesOrderDeliveryInfo salesOrderDelivery = salesOrderDeliveryMapper.selectSalesOrderDeliveryById(id);
        SalesOrderManage salesOrderManage = salesOrderManageMapper.selectSalesOrderManageBySalesNumber(salesOrderDelivery.getSalesNumber());
        BigDecimal deliverTotal = salesOrderDeliveryMapper.getDeliverTotal(salesOrderDelivery.getSalesNumber());

        if (!SalesOrderStatus.TO_BE_REVIEWED.getCode().equals(salesOrderDelivery.getDeliveryStatus())) {
            return AjaxResult.error("-1", "当前状态不可审核！");
        }

        List<SalesOrderDeliveryDetails> detailsList = salesOrderDeliveryMapper.getDeliverDetailsByDeliveryNumber(salesOrderDelivery.getDeliveryNumber());
        if (Constants.ADOPT.equals(action)) {

            BigDecimal deliveryAmount = new BigDecimal(0);
            BigDecimal deliveryQuantityTotal = new BigDecimal(0);

            for (SalesOrderDeliveryDetails details : detailsList) {

                BigDecimal total = details.getQuantity();
                BigDecimal deliveryQuantity = details.getDeliverQuantity();
                BigDecimal deliveredQuantity = details.getDeliveredQuantity();
                int a = total.compareTo(deliveryQuantity.add(deliveredQuantity));
                if (a == -1) {
                    return AjaxResult.error(details.getMaterialNumberName() + "的发货数量不能大于销售数量");
                }
                deliveryAmount = deliveryAmount.add(details.getDeliveryAmount()).setScale(2);
                deliveryQuantityTotal = deliveryQuantityTotal.add(deliveryQuantity).setScale(1);

                details.setDeliveredQuantity(deliveryQuantity);
            }
            int a = salesOrderManage.getOrderQuantity().compareTo(deliverTotal.add(deliveryQuantityTotal));
            if (a == -1) {
                return AjaxResult.error("总发货数量不能大于总销售数量");
            }
            salesOrderDelivery.setDeliveryStatus(SalesOrderStatus.REVIEWED.getCode());
            salesOrderDelivery.setDeliveredQuantity(deliveryQuantityTotal);
            salesOrderDelivery.setDeliveryAmount(deliveryAmount);
            salesOrderManageMapper.updateSalesOrderManage(salesOrderManage);
            salesOrderDeliveryMapper.updateDeliveredQuantityBatch(detailsList);

            int result = insertTransactionDetailsService(salesOrderDelivery, detailsList);
            if (result <= 0) {
                throw new BaseException("交易记录插入失败");
            }
        } else {
            salesOrderDelivery.setDeliveryStatus(SalesOrderStatus.NOT_SUBMIT.getCode());
        }
        salesOrderDelivery.setApproveTime(new Date());
        salesOrderDelivery.setApprove(SecurityUtils.getUsername());
        salesOrderDeliveryMapper.updateSalesOrderDelivery(salesOrderDelivery);
        for (SalesOrderDeliveryDetails details : detailsList) {
            /**
             * 反射关联内部产品
             */
            field(details);
            if (Constants.ADOPT.equals(action)) {
                skStockService.confirmLockedStock(details.getDetailsType(), details.getMaterialNumber(), details.getDeliverQuantity(), SecurityUtils.getUsername());
            } else {
                skStockService.unlockStock(details.getDetailsType(), details.getMaterialNumber(), details.getDeliverQuantity(), SecurityUtils.getUsername());
            }
        }
        return AjaxResult.success();
    }

    public int insertTransactionDetailsService(SalesOrderDelivery delivery, List<SalesOrderDeliveryDetails> deliveryDetails) {

        List<SalesOrderTransactionDetails> transactionDetails = new ArrayList<>();

        deliveryDetails.stream().forEach(d -> {

            SalesOrderTransactionDetails t = new SalesOrderTransactionDetails(
                    delivery.getCustomer(),
                    delivery.getDeliveryNumber(),
                    d.getMaterialNumber(),
                    d.getDeliverQuantity(),
                    d.getPrice(),
                    d.getDeliveryAmount()
            );

            transactionDetails.add(t);
        });

        return transactionDetailsService.insertSalesOrderTransactionDetails(TransactionType.DELIVER, transactionDetails);
    }


    /**
     * 反射方法 根据object对象转成实体类
     */
    public void field(SalesOrderDeliveryDetails details){
        /**
         * 根据外部产品查询内部产品
         */
        String pdId = pdAssociationMapper.selectInnerPdIdByNo(details.getMaterialNumber());
        Object InnerPdInfo = pdAssociationService.queryInnerPdInfo(pdId);

//        System.out.println("Object" + InnerPdInfo.toString());

        /**
         * 反射获取属性值
         */
        Method[] declaredMethods = InnerPdInfo.getClass().getDeclaredMethods();
        for(Method method:declaredMethods){
            Object o= null;
            if(method.getName().startsWith("getProduceName") || method.getName().startsWith("getSemiProduceName")){
                try {
                    o = method.invoke(InnerPdInfo);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
//                System.out.println(method.getName());
//                System.out.println("属性值get方法->"+o);

                details.setMaterialNumberName((String) o);
            }

            if(method.getName().startsWith("getProduceNumber") || method.getName().startsWith("getSemiProduceNumber")){
                try {
                    o = method.invoke(InnerPdInfo);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
//                System.out.println(method.getName());
//                System.out.println("属性值get方法->"+o);
                details.setMaterialNumber((String) o);
            }
        }
    }
}
