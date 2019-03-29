package com.meiling.qq.common.callback.info;


import com.meiling.qq.common.bean.QQInfoBean;

/**
 * Created by Hz on 2018-11-21.
 */

public interface QQInfoCallback {
    // 先判断 type,然后根据type的值获取Bean内的数据
    void getQQInfo(int type, QQInfoBean qqInfoBean);
}
