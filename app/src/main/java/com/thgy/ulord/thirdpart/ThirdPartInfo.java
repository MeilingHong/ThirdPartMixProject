package com.thgy.ulord.thirdpart;

/**
 * Created by Hz on 2018-12-10.
 */

public class ThirdPartInfo {

    public enum ThidPartType {
        WEIBO, WEIXIN, QQ, ALIPAY,
        WEIBO_SHARE, WEIXIN_SHARE, QQ_SHARE;
    }

    public interface RETUREN_CODE {
        int TYPE_UNSUPPORT = -8;
        int TYPE_AUTH_DENIED = -7;
        int TYPE_NO_INIT = -6; //未初始化
        int TYPE_UNKNOWN_ERROR = -5;
        int TYPE_NO_INSTALL = -4;
        int TYPE_ERROR_JSON_FORMAT = -3;
        int TYPE_CANCEL = -2;
        int TYPE_ERROR = -1;
        int TYPE_NOT_LOGIN = 0;
        int TYPE_LOGIN_INFO = 1;
        int TYPE_INVALID_INFO = 2;
        int TYPE_UNION_ID = 3;
        int TYPE_NO_UNION_ID = 4;
    }

    public interface WEIXIN {
        String APP_ID = "wxedca2d01d3a86674";
        String APP_SECRET = "20c97ff80e85bb2f70ebaef7c6fa9d4a";

        String MINI_APP_ORIGINAL_ID = "gh_35d9f520a8f5";
    }

    public interface WEIBO {
        String APP_KEY = "2408720574";
        String APP_SECRET = "5cb6bd2451aebc45456cd8011ef78a15";
        String SCOPE =
                "email,direct_messages_read,direct_messages_write,"
                        + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                        + "follow_app_official_microblog," + "invitation_write";
        String REDIRECTION = "https://ushare.org";
    }

    public interface QQ {
        String APP_ID = "101532446";
        String APP_KEY = "ff92f1df076d5bf14119e0ca77c50711";
    }

    public interface ALIPAY {

    }
}
