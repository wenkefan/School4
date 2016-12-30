package com.fwk.school4.utils;

/**
 * Created by Administrator on 2016/7/20.
 */
public class DataVerifyUtils {
    //登录账户验证

    public static boolean VerifyData(String userName, String password) {
        if (userName.isEmpty()){
            ToastUtil.show("账户不能为空");
            return false;
        }
        if (password.isEmpty()){
            ToastUtil.show("密码不能为空");
            return false;
        }
        return true;
    }
}
