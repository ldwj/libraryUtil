package com.ldwj.library.util.utils;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

/**
 * @author LS:
 */
public class PakageInfoUtil
{
    private Context context;

    public PakageInfoUtil(Context context)
    {
        super();
        this.context = context;
    }

    /**
     * 获取设备安装的app
     * @return
     */
    public List<AppInfo> getAppInfo()
    {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> pakageinfos = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        List<AppInfo> appInfos = new ArrayList<AppInfo>();
        for (PackageInfo packageInfo : pakageinfos)
        {
            AppInfo appInfo = new AppInfo();
            // 获取字符串方法
//            context.getString(R.string.app_name);
//            context.getResources().getString(R.string.app_name);
            String str_name = packageInfo.applicationInfo.loadLabel(pm).toString();
            appInfo.setAppName(str_name);
            // 获取应用程序的版本号码
            String version = packageInfo.versionName;
            appInfo.setAppVersion(version);
            // 获取应用程序的快捷方式图标
            Drawable drawable = packageInfo.applicationInfo.loadIcon(pm);
            appInfo.setDrawable(drawable);
            // 获取应用程序是否是第三方应用程序
            appInfo.setIsUserApp(filterApp(packageInfo.applicationInfo));
            // 给一同程序设置包名
            appInfo.setPackageName(packageInfo.packageName);
            appInfos.add(appInfo);
        }
        return appInfos;
    }

    /**
     * 三方应用程序的过滤器
     * 
     * @param info
     * @return true 三方应用 false 系统应用
     */
    public boolean filterApp(ApplicationInfo info)
    {
        if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0)
        {
            // 代表的是系统的应用,但是被用户升级了. 用户应用
            return true;
        }
        else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
        {
            // 代表的用户的应用
            return true;
        }
        return false;
    }

    /**
     * appinfo
     * 
     * @author lishuai
     */
    public class AppInfo implements Serializable
    {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        private boolean isUserApp;// 用户应用

        private String appName, appVersion, packageName;

        private Drawable drawable;// 获取应用程序的快捷方式图标

        public boolean isUserApp()
        {
            return isUserApp;
        }

        public void setIsUserApp(boolean isUserApp)
        {
            this.isUserApp = isUserApp;
        }

        public String getAppName()
        {
            return appName;
        }

        public void setAppName(String appName)
        {
            this.appName = appName;
        }

        public String getAppVersion()
        {
            return appVersion;
        }

        public void setAppVersion(String appVersion)
        {
            this.appVersion = appVersion;
        }

        public String getPackageName()
        {
            return packageName;
        }

        public void setPackageName(String packageName)
        {
            this.packageName = packageName;
        }

        public Drawable getDrawable()
        {
            return drawable;
        }

        public void setDrawable(Drawable drawable)
        {
            this.drawable = drawable;
        }
    }
}
