package com.ldwj.library.util.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 * @author wfs
 *
 */
public class AppManager {
	
	private static Stack<Activity> activityStack;
	private static AppManager instance;

	private Context mContext;

	private AppManager(Context context) {
		this.mContext = context.getApplicationContext();
	}

	public static AppManager getAppManager(Context context) {
		if (instance == null) {
			instance = new AppManager(context);
		}
		return instance;
	}
	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity){
		if(activityStack==null){
			activityStack=new Stack<Activity>();
		}
		activityStack.add(activity);
	}
	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Activity currentActivity(){
		Activity activity=activityStack.lastElement();
		return activity;
	}
	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity){
		if(activity!=null){
			activityStack.remove(activity);
			activity.finish();
			activity=null;
		}
	}
	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls){
		for (int i = activityStack.size() - 1; i >= 0; i--) {
			if(activityStack.get(i).getClass().equals(cls) ){
				finishActivity(activityStack.get(i));
			}

		}
	}
	/**
	 * 判断是否当前栈中有指定类名的Activity
	 */
	public boolean ifHaveClassActivity(Class<?> cls){
		for (int i = activityStack.size() - 1; i >= 0; i--) {
			if(activityStack.get(i).getClass().equals(cls) ){
				return true;
			}
		}
		return false;
	}
	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity(){
		for (int i = activityStack.size() - 1; i >= 0; i--) {
			if (null != activityStack.get(i)){
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}
	/**
	 * 结束所有Activity跳转到指定activity
	 */
	public void finishAllActivity(Class<?> cls){
		for (int i = activityStack.size() - 1; i >= 0; i--) {
			if(!activityStack.get(i).getClass().equals(cls) ){
				finishActivity(activityStack.get(i));
			}
		}
	}
	/**
	 * 退出应用程序
	 */
	@SuppressWarnings("deprecation")
	public void AppExit() {
		try {
			finishAllActivity();
			ActivityManager activityMgr= (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.restartPackage(mContext.getPackageName());
			System.exit(0);
		} catch (Exception e) {	}
	}
}