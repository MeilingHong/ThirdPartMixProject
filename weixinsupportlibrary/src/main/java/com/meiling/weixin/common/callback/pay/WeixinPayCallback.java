package com.meiling.weixin.common.callback.pay;


public interface WeixinPayCallback {
    void paySuccess();//支付成功
    void payFail(int errorCode,String errorMsg);
}
