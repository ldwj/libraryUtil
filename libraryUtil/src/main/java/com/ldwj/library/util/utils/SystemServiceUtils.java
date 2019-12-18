package com.ldwj.library.util.utils;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.WindowManager;

/**
 *@author  LS: 
 */
public class SystemServiceUtils {
	/* 
	 * 电话状态： 
	 * 1.tm.CALL_STATE_IDLE=0          无活动 
	 * 2.tm.CALL_STATE_RINGING=1  响铃 
	 * 3.tm.CALL_STATE_OFFHOOK=2  摘机 
	 */  
	//	  tm.getCallState();//int  

	/* 
	 * 电话方位： 
	 *  
	 */  
	//	  tm.getCellLocation();//CellLocation  

	/* 
	 * 唯一的设备ID： 
	 * GSM手机的 IMEI 和 CDMA手机的 MEID.  
	 * Return null if device ID is not available. 
	 */  
	//	  tm.getDeviceId();//String  

	/* 
	 * 设备的软件版本号： 
	 * 例如：the IMEI/SV(software version) for GSM phones. 
	 * Return null if the software version is not available.  
	 */  
	//	  tm.getDeviceSoftwareVersion();//String  

	/* 
	 * 手机号： 
	 * GSM手机的 MSISDN. 
	 * Return null if it is unavailable.  
	 */  
	//	  tm.getLine1Number();//String  

	/* 
	 * 附近的电话的信息: 
	 * 类型：List<NeighboringCellInfo>  
	 * 需要权限：android.Manifest.permission#ACCESS_COARSE_UPDATES 
	 */  
	//	  tm.getNeighboringCellInfo();//List<NeighboringCellInfo>  

	/* 
	 * 获取ISO标准的国家码，即国际长途区号。 
	 * 注意：仅当用户已在网络注册后有效。 
	 *       在CDMA网络中结果也许不可靠。 
	 */  
	//	  tm.getNetworkCountryIso();//String  

	/* 
	 * MCC+MNC(mobile country code + mobile network code) 
	 * 注意：仅当用户已在网络注册时有效。 
	 *    在CDMA网络中结果也许不可靠。 
	 */  
	//	  tm.getNetworkOperator();//String  

	/* 
	 * 按照字母次序的current registered operator(当前已注册的用户)的名字 
	 * 注意：仅当用户已在网络注册时有效。 
	 *    在CDMA网络中结果也许不可靠。 
	 */  
	//	  tm.getNetworkOperatorName();//String  

	/* 
	 * 当前使用的网络类型： 
	 * 例如： NETWORK_TYPE_UNKNOWN  网络类型未知  0 
	     NETWORK_TYPE_GPRS     GPRS网络  1 
	     NETWORK_TYPE_EDGE     EDGE网络  2 
	     NETWORK_TYPE_UMTS     UMTS网络  3 
	     NETWORK_TYPE_HSDPA    HSDPA网络  8  
	     NETWORK_TYPE_HSUPA    HSUPA网络  9 
	     NETWORK_TYPE_HSPA     HSPA网络  10 
	     NETWORK_TYPE_CDMA     CDMA网络,IS95A 或 IS95B.  4 
	     NETWORK_TYPE_EVDO_0   EVDO网络, revision 0.  5 
	     NETWORK_TYPE_EVDO_A   EVDO网络, revision A.  6 
	     NETWORK_TYPE_1xRTT    1xRTT网络  7 
	 */  
	//	  tm.getNetworkType();//int  

	/* 
	 * 手机类型： 
	 * 例如： PHONE_TYPE_NONE  无信号 
	     PHONE_TYPE_GSM   GSM信号 
	     PHONE_TYPE_CDMA  CDMA信号 
	 */  
	//	  tm.getPhoneType();//int  

	/* 
	 * Returns the ISO country code equivalent for the SIM provider's country code. 
	 * 获取ISO国家码，相当于提供SIM卡的国家码。 
	 *  
	 */  
	//	  tm.getSimCountryIso();//String  

	/* 
	 * Returns the MCC+MNC (mobile country code + mobile network code) of the provider of the SIM. 5 or 6 decimal digits. 
	 * 获取SIM卡提供的移动国家码和移动网络码.5或6位的十进制数字. 
	 * SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断). 
	 */  
	//	  tm.getSimOperator();//String  

	/* 
	 * 服务商名称： 
	 * 例如：中国移动、联通 
	 * SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断). 
	 */  
	//	  tm.getSimOperatorName();//String  

	/* 
	 * SIM卡的序列号： 
	 * 需要权限：READ_PHONE_STATE 
	 */  
	//	  tm.getSimSerialNumber();//String  

	/* 
	 * SIM的状态信息： 
	 * SIM_STATE_UNKNOWN          未知状态 0 
	   SIM_STATE_ABSENT           没插卡 1 
	   SIM_STATE_PIN_REQUIRED     锁定状态，需要用户的PIN码解锁 2 
	   SIM_STATE_PUK_REQUIRED     锁定状态，需要用户的PUK码解锁 3 
	   SIM_STATE_NETWORK_LOCKED   锁定状态，需要网络的PIN码解锁 4 
	   SIM_STATE_READY            就绪状态 5 
	 */  
	//	  tm.getSimState();//int  

	/* 
	 * 唯一的用户ID： 
	 * 例如：IMSI(国际移动用户识别码) for a GSM phone. 
	 * 需要权限：READ_PHONE_STATE 
	 */  
	//	  tm.getSubscriberId();//String  

	/* 
	 * 取得和语音邮件相关的标签，即为识别符 
	 * 需要权限：READ_PHONE_STATE 
	 */  
	//	  tm.getVoiceMailAlphaTag();//String  

	/* 
	 * 获取语音邮件号码： 
	 * 需要权限：READ_PHONE_STATE 
	 */  
	//	  tm.getVoiceMailNumber();//String  

	/* 
	 * ICC卡是否存在 
	 */  
	//	  tm.hasIccCard();//boolean  

	/* 
	 * 是否漫游: 
	 * (在GSM用途下) 
	 */  
	//	  tm.isNetworkRoaming();//  

	//	        tm.getDeviceId();//获取设备编号  
	//	        tm.getSimCountryIso());//获取SIM卡国别  
	//	        tm.getSimSerialNumber());//获取SIM卡序列号      
	//	        simState[tm.getSimState()]);//获取SIM卡状态  
	//	        (tm.getDeviceSoftwareVersion()!=null?tm.getDeviceSoftwareVersion():"未知"));    //获取软件版本  
	//	        tm.getNetworkOperator());//获取网络运营商代号  
	//	        tm.getNetworkOperatorName());//获取网络运营商名称  
	//	        tm.getPhoneType();//获取手机制式  
	//	        tm.getCellLocation().toString());//获取设备当前位置  

	/**
	 * 手机sim管理
	 * @return
	 */
	public static TelephonyManager getTelephoneManager(Context cxt){
		TelephonyManager tm = (TelephonyManager) cxt.getSystemService(Context.TELEPHONY_SERVICE);
		return tm;
	}
	/**
	 * 管理打开的窗口程序
	 * @param cxt
	 * @return
	 */
	public static WindowManager getWindowManager(Context cxt){
		WindowManager wm = (WindowManager) cxt.getSystemService(Context.WINDOW_SERVICE);
		return wm;
	}
	/**
	 * 取得xml里定义的view
	 */
	public static LayoutInflater getLayoutInflater(Context cxt){
		return (LayoutInflater) cxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	/**
	 * 管理应用程序的系统状态
	 * @return
	 */
	public static  ActivityManager getActivityManager(Context cxt){
		return (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
	}              

	/**
	 * 电源的服务
	 * @param cxt
	 * @return
	 */
	public static  PowerManager getPowerManger(Context cxt){
		return (PowerManager) cxt.getSystemService(Context.POWER_SERVICE);
	} 
	/**
	 * 闹钟的服务
	 * @param cxt
	 * @return
	 */
	public static  AlarmManager getAlarmManager(Context cxt){
		return (AlarmManager) cxt.getSystemService(Context.ALARM_SERVICE);
	}

	/**
	 * 状态栏的服务
	 * @param cxt
	 * @return
	 */
	public static  NotificationManager getNotificationManager(Context cxt){
		return (NotificationManager) cxt.getSystemService(Context.NOTIFICATION_SERVICE);
	}

	/**
	 * 键盘锁的服务
	 * @param cxt
	 * @return
	 * 
	 <uses-permission android:name="android.permission.DISABLE_KEYGUARD" />
	 KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE); 
		lock.disableKeyguard();
		lock.reenableKeyguard();
		通过disableKeyguard()函数来解除锁屏，
		通过来reenableKeyguard()反解除锁屏.
		reenableKeyguard()反解除锁屏的意思是：
		如果在调用disableKeyguard()函数之前是锁屏的，
		那么就进行锁屏，否则不进行任何操作。
		当然如果之前没调用disableKeyguard()函数，
		也不进行任何操作。
	 */
	public static  KeyguardManager getKeyguardManager(Context cxt){
		return (KeyguardManager) cxt.getSystemService(Context.KEYGUARD_SERVICE);
	}

	/**
	 * 位置的服务，如GPS
	 * @param cxt
	 * @return
	 * //GPS和被动提供程序所需要,也可以用于网络
	 * //网络提供程序需要
	 <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
	<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
//提供设备最后已知位置,这里有3种,GPS_PROVIDER GPS获得,NETWORK_PROVIDER网络获得,PASSIVE_PROVIDER被动提供其他应用程序提供
  locMgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
  
locMgr.getAllProviders();//返回所有能用或不能用的提供程序
locMgr.getProvider(Provider name);//返回指定提供程序
locMgr.isProviderEnabled(provider);//判断指定提供程序是否能用
locMgr.getProviders(true);//返回立即可以使用的提供程序
locMgr.getProviders(criteria, true)//返回可以使用的提供程序，并且用criteria对象指定条件

locMgr.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0,			// 间隔多少毫秒通知
            0,			// 最小间隔距离变化通知
            locListener);//在onResume()中注册接收位置跟新事件的接收器
locMgr.removeUpdates(locListener);//在onPause()中删除注册

LocationListener locListener = new LocationListener(){//位置监听器
            //位置信息更新时调用
            public void  onLocationChanged(Location location)
            {
                if (location != null)
                {
                    Toast.makeText(getBaseContext(),
                        "New location latitude [" + 
                        location.getLatitude() +
                        "] longitude [" + 
                        location.getLongitude()+"]",
                        Toast.LENGTH_SHORT).show();
                }
            }

            //当禁用的提供程序被调用时会立即调用
            public void  onProviderDisabled(String provider)
            {
            }

            //用户启用提供程序时被调用
            public void  onProviderEnabled(String provider)
            {
            }

            //状态变化时被调用
            public void  onStatusChanged(String provider, 
                            int status, Bundle extras)
            {
            }
        };
    }
startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCALE_SETTINGS), 0);//请求用户打开GPS

LocationManager 接近提醒
//geo模式:用uri的前缀来过滤数据类型
String PROX_ALERT = "com.androidbook.intent.action.PROXIMITY_ALERT";
IntentFilter iFilter = new IntentFilter(PROX_ALERT);//设置了过滤器所接受的行为
iFilter.addDataScheme("geo");//设置了过滤器说接受的uri前缀

String geo = "geo:"+lat+","+lon;
Intent intent = new Intent(PROX_ALERT, Uri.parse(geo));//在intent中添加uri匹配intentFilter
pIntent1 = PendingIntent.getBroadcast(getApplicationContext(), 0, intent,
        		PendingIntent.FLAG_CANCEL_CURRENT);//创建PendingIntent
locMgr.addProximityAlert(lat, lon, radius, 6000L, pIntent1);//纬度,经度,半径，超时,触发后的行为


	 */
	public static  LocationManager getLocationManager(Context cxt){
		return (LocationManager) cxt.getSystemService(Context.LOCATION_SERVICE);
	}                                       

	/**
	 *   搜索的服务
	 * @param cxt
	 * @return
	 */
	public static  SearchManager getSearchManager(Context cxt){
		return (SearchManager) cxt.getSystemService(Context.SEARCH_SERVICE);
	} 

	/**
	 *   手机震动的服务
	 	<uses-permission android:name="android.permission.VIBRATE" />
	 * @param cxt
	 * @return
	 vibrator.vibrate(1000)
	  vibrator.vibrate(new long[]{1000,200},0)//一直震动:-1震动一次
	  vibrator.cancel();
	 */
	public static  Vibrator getVebrator(Context cxt){
		return (Vibrator) cxt.getSystemService(Context.VIBRATOR_SERVICE);
	} 
	/**
	 * 传感器             
	 * @param cxt
	 * @return
	ShakeListener 中有详细用法
	 */
	public static  SensorManager getSensorManager(Context cxt){
		
		return (SensorManager) cxt.getSystemService(Context.SENSOR_SERVICE);
	}
	/**
	 *  网络连接的服务
	 * @param cxt
	 * @return
		<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();//获取网络的连接情况  
		networkInfo.isAvailable();  //true 当前的网络连接可用;

if(activeNetInfo.getType()==ConnectivityManager.TYPE_WIFI){  
//判断WIFI网  
}else if(activeNetInfo.getType()==ConnectivityManager.TYPE_MOBILE) {  
//判断3G网  
}  

connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() 或者networkInfo.getType() == ConnectivityManager.TYPE_WIFI//TYPE_MOBILE

State.CONNECTED: GPRS网络已连接

connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()
State.CONNECTED: WIFI网络已连接


	 *
	 */
	public static  ConnectivityManager getConnectivityManager(Context cxt){
		return (ConnectivityManager) cxt.getSystemService(Context.CONNECTIVITY_SERVICE);
	} 

	/**
	 *  Wi-Fi服务
	 * @param cxt
	 * @return
		wm.getWifiState();
	 * WIFI_STATE_ENABLING:正在打开
	 * WifiManager.WIFI_STATE_ENABLED:已打开
	 *  WifiManager.WIFI_STATE_DISABLING:正在关闭
	 *  WifiManager.WIFI_STATE_DISABLED:已关闭
	 *  WifiManager.WIFI_STATE_UNKNOWN: 无法取得或辨识WiFi状态 
	 *  
		 WifiInfo wif = wm.getConnectionInfo(); 
					　　1、getBSSID()   获取接入点的mac地址（String）
					　　2、getIpAddress() 获取本机的IP地址   （int）
					　　3、getLinkSpeed() 获取连接速度(不是下载速度)，单位为Mbps  （int）
					　　4、getMacAddress() 获取mac地址  （String）
					　　5、getNetworkId()    获取网络id号。每一个设定好了的网络都有一个独一无二的整数型ID号，用来识别网络，当操作请求时（翻译）。简而言之，就是这个号就是代表一个连接点，手机可以通过wifi连接很多无线网的。
					　　6、getRssi()       返回收到的信号强度，是个负数。（好像到-113就表示一点信号都没有，也就是说数越大信号就越好）
					　　7、getSSID()    获取无线信号提供者的名称  

		DhcpInfo df=wm.getDhcpInfo();
		
　　DhcpInfo类没什么方法，提供了一些字段：
　　1、dns1  第一个DNS
　　2、dns2 如上
　　3、gateway  网关
　　4、ipAddress  IP地址
　　5、netmask 子网掩码
　　6、serverAddress 服务端地址（其实就是路由器ip，和网关一样）
　　7、leaseDuration  和上面的getLinkSpeed() 方法的值接近，可能是连接速度，不过单位不是Mbps，getLinkSpeed() 得到72，这个字段的值是7200
	 */
	public static  WifiManager getWifiManager(Context cxt){
		return (WifiManager) cxt.getSystemService(Context.WIFI_SERVICE);
	}
}
