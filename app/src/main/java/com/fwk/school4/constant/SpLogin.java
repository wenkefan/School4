package com.fwk.school4.constant;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.fwk.school4.MyApp;

/**
 * Created by fanwenke on 16/8/16.
 */
public class SpLogin {
    public static final SharedPreferences SP = MyApp.getContext()
            .getSharedPreferences(Keyword.SPLOGIN, Context.MODE_PRIVATE);
    /**
     * 保存登录信息
     *
     * @param userName 用户名
     * @param password 密码
     * @param kgId     kg ID
     * @param kgName
     * @param name
     * @param userId
     */
    public static void save(String userName, String password, int kgId, String kgName, String name, int userId, int WorkerExtensionId) {
        Editor editor = SP.edit();
        editor.putString(Keyword.LOGIN_USERNAME, userName);
        editor.putString(Keyword.LOGIN_PASSWORD, password);
        editor.putInt(Keyword.LOGIN_KGID, kgId);
        editor.putString(Keyword.LOGIN_KGNAME, kgName);
        editor.putString(Keyword.LOGIN_NAME, name);
        editor.putInt(Keyword.LOGIN_USERID, userId);
        editor.putInt(Keyword.LOGIN_WORKEREXTENSIONID,WorkerExtensionId);
        editor.commit();
    }

    //获取username
    public static String getUserName() {
        return SP.getString(Keyword.LOGIN_USERNAME, null);
    }

    //获取password
    public static String getPassWord() {
        return SP.getString(Keyword.LOGIN_PASSWORD, null);
    }

    //获取kgid
    public static int getKgId() {
        return SP.getInt(Keyword.LOGIN_KGID, 0);
    }

    //获取kgname
    public static String getKgName() {
        return SP.getString(Keyword.LOGIN_KGNAME, null);
    }

    //获取name
    public static String getName() {
        return SP.getString(Keyword.LOGIN_NAME, null);
    }

    //获取userid
    public static int getUserId() {
        return SP.getInt(Keyword.LOGIN_USERID, 0);
    }
    //获取WorkerExtensionId
    public static int getWorkerExtensionId(){
        return SP.getInt(Keyword.LOGIN_WORKEREXTENSIONID,0);
    }
    /**
     * 设置是否登录
     *
     * @param loggedIn
     */
    public static void setAlreadyLogin(boolean loggedIn) {
        Editor editor = SP.edit();
        editor.putBoolean(Keyword.LOGIN_ALREADYLOGIN, loggedIn);
        editor.commit();
    }

    public static boolean getAlreadyLogin() {
        return SP.getBoolean(Keyword.LOGIN_ALREADYLOGIN, false);
    }
}
