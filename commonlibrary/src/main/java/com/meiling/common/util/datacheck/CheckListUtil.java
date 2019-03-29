package com.meiling.common.util.datacheck;

import java.util.List;

/**
 * Created by huangzhou@ulord.com on 2018-10-31 14:54.
 */

public class CheckListUtil {
    public static boolean isEmpty(List string) {
        return string == null || string.size()<1;
    }
}
