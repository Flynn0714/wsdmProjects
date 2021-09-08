package com.wsdm.produce.service.impl;

import com.wsdm.common.enums.WisdomBusinessStatus;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.produce.domain.ProduceModel;
import com.wsdm.produce.mapper.ProduceModelMapper;
import com.wsdm.produce.service.IProduceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品型号Service业务层处理
 *
 * @author wsdm
 * @date 2020-11-06
 */
@Service
public class ProduceModelServiceImpl implements IProduceModelService {
    @Autowired
    private ProduceModelMapper produceModelMapper;

    /**
     * 查询产品型号
     *
     * @param id 产品型号ID
     * @return 产品型号
     */
    @Override
    public ProduceModel selectProduceModelById(Long id) {
        return produceModelMapper.selectProduceModelById(id);
    }

    /**
     * 查询产品型号列表
     *
     * @param produceModel 产品型号
     * @return 产品型号
     */
    @Override
    public List<ProduceModel> selectProduceModelList(ProduceModel produceModel) {
        return produceModelMapper.selectProduceModelList(produceModel);
    }

    /**
     * 新增产品型号
     *
     * @param produceModel 产品型号
     * @return 结果
     */
    @Override
    public int insertProduceModel(ProduceModel produceModel) {
        produceModel.setCreateTime(DateUtils.getNowDate());
        produceModel.setStatus(WisdomBusinessStatus.STATUS_NORMAL.getCode());
        return produceModelMapper.insertProduceModel(produceModel);
    }

    /**
     * 修改产品型号
     *
     * @param produceModel 产品型号
     * @return 结果
     */
    @Override
    public int updateProduceModel(ProduceModel produceModel) {
        produceModel.setUpdateTime(DateUtils.getNowDate());
        produceModel.setUpdateBy(SecurityUtils.getUsername());
        return produceModelMapper.updateProduceModel(produceModel);
    }

    /**
     * 批量删除产品型号
     *
     * @param ids 需要删除的产品型号ID
     * @return 结果
     */
    @Override
    public int deleteProduceModelByIds(Long[] ids) {
        return produceModelMapper.deleteProduceModelByIds(ids);
    }

    /**
     * 删除产品型号信息
     *
     * @param id 产品型号ID
     * @return 结果
     */
    @Override
    public int deleteProduceModelById(Long id) {
        return produceModelMapper.deleteProduceModelById(id);
    }
}
