package com.meiling.common.util.sysinfo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import com.meiling.common.util.datacheck.StringCheckUtil;

import java.io.IOException;

/**
 * Created by huangzhou@ulord.com on 2018-11-07 11:46.
 */

public class NetStatusUtil {

    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable() && mWiFiNetworkInfo.isConnected();
            }
        }
        return false;
    }

    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable() && mMobileNetworkInfo.isConnected();
            }
        }
        return false;
    }

    /**
     * PS 有可连接的网络时，返回比较快；但网络可以连接，但无法上网时，会造成阻塞可能存在ANR异常
     * <b>该过程在网络无法正常上网时异常耗时，但若使用异步方法的话，</b> 考虑到用户的体验，这个方法最好不要使用（虽然在网络通畅情况下能够很快返回，但在网络连接正常单网络本身存在问题的情况下，会严重影响用户体验）
     * @param string
     * @return
     */
    public static boolean isNetworkOnline(String string) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("ping -c 1 -w 1 " + (StringCheckUtil.isEmpty(string) ? "www.baidu.com" : string));
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
