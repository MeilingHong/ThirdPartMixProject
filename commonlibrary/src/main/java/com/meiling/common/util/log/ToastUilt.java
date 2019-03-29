package com.meiling.common.util.log;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.meiling.common.R;
import com.meiling.common.util.datacheck.StringCheckUtil;


/**
 * 封装下Toast
 * Created by huangzhou@ulord.com on 2018-11-02 14:09.
 */

public class ToastUilt {
    //普通的Toast----直接使用系统的
    public static void toast(Context context, String msg, boolean isShort) {
        if (StringCheckUtil.isEmpty(msg)) {//避免无意义的Toast弹出
            return;
        }
        Toast.makeText(context, msg, isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_SHORT).show();
    }

    //自定义的Toast----给定自定义的View
    public static void showToastCenter(Context context, String msg, boolean isShort) {
        Toast mToast = null;
        if (mToast == null) {
            mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void showToast(Context context, String msg, int gravity, boolean isShort) {
        Toast mToast = null;
        if (mToast == null) {
            mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.setGravity(gravity, 0, 0);
        mToast.setDuration(isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void shortToastCenterRectangle(Context contect, String msg, int gravity, boolean isShort) {
        Toast toast = new Toast(contect);
        View view = LayoutInflater.from(contect).inflate(R.layout.layout_toast_style, null);
        TextView content = view.findViewById(R.id.toastContent);
        content.setText(msg);
        toast.setView(view);
        toast.setGravity(gravity, 0, 0);
        toast.setDuration(isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_SHORT);
        toast.show();
    }
}
