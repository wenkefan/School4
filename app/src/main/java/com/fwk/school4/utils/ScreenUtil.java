package com.fwk.school4.utils;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.fwk.school4.MyApp;


public class ScreenUtil {

	private static DisplayMetrics dm = MyApp.getResourcesObj().getDisplayMetrics();
	
	/**
	 * 获取屏幕宽度，与密度有关
	 * @return
	 */
	public static int getScreenWidth() {
		return dm.widthPixels;
	}

	/**
	 * 获取屏幕高度，与密度有关
	 * @return
	 */
	public static int getScreenHeight() {
		return dm.heightPixels;
	}

	/**
	 * 获取屏幕密度
	 * @return
	 */
	public static float getScreenDensity() {
		return dm.density;
	}
	
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(float dpValue) {
		return (int) (dpValue * getScreenDensity() + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(float pxValue) {
		return (int) (pxValue / getScreenDensity() + 0.5f);
	}
	
	
	
	public static int[] setViewWH(View iv, float widthRatio, float heightRatio,
								  ScaleType scaletype, Class<?> parent) {
	int[] a = new int[2];
	int width = (int) (getScreenWidth() * widthRatio);
	if (FrameLayout.class.equals(parent)) {
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				width, (int) (width * heightRatio));
		a[0] = width;
		a[1] = (int) (width * heightRatio);
		iv.setLayoutParams(params);
	} else if (LinearLayout.class.equals(parent)) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				width, (int) (width * heightRatio));
		iv.setLayoutParams(params);
		a[0] = width;
		a[1] = (int) (width * heightRatio);
	} else if (AbsListView.class.equals(parent)) {
		AbsListView.LayoutParams params = new AbsListView.LayoutParams(
				width, (int) (width * heightRatio));
		iv.setLayoutParams(params);
		a[0] = width;
		a[1] = (int) (width * heightRatio);
	} else {
		android.view.ViewGroup.LayoutParams params = new android.view.ViewGroup.LayoutParams(
				width, (int) (width * heightRatio));
		iv.setLayoutParams(params);
		a[0] = width;
		a[1] = (int) (width * heightRatio);
	}
	if (iv instanceof ImageView && null != scaletype) {
		((ImageView) iv).setScaleType(scaletype);
	}
	return a;
}
}
