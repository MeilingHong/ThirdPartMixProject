package com.meiling.common.util.datahandle;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Hz on 2018/12/22.
 */

public class BitmapToBytesUtil {
    public static final byte[] setThumbImage(Bitmap bitmap) {
        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, os);
            return os.toByteArray();
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException var11) {
                var11.printStackTrace();
            }
        }
        return null;
    }
}
