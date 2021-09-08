package com.wsdm.sale.mapper;

import com.wsdm.sale.domain.SalesOrderCollection;

import java.util.List;


/**
 * 收款汇总Mapper接口
 *
 * @author wsdm
 * @date 2021-07-17
 */
public interface SalesOrderCollectionMapper {
    /**
     * 查询收款汇总
     *
     * @param id 收款汇总ID
     * @return 收款汇总
     */
    public SalesOrderCollection selectSalesOrderCollectionById(Long id);

    /**
     * 查询收款汇总列表
     *
     * @param salesOrderCollection 收款汇总
     * @return 收款汇总集合
     */
    public List<SalesOrderCollection> selectSalesOrderCollectionList(SalesOrderCollection salesOrderCollection);

    /**
     * 
     * 根据客户编号
     * @param customer 
     * @author wangr 
     * @date 2021/7/18 22:07
     * @return com.wsdm.sale.domain.SalesOrderCollection
     */
    SalesOrderCollection getOrderCollectionByCustomer(String customer);

    /**
     * 新增收款汇总
     *
     * @param salesOrderCollection 收款汇总
     * @return 结果
     */
    public int insertSalesOrderCollection(SalesOrderCollection salesOrderCollection);

    /**
     * 修改收款汇总
     *
     * @param salesOrderCollection 收款汇总
     * @return 结果
     */
    public int updateSalesOrderCollection(SalesOrderCollection salesOrderCollection);

    /**
     * 删除收款汇总
     *
     * @param id 收款汇总ID
     * @return 结果
     */
    public int deleteSalesOrderCollectionById(Long id);

    /**
     * 批量删除收款汇总
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSalesOrderCollectionByIds(Long[] ids);
}
