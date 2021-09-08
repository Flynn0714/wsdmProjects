package com.wsdm.produce.mapper;

import com.wsdm.produce.domain.ProduceModel;

import java.util.List;

/**
 * 产品型号Mapper接口
 *
 * @author wsdm
 * @date 2020-11-06
 */
public interface ProduceModelMapper {
    /**
     * 查询产品型号
     *
     * @param id 产品型号ID
     * @return 产品型号
     */
    public ProduceModel selectProduceModelById(Long id);

    public ProduceModel selectProduceModelByModel(String model);

    /**
     * 查询产品型号列表
     *
     * @param produceModel 产品型号
     * @return 产品型号集合
     */
    public List<ProduceModel> selectProduceModelList(ProduceModel produceModel);

    /**
     * 新增产品型号
     *
     * @param produceModel 产品型号
     * @return 结果
     */
    public int insertProduceModel(ProduceModel produceModel);

    /**
     * 修改产品型号
     *
     * @param produceModel 产品型号
     * @return 结果
     */
    public int updateProduceModel(ProduceModel produceModel);

    /**
     * 删除产品型号
     *
     * @param id 产品型号ID
     * @return 结果
     */
    public int deleteProduceModelById(Long id);

    /**
     * 批量删除产品型号
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProduceModelByIds(Long[] ids);
}
