package com.fwk.school4.network.api;

import android.app.Activity;

import com.fwk.school4.constant.Keyword;
import com.fwk.school4.model.StationFADAOBean;
import com.fwk.school4.utils.ToastUtil;


/**
 * Created by fanwenke on 16/11/22.
 */

public class CarFCNetWork extends BaseNetWork {

    private static Activity mActivity;

    public static CarFCNetWork newInstance(Activity activity){
        mActivity = activity;
        return new CarFCNetWork();
    }

    private CarFCNetWork(){
        initURL();
    }

    @Override
    public <T> void OnSucceed(int flag, T cla, final String message) {
        if (flag == Keyword.FLAGFACHE){
            if (cla != null){

                StationFADAOBean fadaoBean = (StationFADAOBean) cla;

                listener.NetWorkSuccess(Keyword.FLAGFACHE);
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
