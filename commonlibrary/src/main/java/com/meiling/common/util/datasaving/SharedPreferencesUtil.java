package com.meiling.common.util.datasaving;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;


import com.meiling.common.util.datacheck.StringCheckUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * 默认的保存使用私有形式进行
 * <p>建议文件名与Key名保存在一个文件中，便于分类与阅读</p>
 *
 * Created by huangzhou@ulord.com on 2018-10-30 17:21.
 */

public class SharedPreferencesUtil {
    /**
     * 将对象写入流，并转化成Base64
     *
     * @param context
     * @param spFile
     * @param spKey
     * @param object
     * @return
     */
    public static boolean saveObject(Context context, String spFile, String spKey, Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 初始化对象流
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            // 将对象写入流中，最后到baos流中
            oos.writeObject(object);// 对象需要序列化 实现Serializable接口
            // 将对象字节转为64位字符串
            String base64Str = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
            SharedPreferences shared = context.getApplicationContext()
                    .getSharedPreferences(spFile, Context.MODE_PRIVATE);
            shared.edit().putString(spKey, base64Str).apply();//
            //释放资源
            oos.close();
            baos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param context
     * @param spFile
     * @param spKey
     * @return 需要用户自己进行判空
     */
    public static Object readObject(Context context, String spFile, String spKey) {
        if (context == null || StringCheckUtil.isEmpty(spFile) || StringCheckUtil.isEmpty(spKey)) {
            return null;
        }
        try {
            // 取出指定的数据
            SharedPreferences shared = context.getApplicationContext()
                    .getSharedPreferences(spFile, Context.MODE_PRIVATE);
            String base64Str = shared.getString(spKey, null);
            if (StringCheckUtil.isEmpty(base64Str)) {//判断是否为有效的数据----可能由于file、key错误导致
                return null;
            }
            byte[] objData = Base64.decode(base64Str, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(objData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object obj = ois.readObject();
            // 释放资源
            bais.close();
            ois.close();
            objData = null;
            return obj;
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param context
     * @param spFile
     * @param spKey
     * @param saving
     * @return
     */
    public static boolean saveString(Context context, String spFile, String spKey, String saving) {
        SharedPreferences shared = context.getApplicationContext()
                .getSharedPreferences(spFile, Context.MODE_PRIVATE);
        shared.edit().putString(spKey, saving).apply();//
        return false;
    }

    /**
     *
     * @param context
     * @param spFile
     * @param spKey
     * @return
     */
    public static String readString(Context context, String spFile, String spKey) {
        SharedPreferences shared = context.getApplicationContext()
                .getSharedPreferences(spFile, Context.MODE_PRIVATE);
        return shared.getString(spKey, null);
    }

    /**
     *
     * @param context
     * @param spFile
     * @param spKey
     * @param saving
     * @return
     */
    public static boolean saveInt(Context context, String spFile, String spKey, int saving) {
        SharedPreferences shared = context.getApplicationContext()
                .getSharedPreferences(spFile, Context.MODE_PRIVATE);
        shared.edit().putInt(spKey, saving).apply();//
        return false;
    }

    /**
     *
     * @param context
     * @param spFile
     * @param spKey
     * @return
     */
    public static int readInt(Context context, String spFile, String spKey) {
        SharedPreferences shared = context.getApplicationContext()
                .getSharedPreferences(spFile, Context.MODE_PRIVATE);
        return shared.getInt(spKey, 0);
    }

    /**
     *
     * @param context
     * @param spFile
     * @param spKey
     * @param saving
     * @return
     */
    public static boolean saveLong(Context context, String spFile, String spKey, long saving) {
        SharedPreferences shared = context.getApplicationContext()
                .getSharedPreferences(spFile, Context.MODE_PRIVATE);
        shared.edit().putLong(spKey, saving).apply();//
        return false;
    }

    /**
     *
     * @param context
     * @param spFile
     * @param spKey
     * @return
     */
    public static long readLong(Context context, String spFile, String spKey) {
        SharedPreferences shared = context.getApplicationContext()
                .getSharedPreferences(spFile, Context.MODE_PRIVATE);
        return shared.getLong(spKey, 0);
    }

    /**
     *
     * @param context
     * @param spFile
     * @param spKey
     * @param saving
     * @return
     */
    public static boolean saveFloat(Context context, String spFile, String spKey, float saving) {
        SharedPreferences shared = context.getApplicationContext()
                .getSharedPreferences(spFile, Context.MODE_PRIVATE);
        shared.edit().putFloat(spKey, saving).apply();//
        return false;
    }

    /**
     *
     * @param context
     * @param spFile
     * @param spKey
     * @return
     */
    public static float readFloat(Context context, String spFile, String spKey) {
        SharedPreferences shared = context.getApplicationContext()
                .getSharedPreferences(spFile, Context.MODE_PRIVATE);
        return shared.getFloat(spKey, 0);
    }

    /**
     *
     * @param context
     * @param spFile
     * @param spKey
     * @param saving
     * @return
     */
    public static boolean saveBoolean(Context context, String spFile, String spKey, boolean saving) {
        SharedPreferences shared = context.getApplicationContext()
                .getSharedPreferences(spFile, Context.MODE_PRIVATE);
        shared.edit().putBoolean(spKey, saving).apply();//
        return false;
    }

    /**
     *
     * @param context
     * @param spFile
     * @param spKey
     * @return
     */
    public static boolean readBoolean(Context context, String spFile, String spKey) {
        SharedPreferences shared = context.getApplicationContext()
                .getSharedPreferences(spFile, Context.MODE_PRIVATE);
        return shared.getBoolean(spKey, false);
    }


}
