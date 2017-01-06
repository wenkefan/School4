package com.fwk.school4.network.api;

import android.app.Activity;

import com.fwk.school4.constant.Keyword;
import com.fwk.school4.model.UpDownCar;
import com.fwk.school4.utils.ToastUtil;


/**
 * Created by fanwenke on 16/11/22.
 * 上车接口
 */

public class DownCarNetWork extends BaseNetWork {

    private static Activity mActivity;

    public static DownCarNetWork newInstance(Activity activity){
        mActivity = activity;
        return new DownCarNetWork();
    }

    private DownCarNetWork(){
        initURL();
    }

    @Override
    public <T> void OnSucceed(int flag, T cla, final String message) {
        if (flag == Keyword.FLAGDOWNCAR){
            if (cla != null){

                UpDownCar upDownCar = (UpDownCar) cla;

                listener.NetWorkSuccess(Keyword.FLAGDOWNCAR);
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
