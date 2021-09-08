package com.wsdm.pd.mapper;

import com.wsdm.pd.domain.PdContributionDetail;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

/**
 * 工绩单明细Mapper接口
 *
 * @author wsdm
 * @date 2021-06-16
 */
public interface PdContributionDetailMapper
{
    /**
     * 查询工绩单明细
     *
     * @param id 工绩单明细ID
     * @return 工绩单明细
     */
    public PdContributionDetail selectPdContributionDetailById(Long id);

    /**
     * 查询工绩单明细列表
     *
     * @param pdContributionDetail 工绩单明细
     * @return 工绩单明细集合
     */
    public List<PdContributionDetail> selectPdContributionDetailList(PdContributionDetail pdContributionDetail);

    /**
     * 新增工绩单明细
     *
     * @param pdContributionDetail 工绩单明细
     * @return 结果
     */
    public int insertPdContributionDetail(PdContributionDetail pdContributionDetail);

    /**
     * 修改工绩单明细
     *
     * @param pdContributionDetail 工绩单明细
     * @return 结果
     */
    public int updatePdContributionDetail(PdContributionDetail pdContributionDetail);

    /**
     * 删除工绩单明细
     *
     * @param id 工绩单明细ID
     * @return 结果
     */
    public int deletePdContributionDetailById(Long id);

    /**
     * 批量删除工绩单明细
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePdContributionDetailByIds(Long[] ids);

    /**
     * 查询员工总价格之和（累计加工金额）
     */
    @Select("select sum(pd.cost_time_total) from pd_contribution pc left join pd_contribution_detail pd on pc.id = pd.contribution_id\n" +
            "where pc.employee_name = #{employeeName} and pd.`status` = '1'")
    public BigDecimal selectTotalAmount(String employeeName);
}
