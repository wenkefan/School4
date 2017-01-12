package com.fwk.school4.network.api;


import com.fwk.school4.network.OKHttp;
import com.fwk.school4.listener.NetWorkListener;
import com.fwk.school4.listener.OnSucceedListener;
import com.fwk.school4.utils.SharedPreferencesUtils;
import com.fwk.school4.utils.SharedPreferencesUtils2;

/**
 * Created by fanwenke on 16/11/21.
 */

public abstract class BaseNetWork implements OnSucceedListener {
    NetWorkListener listener;


    public void setNetWorkListener(NetWorkListener listener){
        this.listener = listener;
    };
    public OKHttp okHttp;
    public SharedPreferencesUtils2 spData;

    public void initURL(){
        okHttp = OKHttp.getInstance();
        okHttp.setListener(this);
        spData = new SharedPreferencesUtils2();
    }

    public void setUrl(int Flag, String url, Class cla){
        okHttp.request(Flag,url,cla);
    };

    @Override
    public abstract  <T> void OnSucceed(int flag, T cla, String message);

    @Override
    public abstract void Error();
}
