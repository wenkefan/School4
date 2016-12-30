package com.fwk.school4;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;

public class MyApp extends Application {
	
	private static MyApp mInstance ;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		getApplicationContext();
	}
	
	public static Context getContext(){
		return mInstance.getApplicationContext();
	}

	/**
	 * 获取资源对象
	 */
	public static Resources getResourcesObj(){
		return mInstance.getResources();
	}
	
	/**
	 * 判断程序是否在Debug模式下运行
	 * @return
	 */
	public static boolean isDebug(){
		ApplicationInfo info = mInstance.getApplicationInfo();
		return ( info.flags & ApplicationInfo.FLAG_DEBUGGABLE ) != 0;
	}
	public static int number = 0;
}
