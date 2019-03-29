package com.meiling.common.util.datacheck;

/**
 * Created by huangzhou@ulord.com on 2018-10-26 10:49.
 */

public class StringCheckUtil {
    public static boolean isEmpty(String string) {
        return string == null || string.length() < 1;
    }

    public static boolean isEmpty(CharSequence string) {
        return string == null || string.length() < 1;
    }
}
