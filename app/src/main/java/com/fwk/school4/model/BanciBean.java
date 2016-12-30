package com.fwk.school4.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fanwenke on 16/10/31.
 */

public class BanciBean implements Serializable {

    /**
     * Success : 10000
     * Message :
     * RerurnValue : [{"DriverName":"白萍","TeacherName":"杨菲洋","TeacherTelNumber":"15010552659","BusLicensePlate":"京ＮHYLK002","BusLineName":"十号线","ParentId":0,"BusScheduleId":9,"BusScheduleName":"一班次","BusId":5,"TeacherId":375,"LineId":45,"AttendanceDirections":1,"SendStartTime":"2016-10-27T13:00:00","SendEndTime":"2016-10-27T17:26:00","DriverId":2477,"IsPublic":2,"Status":1,"ModifierId":80,"ModifyDate":"2016-10-27T13:31:02.313","Remark":""}]
     */

    private int Success;
    private String Message;
    /**
     * DriverName : 白萍
     * TeacherName : 杨菲洋
     * TeacherTelNumber : 15010552659
     * BusLicensePlate : 京ＮHYLK002
     * BusLineName : 十号线
     * ParentId : 0
     * BusScheduleId : 9
     * BusScheduleName : 一班次
     * BusId : 5
     * TeacherId : 375
     * LineId : 45
     * AttendanceDirections : 1  考勤放心 1接  2送
     * SendStartTime : 2016-10-27T13:00:00
     * SendEndTime : 2016-10-27T17:26:00
     * DriverId : 2477
     * IsPublic : 2
     * Status : 1
     * ModifierId : 80
     * ModifyDate : 2016-10-27T13:31:02.313
     * Remark :
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
        private String DriverName;
        private String TeacherName;
        private String TeacherTelNumber;
        private String BusLicensePlate;
        private String BusLineName;
        private int ParentId;
        private int BusScheduleId;
        private String BusScheduleName;
        private int BusId;
        private int TeacherId;
        private int LineId;
        private int AttendanceDirections;
        private String SendStartTime;
        private String SendEndTime;
        private int DriverId;
        private int IsPublic;
        private int Status;
        private int ModifierId;
        private String ModifyDate;
        private String Remark;
        private boolean original;

        public boolean getOriginal() {
            return original;
        }

        public void setOriginal(boolean original) {
            this.original = original;
        }

        public String getDriverName() {
            return DriverName;
        }

        public void setDriverName(String DriverName) {
            this.DriverName = DriverName;
        }

        public String getTeacherName() {
            return TeacherName;
        }

        public void setTeacherName(String TeacherName) {
            this.TeacherName = TeacherName;
        }

        public String getTeacherTelNumber() {
            return TeacherTelNumber;
        }

        public void setTeacherTelNumber(String TeacherTelNumber) {
            this.TeacherTelNumber = TeacherTelNumber;
        }

        public String getBusLicensePlate() {
            return BusLicensePlate;
        }

        public void setBusLicensePlate(String BusLicensePlate) {
            this.BusLicensePlate = BusLicensePlate;
        }

        public String getBusLineName() {
            return BusLineName;
        }

        public void setBusLineName(String BusLineName) {
            this.BusLineName = BusLineName;
        }

        public int getParentId() {
            return ParentId;
        }

        public void setParentId(int ParentId) {
            this.ParentId = ParentId;
        }

        public int getBusScheduleId() {
            return BusScheduleId;
        }

        public void setBusScheduleId(int BusScheduleId) {
            this.BusScheduleId = BusScheduleId;
        }

        public String getBusScheduleName() {
            return BusScheduleName;
        }

        public void setBusScheduleName(String BusScheduleName) {
            this.BusScheduleName = BusScheduleName;
        }

        public int getBusId() {
            return BusId;
        }

        public void setBusId(int BusId) {
            this.BusId = BusId;
        }

        public int getTeacherId() {
            return TeacherId;
        }

        public void setTeacherId(int TeacherId) {
            this.TeacherId = TeacherId;
        }

        public int getLineId() {
            return LineId;
        }

        public void setLineId(int LineId) {
            this.LineId = LineId;
        }

        public int getAttendanceDirections() {
            return AttendanceDirections;
        }

        public void setAttendanceDirections(int AttendanceDirections) {
            this.AttendanceDirections = AttendanceDirections;
        }

        public String getSendStartTime() {
            return SendStartTime;
        }

        public void setSendStartTime(String SendStartTime) {
            this.SendStartTime = SendStartTime;
        }

        public String getSendEndTime() {
            return SendEndTime;
        }

        public void setSendEndTime(String SendEndTime) {
            this.SendEndTime = SendEndTime;
        }

        public int getDriverId() {
            return DriverId;
        }

        public void setDriverId(int DriverId) {
            this.DriverId = DriverId;
        }

        public int getIsPublic() {
            return IsPublic;
        }

        public void setIsPublic(int IsPublic) {
            this.IsPublic = IsPublic;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
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
