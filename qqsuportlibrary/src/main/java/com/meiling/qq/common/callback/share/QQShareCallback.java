package com.meiling.qq.common.callback.share;

/**
 * Created by Hz on 2018-12-11.
 */

public interface QQShareCallback {
    void onQQShareSuccess();
    void onQQShareCancel();
    void onQQShareFail();
    void onQQShareDenied();
    void onQQShareFail(int type,String message);
}
