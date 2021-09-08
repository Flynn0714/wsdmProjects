package com.wsdm.materiel.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MaterialsMapper {

    List<Map> salesList(@Param("type") String type, @Param("number") String number, @Param("name") String name);

    List<Map> purchaseList(@Param("type") String type, @Param("number") String number, @Param("name") String name);

    List<Map> salesListByRelation(@Param("type") String type, @Param("number") String number, @Param("name") String name);
}
