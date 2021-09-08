package com.wsdm.stock.service;

import com.wsdm.stock.domain.SkStockInventoryInfo;
import com.wsdm.stock.domain.SkStockInventoryInfoVO;

import java.util.List;

/**
 * 库存盘点信息Service接口
 *
 * @author wsdm
 * @date 2021-05-25
 */
public interface ISkStockInventoryInfoService
{
    String DELETE       = "0";
    String WAIT_START   = "1";
    String WAIT_RECORD  = "2";
    String WAIT_ACCOUNT = "3";
    String COMPLETE     = "4";

    /**
     * 查询库存盘点信息
     *
     * @param id 库存盘点信息ID
     * @return 库存盘点信息
     */
    public SkStockInventoryInfoVO selectSkStockInventoryInfoById(Long id);

    /**
     * 查询库存盘点信息列表
     *
     * @param skStockInventoryInfo 库存盘点信息
     * @return 库存盘点信息集合
     */
    public List<SkStockInventoryInfo> selectSkStockInventoryInfoList(SkStockInventoryInfo skStockInventoryInfo);

    /**
     * 新增库存盘点信息
     *
     * @param skStockInventoryInfoVO 库存盘点信息
     * @return 结果
     */
    public int insertSkStockInventoryInfo(SkStockInventoryInfoVO skStockInventoryInfoVO);

    /**
     * 修改库存盘点信息
     *
     * @param skStockInventoryInfoVO 库存盘点信息
     * @return 结果
     */
    public int updateSkStockInventoryInfo(SkStockInventoryInfoVO skStockInventoryInfoVO);

    /**
     * 批量删除库存盘点信息
     *
     * @param ids 需要删除的库存盘点信息ID
     * @return 结果
     */
    public int deleteSkStockInventoryInfoByIds(Long[] ids);

    /**
     * 删除库存盘点信息信息
     *
     * @param id 库存盘点信息ID
     * @return 结果
     */
    public int deleteSkStockInventoryInfoById(Long id);

    int deleteSafe(Long id, String username);
}
