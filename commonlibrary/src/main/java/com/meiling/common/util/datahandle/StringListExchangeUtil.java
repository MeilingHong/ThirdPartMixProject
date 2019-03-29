package com.meiling.common.util.datahandle;


import com.meiling.common.util.datacheck.CheckListUtil;
import com.meiling.common.util.datacheck.StringCheckUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 字符串与字符串列表间的转换
 * Created by huangzhou@ulord.com on 2018-10-31 14:42.
 */

public class StringListExchangeUtil {
    /**
     * 将字符串分割成
     *
     * @param listString
     * @param splitRegex
     * @return
     */
    public static List<String> stringToList(String listString, String splitRegex) {
        if (StringCheckUtil.isEmpty(listString) || StringCheckUtil.isEmpty(splitRegex)) {
            return null;
        }
        return Arrays.asList(listString.split(splitRegex));
    }

    public static String listToString(List<String> stringList, String splitRegex) {
        if (CheckListUtil.isEmpty(stringList) || StringCheckUtil.isEmpty(splitRegex)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int size = stringList.size();
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                stringBuilder.append(stringList);
            }else{
                stringBuilder.append(stringList);
                stringBuilder.append(splitRegex);
            }
        }
        return stringBuilder.toString();
    }
}
