package com.wsdm.controller.pd;

import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.pd.domain.PdAssociation;
import com.wsdm.pd.domain.PdProduceManage;
import com.wsdm.pd.mapper.PdAssociationMapper;
import com.wsdm.pd.service.IPdAssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 产品关联 Controller
 * @author 符方龙
 * @date 2021年8月27日09:21:20
 */

@RestController
@RequestMapping("/pd/associ")
public class PdAssociationController extends BaseController {
    @Autowired
    IPdAssociationService iPdAssociationService;

    @Autowired
    PdAssociationMapper pdAssociationMapper;

    /**
     * 查询列表
     * @param pdAssociation
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('pd:associ:querypds')")
    @GetMapping("/querypds")
    public TableDataInfo queryList(PdAssociation pdAssociation){
        startPage();
        List<PdAssociation> pdAssociationList = iPdAssociationService.queryPdAssociList(pdAssociation);
        return getDataTable(pdAssociationList);
    }

    /**
     * 新增  参数：名称materialName、规格 specs、产品类型-字典key值 detailsType、图纸号 annexName、图纸url annexUrl 备注 remark
     * @param pdAssociations
     * @return
     */
    @PreAuthorize("@ss.hasPermi('pd:associ:add')")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody List<PdAssociation> pdAssociations) {
        return iPdAssociationService.insertPdAssociList(pdAssociations);
    }

    /**
     * 编辑 参数：id materialName specs detailsType remark annexName annexUrl
     * @param pdAssociation
     * @return
     */
    @PreAuthorize("@ss.hasPermi('pd:associ:edit')")
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody PdAssociation pdAssociation){
        return iPdAssociationService.editPdAssoci(pdAssociation);
    }

    /**
     * 关联产品 参数：id detailsType pdId
     * @param pdAssociation
     * @return
     */
    @PreAuthorize("@ss.hasPermi('pd:associ:relation')")
    @PostMapping("/relation")
    public AjaxResult relation(@RequestBody PdAssociation pdAssociation){
        return iPdAssociationService.toRelation(pdAssociation);
    }

    /**
     * 查询内部产品
     * @param pdProduceManage
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('pd:associ:querypros')")
    @GetMapping("/querypros")
    public TableDataInfo selectInnerProduct(PdProduceManage pdProduceManage){
        startPage();
        return getDataTable(iPdAssociationService.selectPdProduct(pdProduceManage));
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PreAuthorize("@ss.hasPermi('pd:associ:delete')")
    @GetMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return iPdAssociationService.deletePdAssoci(id);
    }

    /**
     * 内部产品信息查看
     * @param id
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('pd:associ:queryinfo')")
    @GetMapping("/queryinfo/{id}")
    public Object queryPdInfo(@PathVariable("id") Long id){
        if (!Objects.isNull(pdAssociationMapper.selectPdAssicoById(id).getPdId())){
            String pdId = pdAssociationMapper.selectPdAssicoById(id).getPdId();
            return iPdAssociationService.queryInnerPdInfo(pdId);
        }else {
            return AjaxResult.error("此产品未关联！");
        }
    }
}
