package com.thgy.ulord.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.meiling.weixin.login.WeixinUtil;
import com.meiling.weixin.share.WeixinShareUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.thgy.ulord.thirdpart.ThirdPartInfo;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    private static IWXAPIEventHandler eventHandler;

    public static void setShareEventHandler(IWXAPIEventHandler eventHandler) {
        WXEntryActivity.eventHandler = eventHandler;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initWXAPIComponent();
    }

    private void initWXAPIComponent() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, ThirdPartInfo.WEIXIN.APP_ID, false);
        api.registerApp(ThirdPartInfo.WEIXIN.APP_ID);

        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            api.handleIntent(getIntent(), this);
            finish();//避免停在这里使得界面无法正常返回
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        if (eventHandler != null) {
            eventHandler.onReq(baseReq);
        }
        WeixinUtil.getInstances().onReq(baseReq);
        WeixinShareUtil.getInstances().onReq(baseReq);
    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (eventHandler != null) {
            eventHandler.onResp(baseResp);
        }
        WeixinUtil.getInstances().onResp(baseResp);
        WeixinShareUtil.getInstances().onResp(baseResp);
    }
}
