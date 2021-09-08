package com.wsdm.pd.service.impl;

import com.wsdm.common.utils.DateUtils;
import com.wsdm.pd.domain.PdProduceHardware;
import com.wsdm.pd.mapper.PdProduceHardwareMapper;
import com.wsdm.pd.service.IPdProduceHardwareService;
import com.wsdm.stock.service.IPdProduceHardwareSupport;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品五金配件Service业务层处理
 *
 * @author wsdm
 * @date 2021-06-21
 */
@Service
public class PdProduceHardwareServiceImpl implements IPdProduceHardwareService, IPdProduceHardwareSupport {
    @Autowired
    private PdProduceHardwareMapper pdProduceHardwareMapper;

    /**
     * 查询产品五金配件
     *
     * @param id 产品五金配件ID
     * @return 产品五金配件
     */
    @Override
    public PdProduceHardware selectPdProduceHardwareById(Long id) {
        return pdProduceHardwareMapper.selectPdProduceHardwareById(id);
    }

    /**
     * 查询产品五金配件列表
     *
     * @param pdProduceHardware 产品五金配件
     * @return 产品五金配件
     */
    @Override
    public List<PdProduceHardware> selectPdProduceHardwareList(PdProduceHardware pdProduceHardware) {
        return pdProduceHardwareMapper.selectPdProduceHardwareList(pdProduceHardware);
    }

    /**
     * 新增产品五金配件
     *
     * @param pdProduceHardware 产品五金配件
     * @return 结果
     */
    @Override
    public int insertPdProduceHardware(PdProduceHardware pdProduceHardware) {
        pdProduceHardware.setCreateTime(DateUtils.getNowDate());
        return pdProduceHardwareMapper.insertPdProduceHardware(pdProduceHardware);
    }

    /**
     * 修改产品五金配件
     *
     * @param pdProduceHardware 产品五金配件
     * @return 结果
     */
    @Override
    public int updatePdProduceHardware(PdProduceHardware pdProduceHardware) {
        pdProduceHardware.setUpdateTime(DateUtils.getNowDate());
        return pdProduceHardwareMapper.updatePdProduceHardware(pdProduceHardware);
    }

    /**
     * 批量删除产品五金配件
     *
     * @param ids 需要删除的产品五金配件ID
     * @return 结果
     */
    @Override
    public int deletePdProduceHardwareByIds(Long[] ids) {
        return pdProduceHardwareMapper.deletePdProduceHardwareByIds(ids);
    }

    /**
     * 删除产品五金配件信息
     *
     * @param id 产品五金配件ID
     * @return 结果
     */
    @Override
    public int deletePdProduceHardwareById(Long id) {
        return pdProduceHardwareMapper.deletePdProduceHardwareById(id);
    }

    @Override
    public String getModel(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        PdProduceHardware param = new PdProduceHardware();
        param.setCode(code);
        param.setStatus("0");
        List<PdProduceHardware> pdProduceHardwares = pdProduceHardwareMapper.selectPdProduceHardwareList(param);
        return CollectionUtils.isNotEmpty(pdProduceHardwares) ? pdProduceHardwares.get(0).getModel() : null;
    }
}
