package com.meiling.weibo.common.callback.share;

/**
 * Created by Hz on 2018-12-11.
 */

public interface WeiboShareCallback {
    void onWeiboShareSuccess();
    void onWeiboShareCancel();
    void onWeiboShareFail();
    void onWeiboShareDenied();
    void onWeiboShareFail(int type,String message);
}
