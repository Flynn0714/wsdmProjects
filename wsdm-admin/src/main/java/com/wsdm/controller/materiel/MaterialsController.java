package com.wsdm.controller.materiel;

import com.wsdm.common.core.controller.BaseController;
import com.wsdm.common.core.page.TableDataInfo;
import com.wsdm.materiel.domain.DefaultSupplierConfig;
import com.wsdm.materiel.service.MaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 查询物料的接口
 *
 * @author wangr
 * @version 1.0
 * @date 2021/6/19 14:26
 */
@RestController
@RequestMapping("/materiel/info/manage")
public class MaterialsController extends BaseController {
    @Autowired
    private MaterialsService materialsService;
    /**
     * 用于销售--查询物料
     */
    @GetMapping("/salesList")
    public TableDataInfo salesList(String type, String number, String name) {
        startPage();
        List<Map> list = materialsService.salesList(type, number, name);
        return getDataTable(list);
    }

    /**
     * 用于采购--查询物料
     */
    @GetMapping("/purchaseList")
    public TableDataInfo purchaseList(String type, String number, String name) {
        startPage();
        List<Map> list = materialsService.purchaseList(type, number, name);
        return getDataTable(list);
    }

    /**
     * 加工录入
     * @param type
     * @param number
     * @param name
     * @return
     */
    @GetMapping("/prolist")
    public TableDataInfo produceList(String type,String number,String name){
        startPage();
        List<Map> list = materialsService.salesListByRelation(type,number,name);
        return getDataTable(list);
    }
}
