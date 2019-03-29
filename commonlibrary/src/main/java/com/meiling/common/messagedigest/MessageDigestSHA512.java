package com.meiling.common.messagedigest;

import java.security.MessageDigest;

/**
 * Created by huangzhou@ulord.com on 2018-10-25 11:37.
 */

public class MessageDigestSHA512 {

    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(str.getBytes());
            return MessageDigestUtil.getFormattedOfLowerCase(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
