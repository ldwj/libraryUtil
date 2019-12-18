package com.ldwj.library.util.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.ldwj.libraryapplication.BaseApplication;

import java.util.Map;


/**
 *SharedPreferences 保存基础信息 (data/data)
 */
public class SharedPreferencesUtils {

    /**
     * 获取应用程序默认的SharedPreferences对象
     * 
     * @param ctx
     * @return
     */
    private static SharedPreferences getSharedPreferences(Context ctx) {
        if (ctx == null) {
            return null;
        }
//        return ctx.getSharedPreferences("nuoter", ctx.MODE_PRIVATE);
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    /**
     * 获取设备SharedPreferences中存储的指定key对应的value
     *
     * @param key
     * @return
     */
    public static String getStringValue(String key) {
        String defaultValue = "";
        if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        SharedPreferences sp = getSharedPreferences(BaseApplication.getInstance());
        if (sp == null) {
            return defaultValue;
        }
        return sp.getString(key, defaultValue);
    }

    /**
     * 获取设备SharedPreferences中存储的指定key对应的value 有默认值
     *
     * @param key
     * @return
     */
    public static String getStringValue(String key, String defaultValue) {
    	if (TextUtils.isEmpty(key)) {
    		return defaultValue;
    	}
    	SharedPreferences sp = getSharedPreferences(BaseApplication.getInstance());
    	if (sp == null) {
    		return defaultValue;
    	}
    	return sp.getString(key, defaultValue);
    }

    /**
     * 获取设备SharedPreferences中存储的指定key对应的value
     *
     * @param key
     * @return
     */
    public static float getFloatValue(String key) {
        float defaultValue = 0f;
        if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        SharedPreferences sp = getSharedPreferences(BaseApplication.getInstance());
        if (sp == null) {
            return defaultValue;
        }
        return sp.getFloat(key, defaultValue);
    }

    /**
     * 获取设备SharedPreferences中存储的指定key对应的value
     *
     * @param key
     * @return
     */
    public static int getIntValue(String key, int defaultValue) {
        if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        SharedPreferences sp = getSharedPreferences(BaseApplication.getInstance());
        if (sp == null) {
            return defaultValue;
        }
        return sp.getInt(key, defaultValue);
    }

    /**
     * 获取设备SharedPreferences中存储的指定key对应的value
     *
     * @param key
     * @return
     */
    public static long getLongValue( String key) {
        long defaultValue = 0l;
        if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        SharedPreferences sp = getSharedPreferences(BaseApplication.getInstance());
        if (sp == null) {
            return defaultValue;
        }
        return sp.getLong(key, defaultValue);
    }

    /**
     * 获取设备SharedPreferences中存储的指定key对应的value
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBooleanValue(String key, boolean defaultValue) {
        if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        SharedPreferences sp = getSharedPreferences(BaseApplication.getInstance());
        if (sp == null) {
            return defaultValue;
        }
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * 获取设备SharedPreferences中存储的指定key对应的value
     *
     * @param key
     * @return
     */
    public static boolean getBooleanValue(String key) {
        return getBooleanValue(key, false);
    }

    /**
     * 获取Editor对象
     *
     * @return
     */
    private static Editor getEditor() {
        SharedPreferences sp = getSharedPreferences(BaseApplication.getInstance());
        if (sp == null) {
            return null;
        }
        return sp.edit();
    }

    /**
     * 保存int型数据到默认的SharedPreferences
     *
     * @param key
     * @param value
     */
    public static void saveIntValue(String key, int value) {
        Editor editor = getEditor();
        if (editor == null) {
            return;
        }
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 保存String型数据到默认的SharedPreferences
     *
     * @param key
     * @param value
     */
    public static void saveStringValue(String key, String value) {
        Editor editor = getEditor();
        if (editor == null) {
            return;
        }
        editor.putString(key, TextUtils.isEmpty(value) ? "" : value);
        editor.commit();
    }

    /**
     * 保存boolean型数据到默认的SharedPreferences
     *
     * @param key
     * @param value
     */
    public static void saveBooleanValue(String key, boolean value) {
        Editor editor = getEditor();
        if (editor == null) {
            return;
        }
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 保存float型数据到默认的SharedPreferences
     *
     * @param key
     * @param value
     */
    public static void saveFloatValue(String key, float value) {
        Editor editor = getEditor();
        if (editor == null) {
            return;
        }
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * 保存long型数据到默认的SharedPreferences
     *
     * @param key
     * @param value
     */
    public static void saveLongValue(String key, long value) {
        Editor editor = getEditor();
        if (editor == null) {
            return;
        }
        editor.putLong(key, value);
        editor.commit();
    }

    public static String getAll2String() {
        StringBuilder stringBuilder = new StringBuilder();
        SharedPreferences sp = getSharedPreferences(BaseApplication.getInstance());
        Map<String ,Object> map = (Map<String, Object>) sp.getAll();
        for (String key : map.keySet()) {
            stringBuilder.append(key + " : " + map.get(key)+"\n");
        }
        return stringBuilder.toString();
    }
    //清空所有的sharedPreferenced
    public static void clear() {
        SharedPreferences.Editor editor = getEditor();
        editor.clear();
        editor.commit();
    }
}
