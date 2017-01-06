package com.fwk.school4.network.api;

import android.app.Activity;

import com.fwk.school4.constant.Keyword;
import com.fwk.school4.model.StationFADAOBean;
import com.fwk.school4.utils.ToastUtil;


/**
 * Created by fanwenke on 16/11/22.
 */

public class CarDZNetWork extends BaseNetWork {

    private static Activity mActivity;

    public static CarDZNetWork newInstance(Activity activity){
        mActivity = activity;
        return new CarDZNetWork();
    }

    private CarDZNetWork(){
        initURL();
    }

    @Override
    public <T> void OnSucceed(int flag, T cla, final String message) {
        if (flag == Keyword.FLAGDAOZHAN){
            if (cla != null){

                StationFADAOBean dzdaoBean = (StationFADAOBean) cla;

                listener.NetWorkSuccess(Keyword.FLAGDAOZHAN);
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
