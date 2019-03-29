package com.meiling.weixin.common.callback.info;


import com.meiling.weixin.common.bean.WeixinInfoBean;

/**
 * Created by Hz on 2018-11-21.
 */

public interface WeixinInfoCallback {
    void getWeixinInfo(int type, WeixinInfoBean weixinInfoBean);
}
