package com.ldwj.library.util.utils;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.io.File;


/**
 * <pre> author: Blankj blog : http://blankj.com time : 2016/9/23 desc : 意图相关工具类 </pre>
 */
public class IntentUtils
{

    private IntentUtils()
    {
        throw new UnsupportedOperationException("u can't fuck me...");
    }

    /**
     * 获取卸载App的意图
     * 
     * @param packageName
     *            包名
     * @return intent
     */
    public static Intent getUninstallAppIntent(String packageName)
    {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取打开App的意图
     * 
     * @param context
     *            上下文
     * @param packageName
     *            包名
     * @return intent
     */
    public static Intent getLaunchAppIntent(Context context, String packageName)
    {
        return context.getPackageManager().getLaunchIntentForPackage(packageName);
    }

    /**
     * 获取App具体设置的意图
     * 
     * @param packageName
     *            包名
     * @return intent
     */
    public static Intent getAppDetailsSettingsIntent(String packageName)
    {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + packageName));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取分享文本的意图
     * 
     * @param content
     *            分享文本
     * @return intent
     */
    public static Intent getShareTextIntent(String content)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, content);
        return intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取分享图片的意图
     * 
     * @param content
     *            文本
     * @param imagePath
     *            图片文件路径
     * @return intent
     */
    public static Intent getShareImageIntent(String content, String imagePath)
    {
        return getShareImageIntent(content, new File(imagePath));
    }

    /**
     * 获取分享图片的意图
     * 
     * @param content
     *            文本
     * @param image
     *            图片文件
     * @return intent
     */
    public static Intent getShareImageIntent(String content, File image)
    {
        if (!image.exists()) return null;
        return getShareImageIntent(content, Uri.fromFile(image));
    }

    /**
     * 获取分享图片的意图
     * 
     * @param content
     *            分享文本
     * @param uri
     *            图片uri
     * @return intent
     */
    public static Intent getShareImageIntent(String content, Uri uri)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        return intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取其他应用组件的意图
     * 
     * @param packageName
     *            包名
     * @param className
     *            全类名
     * @return intent
     */
    public static Intent getComponentIntent(String packageName, String className)
    {
        return getComponentIntent(packageName, className, null);
    }

    /**
     * 获取其他应用组件的意图
     * 
     * @param packageName
     *            包名
     * @param className
     *            全类名
     * @param bundle
     *            bundle
     * @return intent
     */
    public static Intent getComponentIntent(String packageName, String className, Bundle bundle)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (bundle != null) intent.putExtras(bundle);
        ComponentName cn = new ComponentName(packageName, className);
        intent.setComponent(cn);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取关机的意图 <p>需添加权限 {@code <uses-permission android:name="android.permission.SHUTDOWN"/>}</p>
     * 
     * @return intent
     */
    public static Intent getShutdownIntent()
    {
        Intent intent = new Intent(Intent.ACTION_SHUTDOWN);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    // public static Intent getDailIntent(){
    //
    // }

    /**
     * 获取拍照的意图
     * 
     * @param outUri
     *            输出的uri
     * @return 拍照的意图
     */
    public static Intent getCaptureIntent(Uri outUri)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outUri);
        return intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                               | Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}
