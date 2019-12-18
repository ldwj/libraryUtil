package com.ldwj.library.util.utils;


import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

import com.ldwj.libraryapplication.BaseApplication;

import java.util.List;


public class AppUtil {
  /**
   * 网络是否可以使用
   *
   * @param context
   * @return
   */
  public static boolean isNetworkAvailable(Context context) {
    try {
      ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      if (connectivity != null) {
        NetworkInfo info = connectivity.getActiveNetworkInfo();
        if (info != null) {
          return info.isAvailable();
        }
      }
    } catch (Exception e) {
      return false;
    }
    return false;
  }


  /**
   * 获取应用程序名称
   */
  public static synchronized String getAppName(Context context) {
    try {
      PackageManager packageManager = context.getPackageManager();
      PackageInfo packageInfo = packageManager.getPackageInfo(
              context.getPackageName(), 0);
      int labelRes = packageInfo.applicationInfo.labelRes;
      return context.getResources().getString(labelRes);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * [获取应用程序版本名称信息]
   *
   * @param context
   * @return 当前应用的版本名称
   */
  public static synchronized String getVersionName(Context context) {
    try {
      PackageManager packageManager = context.getPackageManager();
      PackageInfo packageInfo = packageManager.getPackageInfo(
              context.getPackageName(), 0);
      return packageInfo.versionName;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 跳至拨号界面
   *
   * @param phoneNumber
   *            电话号码
   */
  public static void dial(Context context,String phoneNumber)
  {
    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
  }

  /**
   * 拨打电话 <p>需添加权限 {@code <uses-permission android:name="android.permission.CALL_PHONE"/>}</p>
   *
   * @param phoneNumber
   *            电话号码
   */
  public static void call(Context context,String phoneNumber)
  {
    Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNumber));
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
  }
  /**
   * 跳至发送短信界面
   *
   * @param phoneNumber
   *            接收号码
   * @param content
   *            短信内容
   */
  public static void sendSms(Context context,String phoneNumber, String content)
  {
    Uri uri = Uri.parse("smsto:" + (StringUtils.isEmpty(phoneNumber) ? "" : phoneNumber));
    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
    intent.putExtra("sms_body", StringUtils.isEmpty(content) ? "" : content);
    context.startActivity(intent);
  }

  /**
   * 发送短信 <p>需添加权限 {@code <uses-permission android:name="android.permission.SEND_SMS"/>}</p>
   *
   * @param phoneNumber
   *            接收号码
   * @param content
   *            短信内容
   */
  public static void sendSmsSilent(Context context,String phoneNumber, String content)
  {
    if (StringUtils.isEmpty(content)) return;
    PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0, new Intent(),
            0);
    SmsManager smsManager = SmsManager.getDefault();
    if (content.length() >= 70)
    {
      List<String> ms = smsManager.divideMessage(content);
      for (String str : ms)
      {
        smsManager.sendTextMessage(phoneNumber, null, str, sentIntent, null);
      }
    }
    else
    {
      smsManager.sendTextMessage(phoneNumber, null, content, sentIntent, null);
    }
  }
  /**
   * [获取应用程序版本名称信息]
   *
   * @param context
   * @return 当前应用的版本名称
   */
  public static synchronized int getVersionCode(Context context) {
    try {
      PackageManager packageManager = context.getPackageManager();
      PackageInfo packageInfo = packageManager.getPackageInfo(
              context.getPackageName(), 0);
      return packageInfo.versionCode;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }


  /**
   * [获取应用程序版本名称信息]
   *
   * @param context
   * @return 当前应用的版本名称
   */
  public static synchronized String getPackageName(Context context) {
    try {
      PackageManager packageManager = context.getPackageManager();
      PackageInfo packageInfo = packageManager.getPackageInfo(
              context.getPackageName(), 0);
      return packageInfo.packageName;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }


  /**
   * 获取图标 bitmap
   *
   */
  public static synchronized Drawable getDrawable() {
    PackageManager packageManager = null;
    ApplicationInfo applicationInfo = null;
    try {
      packageManager = BaseApplication.getInstance().getApplicationContext()
              .getPackageManager();
      applicationInfo = packageManager.getApplicationInfo(
              BaseApplication.getInstance().getPackageName(), 0);
    } catch (PackageManager.NameNotFoundException e) {
      applicationInfo = null;
    }
    Drawable d = packageManager.getApplicationIcon(applicationInfo); //xxx根据自己的情况获取drawable
//    BitmapDrawable bd = (BitmapDrawable) d;
//    Bitmap bm = bd.getBitmap();
    return d;
  }
  /**
   * 获取图标 bitmap
   *
   */
  public static synchronized Bitmap getBitmap() {
    PackageManager packageManager = null;
    ApplicationInfo applicationInfo = null;
    try {
      packageManager = BaseApplication.getInstance().getApplicationContext()
              .getPackageManager();
      applicationInfo = packageManager.getApplicationInfo(
              BaseApplication.getInstance().getPackageName(), 0);
    } catch (PackageManager.NameNotFoundException e) {
      applicationInfo = null;
    }
    Drawable d = packageManager.getApplicationIcon(applicationInfo); //xxx根据自己的情况获取drawable
    BitmapDrawable bd = (BitmapDrawable) d;
    Bitmap bm = bd.getBitmap();
    return bm;
  }
  /**
   * 打开网络设置界面
   * ACTION_WIRELESS_SETTINGS 全部网络设置
   * ACTION_WIFI_SETTINGS wifi
   * ACTION_DATA_ROAMING_SETTINGS  流量
   */
  public static void openSetting(Activity activity)
  {
    Intent intent = null;
    // 先判断当前系统版本
    if(android.os.Build.VERSION.SDK_INT > 10){  // 3.0以上
      intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
    }else{
      intent = new Intent();
      intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
    }
    activity.startActivity(intent);
  }

  /**
   * 获取Imei
   * @return
   */
  public static String getImei(Context context) {
    String imei = "";
    if (Build.VERSION.SDK_INT >= 23) {
      if (BaseApplication.getInstance().checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
        TelephonyManager telephonyManager = (TelephonyManager) BaseApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null && telephonyManager.getDeviceId() != null && "".equals(telephonyManager.getDeviceId())) {
          imei = telephonyManager.getDeviceId();
        }
      }
    } else {
      try {
        TelephonyManager telephonyManager = (TelephonyManager) BaseApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null && telephonyManager.getDeviceId() != null && "".equals(telephonyManager.getDeviceId())) {
          imei = telephonyManager.getDeviceId();
        }
      } catch (Exception e) {

      }
    }
    if ("".equals(imei)) {
      if ("".equals((String) SharedPreferencesUtils.getStringValue("imei"))) {
        imei = java.util.UUID.randomUUID().toString();
        SharedPreferencesUtils.saveStringValue("imei", imei);
      } else {
        imei = (String) SharedPreferencesUtils.getStringValue("imei");
      }

    }
    return imei;
  }
}
