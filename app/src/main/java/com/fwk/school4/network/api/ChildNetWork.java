package com.fwk.school4.network.api;

import android.app.Activity;


import com.fwk.school4.constant.Keyword;
import com.fwk.school4.model.ChildBean;
import com.fwk.school4.listener.NetWorkListener;
import com.fwk.school4.utils.Stationutil;
import com.fwk.school4.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanwenke on 16/11/21.
 */

public class ChildNetWork extends BaseNetWork {


    private static Activity mActivity;
    private List<ChildBean.RerurnValueBean> list = new ArrayList<>();

    public static ChildNetWork newInstance(Activity activity) {
        mActivity = activity;
        return new ChildNetWork();
    }

    private ChildNetWork() {
        initURL();
    }

    @Override
    public void setNetWorkListener(NetWorkListener listener) {
        this.listener = listener;
    }

    @Override
    public void setUrl(int Flag, String url, Class cla) {
        okHttp.request(Flag, url, cla);
    }

    @Override
    public <T> void OnSucceed(int flag, T cla, final String message) {
        if (flag == Keyword.FLAGCHILD) {

            if (cla != null) {

                ChildBean bean = (ChildBean) cla;
                try {

                    list = bean.getRerurnValue();

                    for (ChildBean.RerurnValueBean valuebean : list) {
                        valuebean.setSelectid(0);
                        valuebean.setOperation(false);
                    }

                } catch (Exception o) {

                    final ChildBean finalBean = bean;
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.show(finalBean.getMessage());
                        }
                    });

                    return;

                }
                spData.saveToShared(Keyword.SP_CHILD_LIST, list);
                Stationutil stationutil = Stationutil.newInstance();
                stationutil.setMaplist();
                listener.NetWorkSuccess(Keyword.FLAGCHILD);
                list = null;
                bean = null;

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
