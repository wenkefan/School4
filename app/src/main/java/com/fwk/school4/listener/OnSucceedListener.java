package com.fwk.school4.listener;

/**
 * Created by Administrator on 2016/7/20.
 */
public interface OnSucceedListener {
    public <T> void OnSucceed(int flag, T cla, String message);

    public void Error();
}
