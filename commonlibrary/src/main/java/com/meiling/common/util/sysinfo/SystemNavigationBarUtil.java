package com.meiling.common.util.sysinfo;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;

import java.lang.reflect.Method;

/**
 * Created by huangzhou@ulord.com on 2018-10-31 14:05.
 */

public class SystemNavigationBarUtil {
    private static int getDpi(Activity activity) {
        int dpi = 0;
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            dpi = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    public static int getNavigationBarHeight(Activity activity){
        int height = 0;
        height = getDpi(activity) - activity.getWindowManager().getDefaultDisplay().getHeight();
        return height;
    }
}
