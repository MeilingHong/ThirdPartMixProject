package com.meiling.common.util.sysinfo;

import android.content.Context;

/**
 * 获取屏幕的宽高数据
 * <p>
 * Created by huangzhou@ulord.com on 2018-10-30 16:54.
 */

public class ScreenInfoUtil {
    /**
     * 获取屏幕的高 单位：px
     *
     * @param context
     * @return
     */
    public static int getScreenHeightPx(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕的宽 单位：px
     *
     * @param context
     * @return
     */
    public static int getScreenWidthPx(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕的密度 【帮助进行单位换算,PX----DP,DIP】
     *
     * @param context
     * @return
     */
    public static float getScreenDensity(Context context){
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 获取屏幕的缩放密度 【帮助进行单位换算,PX----SP】
     *
     * @param context
     * @return
     */
    public static float getScreenScale(Context context){
        return context.getResources().getDisplayMetrics().scaledDensity;
    }
}
