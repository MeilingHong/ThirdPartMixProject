package com.meiling.weixin.common.callback.share;

/**
 * Created by Hz on 2018-12-11.
 */

public interface WeixinShareCallback {
    void onWeixinShareSuccess();
    void onWeixinShareCancel();
    void onWeixinShareFail();
    void onWeixinShareDenied();
    void onWeixinShareFail(int type,String message);
}
