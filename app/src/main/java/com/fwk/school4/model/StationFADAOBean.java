package com.fwk.school4.model;

import java.io.Serializable;

/**
 * Created by fanwenke on 16/8/23.
 */
public class StationFADAOBean implements Serializable {

    /**
     * Success : 10000
     * Message : null
     * RerurnValue : 6
     */

    private int Success;
    private Object Message;
    private int RerurnValue;

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

    public int getRerurnValue() {
        return RerurnValue;
    }

    public void setRerurnValue(int RerurnValue) {
        this.RerurnValue = RerurnValue;
    }
}
