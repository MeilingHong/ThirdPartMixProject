package com.meiling.weibo.share;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;

import com.meiling.weibo.common.callback.share.WeiboShareCallback;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.sina.weibo.sdk.utils.Utility;

/**
 * Created by Hz on 2018-12-11.
 */

public class WeiboShareUtil implements WbShareCallback {
    private WbShareHandler shareHandler;
    private WeiboShareCallback weiboShareCallback;
    private Activity activity;

    private WeiboShareUtil() {

    }

    public static WeiboShareUtil getInstances() {
        return WeiboShareUtilHolder.sinaShare;
    }

    //----------------------------------------------------------------------------------------

    /*
     * 在需要调用的页面进行声明，并处理回调
     */
    public void initShare(Activity activity, WeiboShareCallback weiboShareCallback) {
        this.activity = activity;
        shareHandler = new WbShareHandler(activity);
        shareHandler.registerApp();
        this.weiboShareCallback = weiboShareCallback;
    }

    /*
     * TODO onActivityResult中调用
     */
    public void setResultIntent(Intent intent) {
        if (shareHandler != null) {
            shareHandler.doResultIntent(intent, this);
        }
    }

    public void shareText(String text) {
        WeiboMultiMessage message = new WeiboMultiMessage();

        message.textObject = new TextObject();
        message.textObject.text = text;

        shareHandler.shareMessage(message, false);
    }

    /**
     * 分享单张图片
     *
     * @param bitmap
     */
    public void shareImage(Bitmap bitmap) {
        WeiboMultiMessage message = new WeiboMultiMessage();
        ImageObject imageObject = new ImageObject();
// 设置 Bitmap 类型的图片到视频对象里         设置缩略图。 注意：最终压缩过的缩略图大小 不得超过 32kb。
        imageObject.setImageObject(bitmap);

        message.textObject = new TextObject();

        message.imageObject = imageObject;
        if (shareHandler == null) {
            shareHandler = new WbShareHandler(activity);
            shareHandler.registerApp();
        }
        shareHandler.shareMessage(message, false);
        if (bitmap != null) {
            bitmap.recycle();
        }
    }

    /**
     * 分享多张图片
     *
     * @param bitmap
     */
    public void shareMultiImage(Bitmap bitmap) {
        WeiboMultiMessage message = new WeiboMultiMessage();
        ImageObject imageObject = new ImageObject();
// 设置 Bitmap 类型的图片到视频对象里         设置缩略图。 注意：最终压缩过的缩略图大小 不得超过 32kb。
        imageObject.setImageObject(bitmap);

        message.textObject = new TextObject();

        message.imageObject = imageObject;
        if (shareHandler == null) {
            shareHandler = new WbShareHandler(activity);
            shareHandler.registerApp();
        }
        shareHandler.shareMessage(message, false);
        if (bitmap != null) {
            bitmap.recycle();
        }
    }

    /**
     * 分享链接
     *
     * @param title
     * @param desc
     * @param url
     * @param icon
     */
    public void shareWeb(String title, String desc, String url, Bitmap icon) {
        WeiboMultiMessage message = new WeiboMultiMessage();

        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title = title;
        mediaObject.description = desc;
        // 设置 Bitmap 类型的图片到视频对象里
        mediaObject.setThumbImage(icon);
        mediaObject.actionUrl = url;
        mediaObject.defaultText = desc;
        message.mediaObject = mediaObject;

        message.textObject = new TextObject();
        message.textObject.text = desc;

        if (shareHandler == null) {
            shareHandler = new WbShareHandler(activity);
            shareHandler.registerApp();
        }
        shareHandler.shareMessage(message, false);
        if (icon != null) {
            icon.recycle();
        }
    }

    //----------------------------------------------------------------------------------------

    @Override
    public void onWbShareSuccess() {
        if (weiboShareCallback != null) {
            weiboShareCallback.onWeiboShareSuccess();
        }
    }

    @Override
    public void onWbShareCancel() {
        if (weiboShareCallback != null) {
            weiboShareCallback.onWeiboShareCancel();
        }
    }

    @Override
    public void onWbShareFail() {
        if (weiboShareCallback != null) {
            weiboShareCallback.onWeiboShareFail();
        }
    }

    //----------------------------------------------------------------------------------------
    public static class WeiboShareUtilHolder {
        private static WeiboShareUtil sinaShare = new WeiboShareUtil();
    }
}
