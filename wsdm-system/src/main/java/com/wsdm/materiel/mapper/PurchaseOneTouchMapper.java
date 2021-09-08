package com.wsdm.materiel.mapper;

import com.wsdm.materiel.domain.SalesOrderAutomaticallyGenerate;
import com.wsdm.materiel.domain.SemiProduceEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 物料采购一键生成
 *
 * @author GYJ
 * @date 2021-08-26
 */
public interface PurchaseOneTouchMapper {
    /**
     * 查询销售订单和发货订单信息，
     * 返回未发货的成品和半成品数量
     *
     * @return 物料采购订单信息
     */
    public List<Map<String,Object>> selectProductQuantity();

    /**
     * 查询各产品库存剩余数量
     * @return
     */
    public List<Map<String,Object>> selectStockNumber();

    /**
     * 查询五金库存剩余数量
     * @return
     */
    public List<Map<String,Object>> selectHardwareStockNumber();

    /**
     * 根据成品编号查询所需半成品数量
     * @param finishedProductNo
     * @return
     */
    public List<Map<String,Object>> selectSemiProduceNumber(@Param("finishedProductNo")String finishedProductNo,@Param("salesNumber")String salesNumber);

    /**
     * 根据成品编号查询五金配件所需数量
     * @param finishedProductNo
     * @return
     */
    public List<Map<String,Object>> selectHardwareList(@Param("finishedProductNo")String finishedProductNo,@Param("salesNumber")String salesNumber);
    /**
     * 标记已经自动生成的数据
     * @param salesNumberSet
     * @return
     */
    public int updateSalesOrderTags(@Param(value = "set") Set<String> salesNumberSet);

    /**
     * 批量新增系统记录
     * @param aList
     * @return
     */
    public int insertSalesOrderAutomaticallyGenerate(@Param("aList") List<SalesOrderAutomaticallyGenerate> aList);

    /**
     * 查询半成品详细信息
     * @param materialNumber
     * @return
     */
    public List<SemiProduceEntity> selectSemiProduceEntity(@Param("materialNumber") List<String> materialNumber);




}
