package com.meiling.common.util.datahandle;

/**
 * 将数字转换成小数，默认2位小数
 * <p>
 * Created by huangzhou@ulord.com on 2018-10-26 14:33.
 *
 * https://blog.csdn.net/lonely_fireworks/article/details/7962171/ 额外的格式可以参考这里
 */

public class NumberToStringUtil {
    public static String numberToString(int value, int digit) {
        if (digit < 1) {
            return String.format("%.2f", value);
        } else {
            return String.format("%." + digit + "f", value);
        }
    }

    public static String numberToString(long value, int digit) {
        if (digit < 1) {
            return String.format("%.2f", value);
        } else {
            return String.format("%." + digit + "f", value);
        }
    }

    public static String numberToString(float value, int digit) {
        if (digit < 1) {
            return String.format("%.2f", value);
        } else {
            return String.format("%." + digit + "f", value);
        }
    }

    public static String numberToString(double value, int digit) {
        if (digit < 1) {
            return String.format("%.2f", value);
        } else {
            return String.format("%." + digit + "f", value);
        }
    }
}
