package com.wsdm.stock.mapper;

import com.wsdm.stock.domain.MaterialReceive;
import com.wsdm.stock.domain.MaterialReceiveDetail;
import com.wsdm.stock.domain.MaterialReceiveRequest;

import java.util.List;

/**
 * 物料收货订单信息Mapper接口
 *
 * @author wsdm
 * @date 2021-01-12
 */
public interface MaterialReceiveMapper {
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
     * 删除物料收货订单信息
     *
     * @param id 物料收货订单信息ID
     * @return 结果
     */
    public int deleteMaterialReceiveById(Long id);

    /**
     * 批量删除物料收货订单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterialReceiveByIds(Long[] ids);
    
    /**
     * 
     * 领料单号
     * @param code 
     * @author wangr 
     * @date 2021/1/12 15:09
     * @return java.lang.String
     */
    String getMaxMaterialReceiveCode(String code);
    
    /**
     * 查询物料采购订单详情
     *
     * @param id 物料采购订单详情ID
     * @return 物料采购订单详情
     */
    public MaterialReceiveDetail selectMaterialReceiveDetailById(Long id);

    /**
     * 查询物料采购订单详情列表
     *
     * @param materialReceiveDetail 物料采购订单详情
     * @return 物料采购订单详情集合
     */
    public List<MaterialReceiveDetail> selectMaterialReceiveDetailList(MaterialReceiveDetail materialReceiveDetail);

    /**
     * 新增物料采购订单详情
     *
     * @param materialReceiveDetail 物料采购订单详情
     * @return 结果
     */
    public int insertMaterialReceiveDetail(MaterialReceiveDetail materialReceiveDetail);

    /**
     * 批量新增物料采购订单详情
     *
     * @param materialReceiveDetail 物料采购订单详情
     * @return 结果
     */
    public int insertMaterialReceiveDetailBatch(List<MaterialReceiveDetail> materialReceiveDetails);

    /**
     * 修改物料采购订单详情
     *
     * @param materialReceiveDetail 物料采购订单详情
     * @return 结果
     */
    public int updateMaterialReceiveDetail(MaterialReceiveDetail materialReceiveDetail);

    /**
     * 删除物料采购订单详情
     *
     * @param id 物料采购订单详情ID
     * @return 结果
     */
    public int deleteMaterialReceiveDetailById(Long id);

    /**
     * 删除物料采购订单详情
     *
     * @param number 物料采购订单详情单号
     * @return 结果
     */
    public int deleteMaterialReceiveDetailByNumber(String number);
    /**
     * 批量删除物料采购订单详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterialReceiveDetailByIds(Long[] ids);
}
