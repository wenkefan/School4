package com.fwk.school4.utils;

import android.widget.Toast;

import com.fwk.school4.MyApp;


/**
 * @Description 封装系统Toast，重用Toast实例，增加用户体验</br>
 */
public class ToastUtil {

	@SuppressWarnings("unused")
	private static final String TAG = ToastUtil.class.getSimpleName();
	
	private static Toast toast ;
	/**
	 * 显示提示信息
	 * @param resId		消息资源的ID
	 */
	public static void show( int resId){
		show(resId, Toast.LENGTH_SHORT);
	}
	/**
	 * 显示提示信息
	 * @param resId		消息资源的ID
	 * @param duration	显示的时长
	 */
	public static void show( int resId, int duration ){
		if( toast==null ){
			toast =  Toast.makeText(MyApp.getContext(), resId, duration);
		} else {
			toast.setText(resId);
			toast.setDuration(duration);
		}
		toast.show();
	}
	/**
	 * 显示提示信息Toast.show(
	 * @param text		消息文本
	 */
	public static void show( CharSequence text){
		show(text, Toast.LENGTH_SHORT);
	}
	/**
	 * 显示提示信息
	 * @param text		消息文本
	 * @param duration	显示的时长
	 */
	public static void show(CharSequence text, int duration ){
		if( toast==null ){
			toast = Toast.makeText(MyApp.getContext(), text, duration);
		} else {
			toast.setText(text);
			toast.setDuration(duration);
		}
		toast.show();
	}
	/**
	 * 取消（撤销）当前的提示信息
	 */
	public static void cancelToast(){
		if( toast!=null ){
			toast.cancel();
		}
	}
}
