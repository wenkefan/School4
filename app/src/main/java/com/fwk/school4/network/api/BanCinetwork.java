package com.fwk.school4.network.api;


import android.app.Activity;


import com.fwk.school4.constant.Keyword;
import com.fwk.school4.constant.SpLogin;
import com.fwk.school4.model.BanciBean;
import com.fwk.school4.listener.NetWorkListener;
import com.fwk.school4.utils.GetDateTime;
import com.fwk.school4.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by fanwenke on 16/11/21.
 */

public class BanCinetwork extends BaseNetWork {


    private static Activity mActivity;

    private List<BanciBean.RerurnValueBean> list = new ArrayList<>();
    private List<BanciBean.RerurnValueBean> list1 = new ArrayList<>();

    public static BanCinetwork newInstance(Activity activity) {
        mActivity = activity;
        return new BanCinetwork();
    }

    private BanCinetwork() {
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
        if (flag == Keyword.FLAGBANCI) {

            if (cla != null) {

                BanciBean bean = (BanciBean) cla;

                try {

                    for (BanciBean.RerurnValueBean valueBean : bean.getRerurnValue()) {

                        if (valueBean.getTeacherId() == SpLogin.getWorkerExtensionId()) {
                            valueBean.setOriginal(true);
                            list1.add(0, valueBean);
                        } else {
                            valueBean.setOriginal(false);
                            list.add(valueBean);
                        }

                    }

                } catch (Exception o) {

                    final BanciBean finalBean = bean;
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.show(finalBean.getMessage());
                        }
                    });
                    return;

                }
                //排序
                sortList(list1);
                sortList(list);
                for (int i = 0; i < list1.size(); i++){
                    list.add(i,list1.get(i));
                }

                spData.saveToShared(Keyword.SP_BANCI, bean);
                spData.saveToShared(Keyword.SP_BANCI_LIST, list);
                bean = null;
                list = null;

                listener.NetWorkSuccess(Keyword.FLAGBANCI);

            } else {

                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.show(message);
                        return;
                    }
                });

            }

        }

    }

    private void sortList(List<BanciBean.RerurnValueBean> list) {
        Collections.sort(list, new Comparator<BanciBean.RerurnValueBean>() {
            @Override
            public int compare(BanciBean.RerurnValueBean o1, BanciBean.RerurnValueBean o2) {
                Double d = Double.parseDouble(GetDateTime.getHM2(o1.getSendStartTime()));
                Double d2 = Double.parseDouble(GetDateTime.getHM2(o2.getSendStartTime()));
                return d.compareTo(d2);
            }
        });
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
