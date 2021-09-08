package com.wsdm.stock.service;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.stock.domain.MaterialReceive;
import com.wsdm.stock.domain.MaterialReceiveRequest;

import java.util.List;


/**
 * 物料收货订单信息Service接口
 *
 * @author wsdm
 * @date 2021-01-12
 */
public interface IMaterialReceiveService {
    /**
     * 查询物料收货订单信息
     *
     * @param id 物料收货订单信息ID
     * @return 物料收货订单信息
     */
    public MaterialReceive selectMaterialReceiveById(Long id);

    /**
     * 查询物料收货订单信息列表
     *
     * @param materialReceive 物料收货订单信息
     * @return 物料收货订单信息集合
     */
    public List<MaterialReceive> selectMaterialReceiveList(MaterialReceiveRequest materialReceive);

    /**
     * 新增物料收货订单信息
     *
     * @param materialReceive 物料收货订单信息
     * @return 结果
     */
    public int insertMaterialReceive(MaterialReceive materialReceive);

    /**
     * 修改物料收货订单信息
     *
     * @param materialReceive 物料收货订单信息
     * @return 结果
     */
    public int updateMaterialReceive(MaterialReceive materialReceive);

    /**
     * 审核物料收货订单信息
     *
     * @param materialReceive 物料收货订单信息
     * @return 结果
     */
    public AjaxResult approved(MaterialReceive materialReceive);

    /**
     * 批量删除物料收货订单信息
     *
     * @param ids 需要删除的物料收货订单信息ID
     * @return 结果
     */
    public int deleteMaterialReceiveByIds(Long[] ids);

    /**
     * 删除物料收货订单信息信息
     *
     * @param id 物料收货订单信息ID
     * @return 结果
     */
    public int deleteMaterialReceiveById(Long id);

}
