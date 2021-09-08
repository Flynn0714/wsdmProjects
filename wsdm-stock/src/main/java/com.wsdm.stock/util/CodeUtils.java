package com.wsdm.stock.util;

import com.wsdm.common.exception.CustomException;
import com.wsdm.common.utils.StringUtils;

import java.util.List;

public class CodeUtils {

    public static void getCodeList(String code, String maxCode, List list, int index) {
        int maxNumber = 0;

        for (int i = 0; i < list.size(); i++) {

            if (maxCode != null) {
                String number = maxCode.replace(code, "");
                maxNumber = Integer.parseInt(number);
            }

            maxNumber++;

            code = code + StringUtils.spliceValue(maxNumber, index);

        }
    }

    public static String getOneCode(String prefix, String maxCode, int index) {
        int maxNumber = 0;

        if (StringUtils.isNotEmpty(maxCode)) {
            String number = maxCode.replace(prefix, "");
            maxNumber = Integer.parseInt(number);
        }
        maxNumber++;
        String code = prefix + StringUtils.spliceValue(maxNumber, index);
        if (StringUtils.isEmpty(code)) {
            throw new CustomException("编码生成失败");
        }
        return code;
    }
}


