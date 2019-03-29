package com.meiling.common.decode;


import android.util.Base64;


import com.meiling.common.util.datacheck.StringCheckUtil;

import java.io.UnsupportedEncodingException;


/**
 * Created by huangzhou@ulord.com on 2018-10-26 10:24.
 */

public class Base64Decode {
    private static final String UTF_8 = "UTF-8";

    public static String androidBase64DecodeToString(String inputString) {
        if (!StringCheckUtil.isEmpty(inputString)) {
            return new String(Base64.decode(inputString.getBytes(), Base64.DEFAULT));
        }
        return null;
    }

    public static String normalBase64DecodeToString(String inputString) throws UnsupportedEncodingException {
        if(!StringCheckUtil.isEmpty(inputString)){
            org.apache.commons.codec.binary.Base64 base = new org.apache.commons.codec.binary.Base64();
            return new String(base.decode(inputString.getBytes(UTF_8)));
        }
        return null;
    }
}
