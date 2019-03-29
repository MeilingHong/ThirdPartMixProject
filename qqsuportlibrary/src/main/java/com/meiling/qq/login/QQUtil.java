package com.meiling.qq.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.meiling.qq.common.bean.QQInfoBean;
import com.meiling.qq.common.bean.ReturnCode;
import com.meiling.qq.common.callback.info.QQInfoCallback;
import com.tencent.connect.UnionInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/**
 * Created by Hz on 2018-12-10.
 */

public class QQUtil {

    private Tencent mTencent;
    private QQInfoCallback qqInfoCallback;
    private QQInfoBean qqInfoBean;

    private QQUtil() {

    }

    public static QQUtil getInstances() {
        return QQLoginUtilHolder.instances;
    }

    //---------------------------------------------------------------------------------
    private IUiListener qqLoginListener = new IUiListener() {
        @Override
        public void onComplete(Object response) {
            try {
                if (response != null) {
                    JSONObject jsonObject = (JSONObject) response;
                    //解析设置信息
                            /*
response 结果：
{
  "ret": 0,
  "openid": "1111111111111111111",
  "access_token": "2222222222222222",
  "pay_token": "333333333333333333333333",
  "expires_in": 7776000,
  "pf": "desktop_m_qq-10000144-android-2002-",
  "pfkey": "44444444444444444444444",
  "msg": "",
  "login_cost": 68,
  "query_authority_cost": 153,
  "authority_cost": 0,
  "expires_time": 1550559466058
}
                             */
                    String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
                    String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
                    String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
                    if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                            && !TextUtils.isEmpty(openId)) {
                        mTencent.setAccessToken(token, expires);
                        mTencent.setOpenId(openId);

                        //更新InfoBean信息
                        qqInfoBean.setAccess_token(token);
                        qqInfoBean.setExpires_in(expires);
                        qqInfoBean.setOpenId(openId);
                        if (qqInfoCallback != null) {
                            qqInfoCallback.getQQInfo(ReturnCode.TYPE_LOGIN_INFO, qqInfoBean);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (qqInfoCallback != null) {
                    qqInfoCallback.getQQInfo(ReturnCode.TYPE_ERROR_JSON_FORMAT, null);
                }
            }
        }

        @Override
        public void onError(UiError uiError) {
            if (qqInfoCallback != null) {
                qqInfoCallback.getQQInfo(ReturnCode.TYPE_ERROR, null);
            }
        }

        @Override
        public void onCancel() {
            if (qqInfoCallback != null) {
                qqInfoCallback.getQQInfo(ReturnCode.TYPE_CANCEL, null);
            }
        }
    };

    private IUiListener qqUnionListener = new IUiListener() {
        @Override
        public void onComplete(Object response) {
            if (response != null) {
                JSONObject jsonObject = (JSONObject) response;
                try {
                    String unionid = jsonObject.getString("unionid");
                    qqInfoBean.setUnion_id(unionid);
                    if (qqInfoCallback != null) {
                        qqInfoCallback.getQQInfo(ReturnCode.TYPE_UNION_ID, qqInfoBean);
                    }
                } catch (Exception e) {
                    if (qqInfoCallback != null) {
                        qqInfoCallback.getQQInfo(ReturnCode.TYPE_NO_UNION_ID, null);
                    }
                }
            } else {
                if (qqInfoCallback != null) {
                    qqInfoCallback.getQQInfo(ReturnCode.TYPE_NO_UNION_ID, null);
                }
            }
        }

        @Override
        public void onError(UiError uiError) {
            if (qqInfoCallback != null) {
                qqInfoCallback.getQQInfo(ReturnCode.TYPE_ERROR, null);
            }
        }

        @Override
        public void onCancel() {
            if (qqInfoCallback != null) {
                qqInfoCallback.getQQInfo(ReturnCode.TYPE_CANCEL, null);
            }
        }
    };

    /*
     * 处理通用的回调数据
     */
    private IUiListener commonCallback = new IUiListener() {
        @Override
        public void onComplete(Object o) {
            if (qqLoginListener != null) {
                qqLoginListener.onComplete(o);
            }

            if (qqUnionListener != null) {
                qqUnionListener.onComplete(o);
            }
        }

        @Override
        public void onError(UiError uiError) {
            if (qqLoginListener != null) {
                qqLoginListener.onError(uiError);
            }

            if (qqUnionListener != null) {
                qqUnionListener.onError(uiError);
            }
        }

        @Override
        public void onCancel() {
            if (qqLoginListener != null) {
                qqLoginListener.onCancel();
            }

            if (qqUnionListener != null) {
                qqUnionListener.onCancel();
            }
        }
    };

    //-------------------------------------------
    /*
     * TODO 这个方法最好的Application中调用
     */
    public void initQQ(Context context, String APP_ID) { //避免过于耦合，将APPID作为参数传入，这个model可以作为依赖导入
        mTencent = Tencent.createInstance(APP_ID, context.getApplicationContext());
        qqInfoBean = new QQInfoBean();
    }

    /*
     * TODO 在调用相关功能的Activity或Fragment中调用，设置回调
     */
    public void setQqInfoCallback(QQInfoCallback qqInfoCallback) {
        this.qqInfoCallback = qqInfoCallback;
    }

    /**
     * TODO 这个需要在onActivityResult中进行调用
     *
     * @param requestCode
     * @param resultCode
     * @param data
     * @param isHandleCallback 标识是否处理这个回调结果的回调【当同时在一个页面中】
     */
    public void setQQActivityResult(int requestCode, int resultCode, Intent data,boolean isHandleCallback) {
        if (isHandleCallback){
            if (mTencent != null) {
                mTencent.onActivityResultData(requestCode, resultCode, data, commonCallback);
            } else {
                if (qqInfoCallback != null) {
                    qqInfoCallback.getQQInfo(ReturnCode.TYPE_NO_INIT, null);
                }
            }
        }
    }

    /*
     * 检查QQ是否登录
     */
    private boolean isLogin() {
        return (mTencent != null && mTencent.isSessionValid());
    }

    /*
     * 检查QQ是否未登录
     */
    private boolean isNotLogin() {
        return (mTencent != null && !mTencent.isSessionValid());
    }

    /*
     * 检查QQ是否安装了
     */
    public boolean isQQInstalled(Context context) {
        return mTencent != null && mTencent.isQQInstalled(context);
    }

    /*
     * 检查是否支持单点登录
     */
    public boolean isQQSupportSSOLogin(Activity context) {
        return mTencent != null && mTencent.isSupportSSOLogin(context);
    }

    /*
     * 获取QQ的UnionID
     */
    public void getUnionID(Context context) {
        if (mTencent != null && mTencent.isQQInstalled(context)) {
            if (isLogin()) {
                UnionInfo unionInfo = new UnionInfo(context, mTencent.getQQToken());
                unionInfo.getUnionId(qqUnionListener);
            } else {
                if (qqInfoCallback != null) {
                    qqInfoCallback.getQQInfo(ReturnCode.TYPE_NOT_LOGIN, null);
                }
            }
        } else {
            if (mTencent != null) {
                if (qqInfoCallback != null) {
                    qqInfoCallback.getQQInfo(ReturnCode.TYPE_NO_INSTALL, null);
                }
            } else {
                if (qqInfoCallback != null) {
                    qqInfoCallback.getQQInfo(ReturnCode.TYPE_NO_INIT, null);
                }
            }
        }
    }

    /*
     * 登录------Activity中调用
     */
    public void doQQLogin(Activity context) {
        if (mTencent != null && mTencent.isQQInstalled(context)) {
            if (isNotLogin()) {
                mTencent.login(context, "all", qqLoginListener);//非普通登录
            } else {
                if (mTencent != null && mTencent.isSessionValid()) {
                    mTencent.logout(context);
                }
                if (mTencent != null) {
                    mTencent.login(context, "all", qqLoginListener);//非普通登录
                }
            }
        } else {
            if (mTencent != null) {
                if (qqInfoCallback != null) {
                    qqInfoCallback.getQQInfo(ReturnCode.TYPE_NO_INSTALL, null);
                }
            } else {
                if (qqInfoCallback != null) {
                    qqInfoCallback.getQQInfo(ReturnCode.TYPE_NO_INIT, null);
                }
            }
        }
    }

    /*
     * 登录------Fragment中调用
     */
    public void doQQLogin(Fragment context) {
        if (mTencent != null && mTencent.isQQInstalled(context.getContext())) {
            if (isNotLogin()) {
                mTencent.login(context, "all", qqLoginListener);//非普通登录
            } else {
                if (mTencent != null && mTencent.isSessionValid()) {
                    mTencent.logout(context.getContext());
                }
                if (mTencent != null) {
                    mTencent.login(context, "all", qqLoginListener);//非普通登录
                }
            }
        } else {
            if (mTencent != null) {
                if (qqInfoCallback != null) {
                    qqInfoCallback.getQQInfo(ReturnCode.TYPE_NO_INSTALL, null);
                }
            } else {
                if (qqInfoCallback != null) {
                    qqInfoCallback.getQQInfo(ReturnCode.TYPE_NO_INIT, null);
                }
            }
        }
    }

    public void doLogout(Context context) {
        if (mTencent != null) {
            mTencent.logout(context);
            qqInfoBean.setAccess_token(null);//
            qqInfoBean.setExpires_in(null);//
            qqInfoBean.setOpenId(null);//
            qqInfoBean.setUnion_id(null);//
        } else {
            if (qqInfoCallback != null) {
                qqInfoCallback.getQQInfo(ReturnCode.TYPE_NO_INIT, null);
            }
        }
    }

    //---------------------------------------------------------------------------------
    private static class QQLoginUtilHolder {
        private static final QQUtil instances = new QQUtil();
    }
}
