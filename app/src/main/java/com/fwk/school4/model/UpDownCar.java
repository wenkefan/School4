package com.fwk.school4.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/11.
 */
public class UpDownCar implements Serializable {

    /**
     * Success : 10000
     * Message : null
     * RerurnValue : null
     */

    private int Success;
    private Object Message;
    private Object RerurnValue;

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int Success) {
        this.Success = Success;
    }

    public Object getMessage() {
        return Message;
    }

    public void setMessage(Object Message) {
        this.Message = Message;
    }

    public Object getRerurnValue() {
        return RerurnValue;
    }

    public void setRerurnValue(Object RerurnValue) {
        this.RerurnValue = RerurnValue;
    }
}
