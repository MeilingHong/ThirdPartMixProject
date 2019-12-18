package com.meiling.qq.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.meiling.qq.common.bean.ReturnCode;
import com.meiling.qq.common.callback.share.QQShareCallback;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * Created by Hz on 2018-12-11.
 */

public class QQShareUtil implements QQShareCallback {

    private Tencent mTencent;
    private QQShareCallback qqShareCallback;
    private int mExtarFlag = 0x00 /*| QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN*/;

    private QQShareUtil() {
    }

    public static QQShareUtil getInstances() {
        return QQShareUtilHolder.instances;
    }
    //---------------------------------------------------------------------------------

    private IUiListener shareResultListener = new IUiListener() {
        @Override
        public void onComplete(Object o) {
            if (qqShareCallback != null) {
                qqShareCallback.onQQShareSuccess();
            }
        }

        @Override
        public void onError(UiError uiError) {
            if (qqShareCallback != null) {
                qqShareCallback.onQQShareFail();
            }
        }

        @Override
        public void onCancel() {
            if (qqShareCallback != null) {
                qqShareCallback.onQQShareCancel();
            }
        }
    };

    public void initShare(Context context, String APP_ID) {
        mTencent = Tencent.createInstance(APP_ID, context.getApplicationContext());
    }

    public void setQqShareCallback(QQShareCallback qqShareCallback) {
        this.qqShareCallback = qqShareCallback;
    }

    /**
     * case R.id.radioBtn_share_type_audio:
     * shareType = QQShare.SHARE_TO_QQ_TYPE_AUDIO;
     * break;
     * case R.id.radioBtn_share_type_default:
     * shareType = QQShare.SHARE_TO_QQ_TYPE_DEFAULT;
     * break;
     * case R.id.radioBtn_share_type_image:
     * shareType = QQShare.SHARE_TO_QQ_TYPE_IMAGE;
     * break;
     * case R.id.radioBtn_share_type_app:
     * shareType = QQShare.SHARE_TO_QQ_TYPE_APP;
     * break;
     */

//            if (shareType != QQShare.SHARE_TO_QQ_TYPE_IMAGE) {
//                params.putString(QQShare.SHARE_TO_QQ_TITLE, title.getText().toString());
//                params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetUrl.getText().toString());
//                params.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary.getText().toString());
//            }
//            if (shareType == QQShare.SHARE_TO_QQ_TYPE_IMAGE) {
//                params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imageUrl.getText().toString());
//            } else {
//                params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imageUrl.getText().toString());
//            }
//            params.putString(shareType == QQShare.SHARE_TO_QQ_TYPE_IMAGE ? QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL
//                    : QQShare.SHARE_TO_QQ_IMAGE_URL, imageUrl.getText().toString());
//            params.putString(QQShare.SHARE_TO_QQ_APP_NAME, appName.getText().toString());
//            params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, shareType);
//            params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, mExtarFlag);
//            if (shareType == QQShare.SHARE_TO_QQ_TYPE_AUDIO) {
//                params.putString(QQShare.SHARE_TO_QQ_AUDIO_URL, mEditTextAudioUrl.getText().toString());
//            }
//            if ((mExtarFlag & QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN) != 0) {
//                showToast("在好友选择列表会自动打开分享到qzone的弹窗~~~");
//            } else if ((mExtarFlag & QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE) != 0) {
//                showToast("在好友选择列表隐藏了qzone分享选项~~~");
//            }
//                    /*QQ分享增加ARK*/
//              String arkStr = arkInfo.getText().toString();
//            params.putString(QQShare.SHARE_TO_QQ_ARK_INFO, arkStr);

    /**
     * TODO 似乎QQ中的分享没有单纯的文字消息这个概念，<p>SHARE_TO_QQ_TARGET_URL</p> 这个参数是必须要传的
     * TODO 所以至少都带有一个链接【除图片类型的分享除外】
     *
     * @param activity
     * @param title
     * @param targetUrl
     * @param summary
     * @param isLocalImg
     * @param imgUrl
     * @param isToQzone
     */
    public void shareQQText(Activity activity, String title, String targetUrl, String summary, boolean isLocalImg, String imgUrl, boolean isToQzone) {
        if (mTencent != null) {
            final Bundle params = new Bundle();

            /**
             * 下面三个参数是必须全部要传的，不能够为空
             */
            params.putString(QQShare.SHARE_TO_QQ_TITLE, title.toString());
            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetUrl != null ? targetUrl.toString() : "");// 这个参数必须是一个链接
            params.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary.toString());

            if (isLocalImg) {
                if (imgUrl != null && imgUrl.length() > 0) {
                    params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imgUrl);
                }
            } else {
                if (imgUrl != null && imgUrl.length() > 0 && imgUrl.toLowerCase().startsWith("http")) {
                    params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imgUrl);
                }
            }

            params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
            params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, mExtarFlag);

            if (isToQzone) {
                mTencent.shareToQzone(activity, params, shareResultListener);
            } else {
                mTencent.shareToQQ(activity, params, shareResultListener);
            }
        }
    }

    public void shareQQText(Activity activity, String title, String targetUrl, String summary, boolean isLocalImg, String imgUrl, boolean isToQzone,int shareType) {
        if (mTencent != null) {
            final Bundle params = new Bundle();

            /**
             * 下面三个参数是必须全部要传的，不能够为空
             */
            params.putString(QQShare.SHARE_TO_QQ_TITLE, title.toString());
            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetUrl != null ? targetUrl.toString() : "");// 这个参数必须是一个链接
            params.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary.toString());

            if (isLocalImg) {
                if (imgUrl != null && imgUrl.length() > 0) {
                    params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imgUrl);
                }
            } else {
                if (imgUrl != null && imgUrl.length() > 0 && imgUrl.toLowerCase().startsWith("http")) {
                    params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imgUrl);
                }
            }

            params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, shareType);
            params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, mExtarFlag);

            if (isToQzone) {
                mTencent.shareToQzone(activity, params, shareResultListener);
            } else {
                mTencent.shareToQQ(activity, params, shareResultListener);
            }
        }
    }

    public void shareQQImage() {

    }

    public void shareQQAudio() {

    }

    public void shareQQApp() {

    }

    /**
     * 这个需要在调用分享的页面的onActivityResult中设置回调，否则无法获取到来自QQ分享成功或取消的回调结果
     *
     * @param requestCode
     * @param resultCode
     * @param data
     * @param isHandleCallback
     */
    public void setQQActivityResult(int requestCode, int resultCode, Intent data, boolean isHandleCallback) {
        if (isHandleCallback) {
            if (mTencent != null) {
                mTencent.onActivityResultData(requestCode, resultCode, data, shareResultListener);
            } else {
                if (qqShareCallback != null) {
                    qqShareCallback.onQQShareFail(ReturnCode.TYPE_NO_INIT, null);
                }
            }
        }
    }

    //---------------------------------------------------------------------------------

    @Override
    public void onQQShareSuccess() {

    }

    @Override
    public void onQQShareCancel() {

    }

    @Override
    public void onQQShareFail() {

    }

    @Override
    public void onQQShareDenied() {

    }

    @Override
    public void onQQShareFail(int type, String message) {

    }


    //---------------------------------------------------------------------------------


    private static class QQShareUtilHolder {
        private static final QQShareUtil instances = new QQShareUtil();
    }
}
