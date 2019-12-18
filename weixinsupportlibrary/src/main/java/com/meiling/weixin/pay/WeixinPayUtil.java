package com.meiling.weixin.pay;

import android.content.Context;

import com.meiling.weixin.common.callback.pay.WeixinPayCallback;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONObject;

/**
 * 微信分享文档
 * <p>
 * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419317340&token=&lang=zh_CN
 * <p>
 * <p>
 */

public class WeixinPayUtil implements IWXAPIEventHandler {

    private WeixinPayCallback weixinPayCallback;
    private IWXAPI api;

    private WeixinPayUtil() {

    }

    public static WeixinPayUtil getInstances() {
        return WeixinPayUtil.WeixinPayUtilHolder.instances;
    }

    //---------------------------------------------------------------------------------

    /**
     * 在使用页调用
     *
     * @param context
     * @param APP_ID
     */
    public void initPay(Context context, String APP_ID) {
        api = WXAPIFactory.createWXAPI(context, APP_ID, false);
        api.registerApp(APP_ID);
    }

    public void sendPay(JSONObject json) {
        //  示例性后台返回（无法调起支付）          JSONObject jsonObject = new JSONObject("{\"nonce_str\"=\"dxYH4YjdEu56lx8k\", \"appid\"=\"wx5feb2938ded2199c\", \"sign\"=\"5697627642ACA6461C6E75C437E9926C5586D47C606B34F28B4662A1695B11F3\", \"trade_type\"=\"APP\", \"return_msg\"=\"OK\", \"result_code\"=\"SUCCESS\", \"mch_id\"=\"1548657951\", \"return_code\"=\"SUCCESS\", \"prepay_id\"=\"wx181403516547689f4fecce731817170800\"}");
        try {
            if (null != json && !json.has("retcode")) {
                PayReq req = new PayReq();
                //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                req.appId = json.getString("appid");
                req.partnerId = json.getString("mch_id");
                req.prepayId = json.getString("prepay_id");
                req.nonceStr = json.getString("nonce_str");
                req.timeStamp = json.getString("timestamp");
                req.sign = json.getString("sign");
                //以上部分需要从接口获取
                req.packageValue = "Sign=WXPay";
                req.extData = "APP"; // optional
                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                api.sendReq(req);
            } else {
                if (weixinPayCallback != null) {
                    weixinPayCallback.payFail(BaseResp.ErrCode.ERR_COMM, json.getString("return_msg"));
                }
            }
        } catch (Exception e) {
            if (weixinPayCallback != null) {
                weixinPayCallback.payFail(BaseResp.ErrCode.ERR_COMM, e.getMessage());
            }
        }
    }


    /**
     * 在使用页调用，设置回调，否则即便分享成功了也拿不到微信给的回调
     * <p>
     * 1、在WXEntryActivity中设置回调，
     * 2、直接使用在在WXEntryActivity中调用
     * WeixinPayUtil.getInstances().onReq()
     * WeixinPayUtil.getInstances().onResp()
     */
    public void setWeixinPayCallback(WeixinPayCallback weixinPayCallback) {
        this.weixinPayCallback = weixinPayCallback;
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (baseResp.errCode) {
                case BaseResp.ErrCode.ERR_OK: {
                    if (weixinPayCallback != null) {
                        weixinPayCallback.paySuccess();
                    }
                    break;
                }
                default: {
                    if (weixinPayCallback != null) {
                        weixinPayCallback.payFail(baseResp.errCode, baseResp.errStr);
                    }
                    break;
                }
            }
        }
    }

    //---------------------------------------------------------------------------------
    private static class WeixinPayUtilHolder {
        private static final WeixinPayUtil instances = new WeixinPayUtil();
    }
}
