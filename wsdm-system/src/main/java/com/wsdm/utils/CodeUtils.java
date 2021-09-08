package com.wsdm.utils;

import com.wsdm.common.exception.CustomException;
import com.wsdm.common.utils.StringUtils;

import java.util.List;

public class CodeUtils {

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


