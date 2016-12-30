package com.fwk.school4.network.api;

import android.app.Activity;

import com.fwk.school4.constant.Keyword;
import com.fwk.school4.listener.NetWorkListener;
import com.fwk.school4.utils.ToastUtil;


/**
 * Created by fanwenke on 16/11/22.
 */

public class EndNetWork extends BaseNetWork {

    private static Activity mActivity;

    public static EndNetWork newInstance(Activity activity){
        mActivity = activity;
        return new EndNetWork();
    }

    private EndNetWork(){
        initURL();
    }

    @Override
    public void setNetWorkListener(NetWorkListener listener) {
        this.listener = listener;
    }


    @Override
    public <T> void OnSucceed(int flag, T cla, final String message) {

        if (flag == Keyword.FLAGENDDAOZHAN){

            if (cla != null){

                listener.NetWorkSuccess(Keyword.FLAGENDDAOZHAN);

            } else {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.show(message);
                    }
                });
            }



        }

    }

    @Override
    public void Error() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.show("网络错误");
            }
        });
    }
}
