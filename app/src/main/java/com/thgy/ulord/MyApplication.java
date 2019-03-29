package com.thgy.ulord;

import android.support.multidex.MultiDexApplication;

import com.meiling.qq.login.QQUtil;
import com.meiling.weibo.login.WeiboUtil;
import com.meiling.weixin.login.WeixinUtil;
import com.thgy.ulord.thirdpart.ThirdPartInfo;

/**
 * Created by Hz on 2018-12-10.
 */

public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        initThirdPart();
    }

    private void initThirdPart() {
        QQUtil.getInstances().initQQ(this, "101532446");//

        WeixinUtil.getInstances().initWeixin(this, ThirdPartInfo.WEIXIN.APP_ID);

        WeiboUtil.getInstances().initWeibo(this, ThirdPartInfo.WEIBO.APP_KEY, ThirdPartInfo.WEIBO.REDIRECTION, ThirdPartInfo.WEIBO.SCOPE);
    }

}
