package com.fwk.school4.model;

import java.io.Serializable;

/**
 * Created by fanwenke on 16/8/16.
 */
public class LoginBean implements Serializable {

    /**
     * Success : 10000
     * Message :
     * RerurnValue : {"KgId":33,"KgName":null,"Name":"王博","UserId":456,"WorkerExtensionId":145}
     */

    private int Success;
    private String Message;
    /**
     * KgId : 33
     * KgName : null
     * Name : 王博
     * UserId : 456
     * WorkerExtensionId : 145
     */

    private RerurnValueBean RerurnValue;

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

    public RerurnValueBean getRerurnValue() {
        return RerurnValue;
    }

    public void setRerurnValue(RerurnValueBean RerurnValue) {
        this.RerurnValue = RerurnValue;
    }

    public static class RerurnValueBean {
        private int KgId;
        private Object KgName;
        private String Name;
        private int UserId;
        private int WorkerExtensionId;

        public int getKgId() {
            return KgId;
        }

        public void setKgId(int KgId) {
            this.KgId = KgId;
        }

        public Object getKgName() {
            return KgName;
        }

        public void setKgName(Object KgName) {
            this.KgName = KgName;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public int getWorkerExtensionId() {
            return WorkerExtensionId;
        }

        public void setWorkerExtensionId(int WorkerExtensionId) {
            this.WorkerExtensionId = WorkerExtensionId;
        }
    }
}
