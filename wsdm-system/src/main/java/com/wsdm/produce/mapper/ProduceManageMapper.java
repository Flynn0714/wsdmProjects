package com.wsdm.produce.mapper;

import com.wsdm.produce.domain.ProduceManage;
import com.wsdm.produce.domain.ProduceManageVO;
import com.wsdm.produce.domain.ProduceRef;

import java.util.List;

/**
 * 产品管理Mapper接口
 *
 * @author wsdm
 * @date 2020-11-06
 */
public interface ProduceManageMapper {
    /**
     * 查询产品管理
     *
     * @param id 产品管理ID
     * @return 产品管理
     */
    public ProduceManage selectProduceManageById(Long id);

    /**
     * 查询产品管理
     *
     * @param id 产品管理ID
     * @return 产品管理
     */
    public ProduceManageVO selectProduceManageVoById(Long id);

    /**
     * 查询产品管理列表
     *
     * @param produceManage 产品管理
     * @return 产品管理集合
     */
    public List<ProduceManage> selectProduceManageList(ProduceManage produceManage);

    public List<ProduceManageVO> selectProduceManageVOList(ProduceManage produceManage);

    /**
     * 新增产品管理
     *
     * @param produceManage 产品管理
     * @return 结果
     */
    public int insertProduceManage(ProduceManage produceManage);

    /**
     * 新增产品管理
     *
     * @param list 产品管理详情
     * @return 结果
     */
    public int insertProduceDetailsBatch(List<ProduceRef> list);

    /**
     * 修改产品管理
     *
     * @param produceManage 产品管理
     * @return 结果
     */
    public int updateProduceManage(ProduceManage produceManage);

    /**
     * 删除产品管理
     *
     * @param id 产品管理ID
     * @return 结果
     */
    public int deleteProduceManageById(Long id);

    /**
     * 批量删除产品管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProduceManageByIds(Long[] ids);

    public int deleteProduceDetailsByProduceNumber(String produceNumber);

    public ProduceManage getProduceNumber(String produceNumber);

    public List<ProduceManage>  produceListByProduceNumber(List<String> list);
}
