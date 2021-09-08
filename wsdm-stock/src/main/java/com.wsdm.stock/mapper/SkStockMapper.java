package com.wsdm.stock.mapper;

import com.wsdm.stock.domain.SkStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 库存主Mapper接口
 *
 * @author wsdm
 * @date 2020-12-07
 */
public interface SkStockMapper
{
    /**
     * 查询库存主
     *
     * @param id 库存主ID
     * @return 库存主
     */
    public SkStock selectSkStockById(Long id);

    /**
     * 查询库存主列表
     *
     * @param skStock 库存主
     * @return 库存主集合
     */
    public List<SkStock> selectSkStockList(SkStock skStock);

    public List<SkStock> getStockByGroup();

    /**
     * 新增库存主
     *
     * @param skStock 库存主
     * @return 结果
     */
    public int insertSkStock(SkStock skStock);

    /**
     * 批量新增库存主
     *
     * @param stocks 库存主
     * @return 结果
     */
    public int insertSkStockBatch(List<SkStock> stocks);

    /**
     * 修改库存主
     *
     * @param skStock 库存主
     * @return 结果
     */
    public int updateSkStock(SkStock skStock);

    /**
     * 删除库存主
     *
     * @param id 库存主ID
     * @return 结果
     */
    public int deleteSkStockById(Long id);

    /**
     * 批量删除库存主
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSkStockByIds(Long[] ids);

    /**
     * 查询最大物料单号
     *
     * @param code 物料单号前缀
     * @return 结果
     */
    public String getMaxCode(String code);

    /**
     * 按照物料汇总库存
     */
    SkStock summary(@Param("code") String code, @Param("produce") String produce);

    void addStockTransitNum(SkStock stock);

    int addStock(SkStock param);

    void removeAvailableNum(SkStock stock);

    int addStockLockNum(SkStock stock);

    void removeStockLockNum(SkStock stock);

    List<SkStock> selectAvailableMaterial(SkStock stock);

    void removeStockTransitNum(SkStock stock);
}
