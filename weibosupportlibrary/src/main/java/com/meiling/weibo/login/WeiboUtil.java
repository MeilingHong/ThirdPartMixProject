package com.meiling.weibo.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.meiling.weibo.common.bean.ReturnCode;
import com.meiling.weibo.common.bean.WeiboInfoBean;
import com.meiling.weibo.common.callback.info.WeiboInfoCallback;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

/**
 * Created by Hz on 2018-12-10.
 */

public class WeiboUtil implements WbAuthListener {

    private WeiboInfoCallback weiboInfoCallback;
    private SsoHandler mSsoHandler;
    private Context weiboContext;

    private WeiboUtil() {

    }

    public static WeiboUtil getInstances() {
        return WeiboUtil.WeiboUtilHolder.instances;
    }

    //---------------------------------------------------------------------------------
    //微博
    public void initWeibo(Context context,String APP_KEY,String redirectionUrl,String scope) {
        WbSdk.install(context, new AuthInfo(context, APP_KEY, redirectionUrl, scope));
    }

    public void initWeiboHandler(Activity context) {
        mSsoHandler = new SsoHandler(context);
        weiboContext = context;
    }

    public void initWeiboHandler(Fragment context) {
        mSsoHandler = new SsoHandler(context.getActivity());
        weiboContext = context.getContext();
    }

    public void setWeiboInfoCallback(WeiboInfoCallback weiboInfoCallback) {
        this.weiboInfoCallback = weiboInfoCallback;
    }

    public void doWeiboLogin() {
        mSsoHandler.authorize(this);
    }

    public void setWeiboActivityResult(int requestCode, int resultCode, Intent data) {
        mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
    }

    @Override
    public void onSuccess(Oauth2AccessToken mAccessToken) {
        if (mAccessToken != null && mAccessToken.isSessionValid()) {
            // 显示 Token
            if (weiboInfoCallback != null) {
                WeiboInfoBean weiboInfoBean = new WeiboInfoBean();
                weiboInfoBean.setAccess_token(mAccessToken.getToken());
                weiboInfoBean.setUid(mAccessToken.getUid());
                weiboInfoBean.setExpires_in(mAccessToken.getExpiresTime() + "");
                weiboInfoBean.setRefresh_token(mAccessToken.getRefreshToken());
                AccessTokenKeeper.writeAccessToken(weiboContext, mAccessToken);
                weiboInfoCallback.getWeiboInfo(ReturnCode.TYPE_LOGIN_INFO, weiboInfoBean);
            }
        } else {
            if (weiboInfoCallback != null) {
                weiboInfoCallback.getWeiboInfo(ReturnCode.TYPE_INVALID_INFO, null);
            }
        }
    }

    @Override
    public void cancel() {
        if (weiboInfoCallback != null) {
            weiboInfoCallback.getWeiboInfo(ReturnCode.TYPE_CANCEL, null);
        }
    }

    @Override
    public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
        if (weiboInfoCallback != null) {
            WeiboInfoBean weiboInfoBean = new WeiboInfoBean();
            weiboInfoBean.setErrorCode(wbConnectErrorMessage.getErrorCode());
            weiboInfoBean.setErrorMsg(wbConnectErrorMessage.getErrorMessage());
            weiboInfoCallback.getWeiboInfo(ReturnCode.TYPE_ERROR, weiboInfoBean);
        }
    }

    //---------------------------------------------------------------------------------
    //分享文字

    //分享单个图片

    //分享链接

    //---------------------------------------------------------------------------------

    private static class WeiboUtilHolder {
        private static final WeiboUtil instances = new WeiboUtil();
    }
}
