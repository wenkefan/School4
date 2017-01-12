package com.fwk.school4.network.api;

import android.app.Activity;

import com.fwk.school4.constant.Keyword;
import com.fwk.school4.model.FristFaChe;
import com.fwk.school4.listener.NetWorkListener;
import com.fwk.school4.utils.ToastUtil;


/**
 * Created by fanwenke on 16/11/22.
 */

public class FristNetWork extends BaseNetWork {

    private static Activity mActivity;

    public static FristNetWork newInstance(Activity activity){
        mActivity = activity;
        return new FristNetWork();
    }

    private FristNetWork(){
        initURL();
    }

    @Override
    public void setNetWorkListener(NetWorkListener listener) {
        this.listener = listener;
    }


    @Override
    public <T> void OnSucceed(int flag, T cla, final String message) {

        if (flag == Keyword.FLAGFIRSTFACHE){

            if (cla != null){

                final FristFaChe faChe = (FristFaChe) cla;

                try{
                    int paichedanhao = faChe.getRerurnValue();
                    spData.setInt(Keyword.SP_PAICHEDANHAO,paichedanhao);
                } catch (Exception o){
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.show(faChe.getMessage());
                        }
                    });
                }

                listener.NetWorkSuccess(Keyword.FLAGFIRSTFACHE);

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
