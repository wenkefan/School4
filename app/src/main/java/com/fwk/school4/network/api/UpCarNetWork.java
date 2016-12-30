package com.fwk.school4.network.api;

import android.app.Activity;

import com.fwk.school4.constant.Keyword;
import com.fwk.school4.model.UpDownCar;
import com.fwk.school4.utils.ToastUtil;


/**
 * Created by fanwenke on 16/11/22.
 */

public class UpCarNetWork extends BaseNetWork {

    private static Activity mActivity;

    public static UpCarNetWork newInstance(Activity activity){
        mActivity = activity;
        return new UpCarNetWork();
    }

    private UpCarNetWork(){
        initURL();
    }

    @Override
    public <T> void OnSucceed(int flag, T cla, final String message) {
        if (flag == Keyword.FLAGUPCAR){
            if (cla != null){

                UpDownCar upDownCar = (UpDownCar) cla;

                listener.NetWorkSuccess(Keyword.FLAGUPCAR);
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

    }
}
