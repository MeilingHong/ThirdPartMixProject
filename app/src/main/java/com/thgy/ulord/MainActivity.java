package com.thgy.ulord;

/**
 * Created by Hz on 2018-12-07.
 */

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.meiling.qq.common.bean.QQInfoBean;
import com.meiling.qq.common.callback.info.QQInfoCallback;
import com.meiling.qq.common.callback.share.QQShareCallback;
import com.meiling.qq.login.QQUtil;
import com.meiling.qq.share.QQShareUtil;
import com.meiling.weibo.common.bean.WeiboInfoBean;
import com.meiling.weibo.common.callback.info.WeiboInfoCallback;
import com.meiling.weibo.common.callback.share.WeiboShareCallback;
import com.meiling.weibo.login.WeiboUtil;
import com.meiling.weibo.share.WeiboShareUtil;
import com.meiling.weixin.common.bean.WeixinInfoBean;
import com.meiling.weixin.common.callback.info.WeixinInfoCallback;
import com.meiling.weixin.common.callback.share.WeixinShareCallback;
import com.meiling.weixin.login.WeixinUtil;
import com.meiling.weixin.share.WeixinShareUtil;
import com.tencent.connect.share.QQShare;
import com.thgy.ulord.thirdpart.ThirdPartInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements
        WeixinInfoCallback,
        WeiboInfoCallback,
        QQInfoCallback,
        WeiboShareCallback,
        WeixinShareCallback,
        QQShareCallback {
    private static final String TAG = "_Android";

    @BindView(R.id.weixin)
    TextView weixin;
    @BindView(R.id.qq)
    TextView qq;
    @BindView(R.id.weibo)
    TextView weibo;
    @BindView(R.id.alipay)
    TextView alipay;
    //------------------------------------
    @BindView(R.id.weixinShareText)
    TextView weixinShareText;
    @BindView(R.id.weixinShareImg)
    TextView weixinShareImg;
    @BindView(R.id.weixinShareUrl)
    TextView weixinShareUrl;
    @BindView(R.id.weixinShareMiniApp)
    TextView weixinShareMiniApp;
    @BindView(R.id.weixinShareMiniAppImg)
    TextView weixinShareMiniAppImg;
    //------------------------------------
    @BindView(R.id.qqShareText)
    TextView qqShareText;
    @BindView(R.id.qqShareImg)
    TextView qqShareImg;
    @BindView(R.id.qqShareUrl)
    TextView qqShareUrl;
    //------------------------------------
    @BindView(R.id.weiboShareText)
    TextView weiboShareText;
    @BindView(R.id.weiboShareImg)
    TextView weiboShareImg;
    @BindView(R.id.weiboShareUrl)
    TextView weiboShareUrl;
    //------------------------------------
    @BindView(R.id.weixinPay)
    TextView weixinPay;
    //------------------------------------
    @BindView(R.id.alipayPay)
    TextView alipayPay;
    //------------------------------------
    Unbinder unbinder;


    private ThirdPartInfo.ThidPartType thidPartType = ThirdPartInfo.ThidPartType.WEIXIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        initThird();
        initShare();
    }

    private void initThird() {
        QQUtil.getInstances().setQqInfoCallback(this);

//        WXEntryActivity.setShareEventHandler(ThirdPartWeixinUtil.getInstances());//当WXEntryActivity中直接设置了回调时，不用再使用这个设置
        WeixinUtil.getInstances().setWeixinInfoCallback(this);

        WeiboUtil.getInstances().initWeiboHandler(this);
        WeiboUtil.getInstances().setWeiboInfoCallback(this);
    }

    private void initShare() {
        WeiboShareUtil.getInstances().initShare(this, this);

        WeixinShareUtil.getInstances().initShare(this, ThirdPartInfo.WEIXIN.APP_ID);
        WeixinShareUtil.getInstances().setWeixinShareCallback(this);

        QQShareUtil.getInstances().initShare(this, ThirdPartInfo.QQ.APP_ID);
        QQShareUtil.getInstances().setQqShareCallback(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (thidPartType == ThirdPartInfo.ThidPartType.WEIBO_SHARE) {
            WeiboShareUtil.getInstances().setResultIntent(intent);// 微博Demo误导了不是走onNewIntent,而是走onActivityResult
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //TODO 如果为了避免数据造成冲突最好进行区分下
        if (thidPartType == ThirdPartInfo.ThidPartType.QQ) {
            QQUtil.getInstances().setQQActivityResult(requestCode, resultCode, data, true);
        } else if (thidPartType == ThirdPartInfo.ThidPartType.WEIBO) {
            WeiboUtil.getInstances().setWeiboActivityResult(requestCode, resultCode, data);
        } else if (thidPartType == ThirdPartInfo.ThidPartType.WEIBO_SHARE) {
            WeiboShareUtil.getInstances().setResultIntent(data);//TODO 目前发现微博分享返回实际走的这里，而不是demo中的onNewIntent里面
        } else if (thidPartType == ThirdPartInfo.ThidPartType.QQ_SHARE) {
            QQShareUtil.getInstances().setQQActivityResult(requestCode, resultCode, data, true);
        }
    }

    @OnClick({R.id.weixin, R.id.qq, R.id.weibo, R.id.alipay,
            R.id.weixinShareText, R.id.weixinShareImg, R.id.weixinShareUrl,
            R.id.weixinShareMiniApp, R.id.weixinShareMiniAppImg,
            R.id.qqShareText, R.id.qqShareImg, R.id.qqShareUrl,
            R.id.weiboShareText, R.id.weiboShareImg, R.id.weiboShareUrl,
            R.id.weixinPay, R.id.alipayPay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.weixin: {
                thidPartType = ThirdPartInfo.ThidPartType.WEIXIN;
                WeixinUtil.getInstances().doWeixinLogin();
                Toast.makeText(this, "微信登陆", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.qq: {
                thidPartType = ThirdPartInfo.ThidPartType.QQ;
                QQUtil.getInstances().doQQLogin(this);
                Toast.makeText(this, "QQ登陆", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.weibo: {
                thidPartType = ThirdPartInfo.ThidPartType.WEIBO;
                WeiboUtil.getInstances().doWeiboLogin();
                Toast.makeText(this, "微博登陆", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.alipay: {
                thidPartType = ThirdPartInfo.ThidPartType.ALIPAY;
                Toast.makeText(this, "淘宝登陆", Toast.LENGTH_SHORT).show();
                break;
            }
            //-------------------------------------------------------------
            case R.id.weixinShareText: {
                WeixinShareUtil.getInstances().shareText("文字分享标题", "文字分享描述", true);
//                WeixinShareUtil.getInstances().shareText("文字分享标题", "文字分享描述", false);
                break;
            }
            case R.id.weixinShareImg: {
                WeixinShareUtil.getInstances().shareImage(this,
                        BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "文字分享标题", "文字分享描述", true);
//                WeixinShareUtil.getInstances().shareImage(this,
//                        BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher),"文字分享标题", "文字分享描述", false);
                break;
            }
            case R.id.weixinShareUrl: {
                WeixinShareUtil.getInstances().shareUrl(this, "https://ushare.org/article/07310020181220093000022349",
                        BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "文字分享标题", "文字分享描述", true);
//                WeixinShareUtil.getInstances().shareUrl(this, "https://ushare.org/article/07310020181220093000022349",
//                        BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), "文字分享标题", "文字分享描述", false);
                break;
            }
            case R.id.weixinShareMiniApp: {//目前小程序仅支持 使用会话方式分享，无法直接分享朋友圈
                WeixinShareUtil.getInstances().shareMiniApp(this, R.mipmap.ic_launcher, "https://ushare.org",
                        ThirdPartInfo.WEIXIN.MINI_APP_ORIGINAL_ID, "page/index/index?inviteCode=8e8c689ce73836328216c9e399fec4b8", "文字分享标题", "文字分享描述");
                break;
            }
            case R.id.weixinShareMiniAppImg: {
                WeixinShareUtil.getInstances().shareMiniAppImg(this, "https://ushare.xuanwuyun.com/qrcode/index/index?inviteCode=8e8c689ce73836328216c9e399fec4b8",
                        "文字分享标题", "文字分享描述", true);
//                WeixinShareUtil.getInstances().shareMiniAppImg(this,"https://ushare.xuanwuyun.com/qrcode/index/index?inviteCode=8e8c689ce73836328216c9e399fec4b8",
//                        "文字分享标题", "文字分享描述",false);
                break;
            }
            //-------------------------------------------------------------
            case R.id.qqShareText: {
                thidPartType = ThirdPartInfo.ThidPartType.QQ_SHARE;
                QQShareUtil.getInstances().shareQQText(this, "标题", "https://ushare.org", "文字分享的描述",
                        false, null, false);
                break;
            }
            case R.id.qqShareImg: {
                thidPartType = ThirdPartInfo.ThidPartType.QQ_SHARE;
                QQShareUtil.getInstances().shareQQText(this, "标题", "http://www.iudfs.com:666/images/15724227076.png", "文字分享的描述",
                        false, "http://www.iudfs.com:666/images/15724227076.png", false);
                break;
            }
            case R.id.qqShareUrl: {
                thidPartType = ThirdPartInfo.ThidPartType.QQ_SHARE;
                QQShareUtil.getInstances().shareQQText(this, "标题", "https://ushare.org/article/07310020191028154500042957", "文字分享的描述",
                        false, null, false);
                break;
            }
            //-------------------------------------------------------------
            case R.id.weiboShareText: {
                thidPartType = ThirdPartInfo.ThidPartType.WEIBO_SHARE;
                WeiboShareUtil.getInstances().shareText("分享文本");
                break;
            }
            case R.id.weiboShareImg: {
                thidPartType = ThirdPartInfo.ThidPartType.WEIBO_SHARE;
                WeiboShareUtil.getInstances().shareImage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                break;
            }
            case R.id.weiboShareUrl: {
                thidPartType = ThirdPartInfo.ThidPartType.WEIBO_SHARE;
                WeiboShareUtil.getInstances().shareWeb("标题", "描述", "https://ushare.org", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                break;
            }
            //-------------------------------------------------------------
            case R.id.weixinPay: {
                break;
            }
            case R.id.alipayPay: {
                break;
            }
        }
    }

    @Override
    public void getWeixinInfo(int type, WeixinInfoBean weixinInfoBean) {
        Log.e(TAG, "type:" + type + "\n" + weixinInfoBean);
    }

    @Override
    public void getWeiboInfo(int type, WeiboInfoBean weiboInfoBean) {
        Log.e(TAG, "type:" + type + "\n" + weiboInfoBean);
    }

    @Override
    public void getQQInfo(int type, QQInfoBean qqInfoBean) {
        Log.e(TAG, "type:" + type + "\n" + qqInfoBean);
    }

    //-------------------------------------------------------------------
    @Override
    public void onWeiboShareSuccess() {
        Log.e(TAG, "onWeiboShareSuccess:");
        Toast.makeText(this, "微博分享成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWeiboShareCancel() {
        Log.e(TAG, "onWeiboShareCancel:");
        Toast.makeText(this, "微博分享取消", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWeiboShareFail() {
        Log.e(TAG, "onWeiboShareFail:");
        Toast.makeText(this, "微博分享失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWeiboShareDenied() {
        Toast.makeText(this, "微博分享被拒绝", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWeiboShareFail(int type, String message) {
        Toast.makeText(this, "微博分享：" + message, Toast.LENGTH_SHORT).show();
    }

    //-------------------------------------------------------------------
    @Override
    public void onWeixinShareSuccess() {
        Toast.makeText(this, "微信分享成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWeixinShareCancel() {
        Toast.makeText(this, "微信分享取消", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWeixinShareFail() {
        Toast.makeText(this, "微信分享失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWeixinShareDenied() {
        Toast.makeText(this, "微信分享被拒绝", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWeixinShareFail(int type, String message) {
        Toast.makeText(this, "微信分享：" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQQShareSuccess() {
        Toast.makeText(this, "QQ分享成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQQShareCancel() {
        Toast.makeText(this, "QQ取消分享", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQQShareFail() {
        Toast.makeText(this, "QQ分享失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQQShareDenied() {

    }

    @Override
    public void onQQShareFail(int type, String message) {

    }

    //-------------------------------------------------------------------
}
