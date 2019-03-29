package com.meiling.common.util.pagejump;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * 由于使用基类/父类通用的封装似乎存在不走onActivityResult回调的问题，抽象
 * Created by huangzhou@ulord.com on 2018-11-02 09:22.
 */

public class PageJumpUtil {

    public static void activityJumpForResult(Activity activity, Class toPage, Bundle pageParams,int requestCode) {
        Intent intent = new Intent(activity,toPage);
        intent.putExtras(pageParams);
        activity.startActivityForResult(intent,requestCode);
    }

    public static void activityJumpForResult(Activity activity, Intent intent,int requestCode) {
        activity.startActivityForResult(intent,requestCode);
    }

    public static void activityJumpForResult(AppCompatActivity activity, Class toPage, Bundle pageParams, int requestCode) {
        Intent intent = new Intent(activity,toPage);
        intent.putExtras(pageParams);
        activity.startActivityForResult(intent,requestCode);
    }

    public static void activityJumpForResult(AppCompatActivity activity, Intent intent, int requestCode) {
        activity.startActivityForResult(intent,requestCode);
    }

    public static void fragmentJumpForResult(Fragment fragment,Class toPage, Bundle pageParams,int requestCode){
        Intent intent = new Intent(fragment.getActivity(),toPage);
        intent.putExtras(pageParams);
        fragment.startActivityForResult(intent,requestCode);
    }

    public static void fragmentJumpForResult(Fragment fragment, Intent intent,int requestCode) {
        fragment.startActivityForResult(intent,requestCode);
    }

    public static void fragmentJumpForResult(android.support.v4.app.Fragment fragment, Class toPage, Bundle pageParams, int requestCode){
        Intent intent = new Intent(fragment.getActivity(),toPage);
        intent.putExtras(pageParams);
        fragment.startActivityForResult(intent,requestCode);
    }

    public static void fragmentJumpForResult(android.support.v4.app.Fragment fragment, Intent intent,int requestCode) {
        fragment.startActivityForResult(intent,requestCode);
    }
}
