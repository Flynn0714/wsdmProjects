package com.wsdm.pd.mapper;

import com.wsdm.pd.domain.PdProduceHardware;

import java.util.List;

/**
 * 产品五金配件Mapper接口
 *
 * @author wsdm
 * @date 2021-06-21
 */
public interface PdProduceHardwareMapper {
    /**
     * 查询产品五金配件
     *
     * @param id 产品五金配件ID
     * @return 产品五金配件
     */
    public PdProduceHardware selectPdProduceHardwareById(Long id);

    /**
     * 查询产品五金配件列表
     *
     * @param pdProduceHardware 产品五金配件
     * @return 产品五金配件集合
     */
    public List<PdProduceHardware> selectPdProduceHardwareList(PdProduceHardware pdProduceHardware);

    /**
     * 新增产品五金配件
     *
     * @param pdProduceHardware 产品五金配件
     * @return 结果
     */
    public int insertPdProduceHardware(PdProduceHardware pdProduceHardware);

    /**
     * 修改产品五金配件
     *
     * @param pdProduceHardware 产品五金配件
     * @return 结果
     */
    public int updatePdProduceHardware(PdProduceHardware pdProduceHardware);

    /**
     * 删除产品五金配件
     *
     * @param id 产品五金配件ID
     * @return 结果
     */
    public int deletePdProduceHardwareById(Long id);

    /**
     * 批量删除产品五金配件
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePdProduceHardwareByIds(Long[] ids);
}
