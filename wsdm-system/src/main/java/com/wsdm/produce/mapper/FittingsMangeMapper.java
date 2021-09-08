package com.wsdm.produce.mapper;

import com.wsdm.produce.domain.FittingsMange;

import java.util.List;
import java.util.Map;

/**
 * 配件管理Mapper接口
 *
 * @author wsdm
 * @date 2020-11-06
 */
public interface FittingsMangeMapper {
    /**
     * 查询配件管理
     *
     * @param id 配件管理ID
     * @return 配件管理
     */
    public FittingsMange selectFittingsMangeById(Long id);

    /**
     * 查询配件管理列表
     *
     * @param fittingsMange 配件管理
     * @return 配件管理集合
     */
    public List<FittingsMange> selectFittingsMangeList(FittingsMange fittingsMange);

    /**
     * 查询配件管理
     *
     * @param produceNumber 产品编号
     * @return 配件管理
     */
    public List<FittingsMange> getFittingsByProduceNumber(String produceNumber);

    /**
     * 新增配件管理
     *
     * @param fittingsMange 配件管理
     * @return 结果
     */
    public int insertFittingsMange(FittingsMange fittingsMange);

    /**
     * 批量新增配件管理
     *
     * @param list 配件管理
     * @return 结果
     */
    public int insertFittingsMangeBatch(List<FittingsMange> list);

    /**
     * 修改配件管理
     *
     * @param fittingsMange 配件管理
     * @return 结果
     */
    public int updateFittingsMange(FittingsMange fittingsMange);

    /**
     * 删除配件管理
     *
     * @param id 配件管理ID
     * @return 结果
     */
    public int deleteFittingsMangeById(Long id);

    /**
     * 批量删除配件管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFittingsMangeByIds(Long[] ids);

    public FittingsMange getFittingInfoByFittingNumber(String fittingNumber);

    public List<FittingsMange> fittingInfoByListFittingNumber(List<String> fittingNumbers);

}
