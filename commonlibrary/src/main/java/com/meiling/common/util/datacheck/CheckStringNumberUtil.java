package com.meiling.common.util.datacheck;

/**
 * Created by Hz on 2018/9/18.
 */

public class CheckStringNumberUtil {
    public static boolean isNumeric(char[] str) {
        //1  用正则表达式   判断其是否是数字
        String res=String.valueOf(str);
        return res.matches("[\\+-]?[0-9]*(\\.[0-9])?([eE][\\+-]?[0-9]+)?");
        /**
         * 正则表达式说明:
         * [\+-]?  + -号可出现也可不出现
         * [0-9]*  整数部分是否出现    [0-9]可以用\\d代替
         * (\.[0-9])?  出现小数点后面必须跟数字
         * ([eE][\+-]?[0-9]+)  若有指数部分E或e肯定出现 + -可以不出现
         *                      紧接着可以跟着整数，也可以什么都没有
         */
    }

    public static boolean isNumeric(String res) {
        //1  用正则表达式   判断其是否是数字
        return res.matches("[\\+-]?[0-9]*(\\.[0-9])?([eE][\\+-]?[0-9]+)?");
        /**
         * 正则表达式说明:
         * [\+-]?  + -号可出现也可不出现
         * [0-9]*  整数部分是否出现    [0-9]可以用\\d代替
         * (\.[0-9])?  出现小数点后面必须跟数字
         * ([eE][\+-]?[0-9]+)  若有指数部分E或e肯定出现 + -可以不出现
         *                      紧接着可以跟着整数，也可以什么都没有
         */
    }
}
