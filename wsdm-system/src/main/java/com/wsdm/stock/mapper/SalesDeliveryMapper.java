package com.wsdm.stock.mapper;
import com.wsdm.stock.domain.SalesDelivery;
import com.wsdm.stock.domain.SalesDeliveryDetail;
import com.wsdm.stock.domain.SalesDeliveryRequest;

import java.util.List;


/**
 * 发货订单信息Mapper接口
 *
 * @author wsdm
 * @date 2021-01-12
 */
public interface SalesDeliveryMapper {
    /**
     * 查询发货订单信息
     *
     * @param id 发货订单信息ID
     * @return 发货订单信息
     */
    public SalesDelivery selectSalesDeliveryById(Long id);

    /**
     * 查询发货订单信息列表
     *
     * @param salesDelivery 发货订单信息
     * @return 发货订单信息集合
     */
    public List<SalesDelivery> selectSalesDeliveryList(SalesDeliveryRequest salesDelivery);

    /**
     * 新增发货订单信息
     *
     * @param salesDelivery 发货订单信息
     * @return 结果
     */
    public int insertSalesDelivery(SalesDelivery salesDelivery);

    /**
     * 修改发货订单信息
     *
     * @param salesDelivery 发货订单信息
     * @return 结果
     */
    public int updateSalesDelivery(SalesDelivery salesDelivery);

    /**
     * 删除发货订单信息
     *
     * @param id 发货订单信息ID
     * @return 结果
     */
    public int deleteSalesDeliveryById(Long id);

    /**
     * 批量删除发货订单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSalesDeliveryByIds(Long[] ids);

    String getMaxSalesDeliveryCode(String code);

    /**
     * 查询发货订单详情
     *
     * @param id 发货订单详情ID
     * @return 发货订单详情
     */
    public SalesDeliveryDetail selectSalesDeliveryDetailById(Long id);

    /**
     * 查询发货订单详情列表
     *
     * @param salesDeliveryDetail 发货订单详情
     * @return 发货订单详情集合
     */
    public List<SalesDeliveryDetail> selectSalesDeliveryDetailList(SalesDeliveryDetail salesDeliveryDetail);

    /**
     * 新增发货订单详情
     *
     * @param salesDeliveryDetail 发货订单详情
     * @return 结果
     */
    public int insertSalesDeliveryDetail(SalesDeliveryDetail salesDeliveryDetail);

    /**
     * 批量新增发货订单详情
     *
     * @param salesDeliveryDetail 发货订单详情
     * @return 结果
     */
    public int insertSalesDeliveryDetailBatch(List<SalesDeliveryDetail> salesDeliveryDetails);

    /**
     * 修改发货订单详情
     *
     * @param salesDeliveryDetail 发货订单详情
     * @return 结果
     */
    public int updateSalesDeliveryDetail(SalesDeliveryDetail salesDeliveryDetail);

    /**
     * 删除发货订单详情
     *
     * @param id 发货订单详情ID
     * @return 结果
     */
    public int deleteSalesDeliveryDetailById(Long id);

    /**
     * 批量删除发货订单详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSalesDeliveryDetailByIds(Long[] ids);

    /**
     * 根据发货单号删除发货订单详情
     *
     * @param id 发货订单详情ID
     * @return 结果
     */
    int deleteSalesDeliveryDetailBySalesNumber(String salesNumber);
}
