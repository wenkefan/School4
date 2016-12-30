package com.fwk.school4.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fanwenke on 16/10/31.
 */

public class StationBean implements Serializable {

    /**
     * Success : 10000
     * Message :
     * RerurnValue : [{"StationName":"励志园(起点)","LineDetailsId":485,"KgId":33,"LineId":45,"LineDirections":1,"StationId":178,"Duration":0,"DisplayOrder":1,"State":1,"ModifierId":80,"ModifyDate":"2016-08-16T20:40:53.78","Remark":"起始站"},{"StationName":"远大路","LineDetailsId":487,"KgId":33,"LineId":45,"LineDirections":1,"StationId":179,"Duration":20,"DisplayOrder":2,"State":1,"ModifierId":80,"ModifyDate":"2016-08-16T20:41:14.797","Remark":""},{"StationName":"东冉路","LineDetailsId":489,"KgId":33,"LineId":45,"LineDirections":1,"StationId":180,"Duration":25,"DisplayOrder":3,"State":1,"ModifierId":80,"ModifyDate":"2016-08-16T20:41:36.03","Remark":""},{"StationName":"黄庄","LineDetailsId":491,"KgId":33,"LineId":45,"LineDirections":1,"StationId":181,"Duration":21,"DisplayOrder":4,"State":1,"ModifierId":80,"ModifyDate":"2016-08-16T20:41:57.937","Remark":""},{"StationName":"正福寺","LineDetailsId":493,"KgId":33,"LineId":45,"LineDirections":1,"StationId":182,"Duration":20,"DisplayOrder":5,"State":1,"ModifierId":80,"ModifyDate":"2016-08-16T20:42:20.673","Remark":""},{"StationName":"励志园(终点)","LineDetailsId":495,"KgId":33,"LineId":45,"LineDirections":1,"StationId":183,"Duration":30,"DisplayOrder":6,"State":1,"ModifierId":80,"ModifyDate":"2016-08-16T20:45:39.97","Remark":""}]
     */

    private int Success;
    private String Message;
    /**
     * StationName : 励志园(起点)
     * LineDetailsId : 485
     * KgId : 33
     * LineId : 45
     * LineDirections : 1
     * StationId : 178
     * Duration : 0
     * DisplayOrder : 1
     * State : 1
     * ModifierId : 80
     * ModifyDate : 2016-08-16T20:40:53.78
     * Remark : 起始站
     */

    private List<RerurnValueBean> RerurnValue;

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

    public List<RerurnValueBean> getRerurnValue() {
        return RerurnValue;
    }

    public void setRerurnValue(List<RerurnValueBean> RerurnValue) {
        this.RerurnValue = RerurnValue;
    }

    public static class RerurnValueBean implements Serializable {
        private String StationName;
        private int LineDetailsId;
        private int KgId;
        private int LineId;
        private int LineDirections;
        private int StationId;
        private int Duration;
        private int DisplayOrder;
        private int State;
        private int ModifierId;
        private String ModifyDate;
        private String Remark;

        /**
         * 添加的两个属性  代表此站点的上下操作
         * String stationiddown
         * String stationidup
         * @return
         */
        private String stationiddown;
        private String stationidup;


        public String getStationiddown() {
            return stationiddown;
        }

        public String getStationidup() {
            return stationidup;
        }

        public void setStationiddown(String stationiddown) {
            this.stationiddown = stationiddown;
        }

        public void setStationidup(String stationidup) {
            this.stationidup = stationidup;
        }

        public String getStationName() {
            return StationName;
        }

        public void setStationName(String StationName) {
            this.StationName = StationName;
        }

        public int getLineDetailsId() {
            return LineDetailsId;
        }

        public void setLineDetailsId(int LineDetailsId) {
            this.LineDetailsId = LineDetailsId;
        }

        public int getKgId() {
            return KgId;
        }

        public void setKgId(int KgId) {
            this.KgId = KgId;
        }

        public int getLineId() {
            return LineId;
        }

        public void setLineId(int LineId) {
            this.LineId = LineId;
        }

        public int getLineDirections() {
            return LineDirections;
        }

        public void setLineDirections(int LineDirections) {
            this.LineDirections = LineDirections;
        }

        public int getStationId() {
            return StationId;
        }

        public void setStationId(int StationId) {
            this.StationId = StationId;
        }

        public int getDuration() {
            return Duration;
        }

        public void setDuration(int Duration) {
            this.Duration = Duration;
        }

        public int getDisplayOrder() {
            return DisplayOrder;
        }

        public void setDisplayOrder(int DisplayOrder) {
            this.DisplayOrder = DisplayOrder;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }

        public int getModifierId() {
            return ModifierId;
        }

        public void setModifierId(int ModifierId) {
            this.ModifierId = ModifierId;
        }

        public String getModifyDate() {
            return ModifyDate;
        }

        public void setModifyDate(String ModifyDate) {
            this.ModifyDate = ModifyDate;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }
    }
}
