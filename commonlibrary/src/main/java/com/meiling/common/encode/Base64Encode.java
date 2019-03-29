package com.meiling.common.encode;

import android.util.Base64;


import com.meiling.common.util.datacheck.StringCheckUtil;

import java.io.UnsupportedEncodingException;

/**
 * Created by huangzhou@ulord.com on 2018-10-26 10:24.
 */

public class Base64Encode {
    private static final String UTF_8 = "UTF-8";

    public static String androidBase64EncodeToString(String inputString) {
        if (!StringCheckUtil.isEmpty(inputString)) {
            return Base64.encodeToString(inputString.getBytes(), Base64.DEFAULT);
        }
        return null;
    }

    public static String normalBase64EncodeToString(String inputString) throws UnsupportedEncodingException {
        if(!StringCheckUtil.isEmpty(inputString)){
            org.apache.commons.codec.binary.Base64 base = new org.apache.commons.codec.binary.Base64();
            return new String(base.encode(inputString.getBytes(UTF_8)));
        }
        return null;
    }
}
