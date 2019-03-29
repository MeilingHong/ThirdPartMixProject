package com.meiling.common.util.resource;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by huangzhou@ulord.com on 2018-10-30 16:36.
 */

public class GetResourceUtil {
    /**
     * 获取Drawable资源
     *
     * @param context
     * @param resId
     * @return
     */
    public static Drawable getDrawable(Context context, int resId) {
        return context.getResources().getDrawable(resId);
    }

    /**
     * 获取字符串资源
     *
     * @param context
     * @param resId
     * @return
     */
    public static String getString(Context context, int resId) {
        return context.getResources().getString(resId);
    }

    /**
     * 获取color资源
     *
     * @param context
     * @param resId
     * @return
     */
    public static int getColor(Context context, int resId) {
        return context.getResources().getColor(resId);
    }

    /**
     * 获取dimens资源
     *
     * @param context
     * @param resId
     * @return
     */
    public static float getDimens(Context context, int resId) {
        return context.getResources().getDimension(resId);
    }

    /**
     * 获取字符串数组资源
     *
     * @param context
     * @param resId
     * @return
     */
    public static String[] getStringArray(Context context, int resId) {
        return context.getResources().getStringArray(resId);
    }

    //------------------------------------------------------------------------------------------

    /**
     * 通过资源名获取资源ID【String 类型】
     *
     * @param context
     * @param stringResourceName
     * @return
     */
    public static int getStringResourceId(Context context, String stringResourceName) {
        return context.getResources().getIdentifier(stringResourceName, "string", context.getPackageName());
    }

    /**
     * 通过资源名获取资源ID【drawable 类型】
     *
     * @param context
     * @param drawableResourceName
     * @return
     */
    public static int getDrawableResourceId(Context context, String drawableResourceName) {
        return context.getResources().getIdentifier(drawableResourceName, "drawable", context.getPackageName());
    }

    /**
     * 通过资源名获取资源ID【mipmap 类型】
     *
     * @param context
     * @param mipmapResourceName
     * @return
     */
    public static int getMipmapResourceId(Context context, String mipmapResourceName) {
        return context.getResources().getIdentifier(mipmapResourceName, "mipmap", context.getPackageName());
    }

    /**
     * 通过资源名获取资源ID【drawable 类型】
     *
     * @param context
     * @param layoutResourceName
     * @return
     */
    public static int getLayoutResourceId(Context context, String layoutResourceName) {
        return context.getResources().getIdentifier(layoutResourceName, "layout", context.getPackageName());
    }

    //----------------------------------------------------------------------------------

    public static int getId(Context context, String resourceName) {
        return getIdentifierByType(context, resourceName, "id");
    }

    public static int getLayoutId(Context context, String resourceName) {
        return getIdentifierByType(context, resourceName, "layout");
    }

    public static int getStringId(Context context, String resourceName) {
        return getIdentifierByType(context, resourceName, "string");
    }

    public static int getDrawableId(Context context, String resourceName) {
        return getIdentifierByType(context, resourceName, "drawable");
    }

    public static int getMipmapId(Context context, String resourceName) {
        return getIdentifierByType(context, resourceName, "mipmap");
    }

    public static int getColorId(Context context, String resourceName) {
        return getIdentifierByType(context, resourceName, "color");
    }

    public static int getDimenId(Context context, String resourceName) {
        return getIdentifierByType(context, resourceName, "dimen");
    }

    public static int getAttrId(Context context, String resourceName) {
        return getIdentifierByType(context, resourceName, "attr");
    }

    public static int getStyleId(Context context, String resourceName) {
        return getIdentifierByType(context, resourceName, "style");
    }

    public static int getAnimId(Context context, String resourceName) {
        return getIdentifierByType(context, resourceName, "anim");
    }

    public static int getArrayId(Context context, String resourceName) {
        return getIdentifierByType(context, resourceName, "array");
    }

    public static int getIntegerId(Context context, String resourceName) {
        return getIdentifierByType(context, resourceName, "integer");
    }

    public static int getBoolId(Context context, String resourceName) {
        return getIdentifierByType(context, resourceName, "bool");
    }

    private static int getIdentifierByType(Context context, String resourceName, String defType) {
        return context.getResources().getIdentifier(resourceName, defType, context.getPackageName());
    }
}
