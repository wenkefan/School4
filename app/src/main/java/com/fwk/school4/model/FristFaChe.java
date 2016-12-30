package com.fwk.school4.model;

import java.io.Serializable;

/**
 * Created by fanwenke on 16/8/23.
 */
public class FristFaChe implements Serializable {

    /**
     * Success : 10000
     * Message : 根据校车班次添加派车单成功！
     * RerurnValue : 26
     */

    private int Success;
    private String Message;
    private int RerurnValue;

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int Success) {
        this.Success = Success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getRerurnValue() {
        return RerurnValue;
    }

    public void setRerurnValue(int RerurnValue) {
        this.RerurnValue = RerurnValue;
    }
}
