package com.meiling.weibo.common.callback.info;


import com.meiling.weibo.common.bean.WeiboInfoBean;

/**
 * Created by Hz on 2018-11-21.
 */

public interface WeiboInfoCallback {
    void getWeiboInfo(int type, WeiboInfoBean weiboInfoBean);
}
