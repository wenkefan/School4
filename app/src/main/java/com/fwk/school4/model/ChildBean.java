package com.fwk.school4.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fanwenke on 16/10/31.
 */

public class ChildBean implements Serializable {

    /**
     * Success : 10000
     * Message :
     * RerurnValue : [{"OrganizationId":33,"ChildId":2544,"ChildName":"何诗琪","FatherName":"","FatherPhone":"","MotherName":"","MotherPhone":"","TeacherName":"李晨","Teacherphone":"13911100000","SACardNo":"7966CC2A","ConnectLineId":45,"SendLineId":45,"ConnectStation":181,"ConnectEndStation":183,"SendStartStation":182,"SendStation":182},{"OrganizationId":33,"ChildId":2553,"ChildName":"徐诺妍","FatherName":"","FatherPhone":"","MotherName":"","MotherPhone":"","TeacherName":"李晨","Teacherphone":"13911100000","SACardNo":"9486E054","ConnectLineId":45,"SendLineId":45,"ConnectStation":179,"ConnectEndStation":183,"SendStartStation":179,"SendStation":179},{"OrganizationId":33,"ChildId":2764,"ChildName":"鲁婉莹","FatherName":"","FatherPhone":"","MotherName":"","MotherPhone":"","TeacherName":"老师","Teacherphone":"13254784521","SACardNo":"E00401504F4983FE","ConnectLineId":45,"SendLineId":45,"ConnectStation":182,"ConnectEndStation":183,"SendStartStation":182,"SendStation":182},{"OrganizationId":33,"ChildId":2765,"ChildName":"王君溪","FatherName":"","FatherPhone":"","MotherName":"","MotherPhone":"","TeacherName":"老师","Teacherphone":"13254784521","SACardNo":"E00401504F49F6AE","ConnectLineId":45,"SendLineId":45,"ConnectStation":180,"ConnectEndStation":183,"SendStartStation":180,"SendStation":180},{"OrganizationId":33,"ChildId":2766,"ChildName":"王耀东","FatherName":"","FatherPhone":"","MotherName":"","MotherPhone":"","TeacherName":"老师","Teacherphone":"13254784521","SACardNo":"E00401504F48EF1C","ConnectLineId":45,"SendLineId":45,"ConnectStation":180,"ConnectEndStation":183,"SendStartStation":180,"SendStation":180},{"OrganizationId":33,"ChildId":2767,"ChildName":"樊鑫烁","FatherName":"","FatherPhone":"","MotherName":"","MotherPhone":"","TeacherName":"老师","Teacherphone":"13254784521","SACardNo":"E00401504F48ECB0","ConnectLineId":45,"SendLineId":45,"ConnectStation":181,"ConnectEndStation":183,"SendStartStation":181,"SendStation":181},{"OrganizationId":33,"ChildId":2768,"ChildName":"廖星哲","FatherName":"","FatherPhone":"","MotherName":"","MotherPhone":"","TeacherName":"老师","Teacherphone":"13254784521","SACardNo":"E00401504F48BF62","ConnectLineId":45,"SendLineId":45,"ConnectStation":181,"ConnectEndStation":183,"SendStartStation":181,"SendStation":181},{"OrganizationId":33,"ChildId":2555,"ChildName":"赵宇博","FatherName":"","FatherPhone":"","MotherName":"","MotherPhone":"","TeacherName":"李晨","Teacherphone":"13911100000","SACardNo":"","ConnectLineId":45,"SendLineId":45,"ConnectStation":179,"ConnectEndStation":183,"SendStartStation":179,"SendStation":179}]
     */

    private int Success;
    private String Message;
    /**
     * OrganizationId : 33
     * ChildId : 2544
     * ChildName : 何诗琪
     * FatherName :
     * FatherPhone :
     * MotherName :
     * MotherPhone :
     * TeacherName : 李晨
     * Teacherphone : 13911100000
     * SACardNo : 7966CC2A
     * ConnectLineId : 45
     * SendLineId : 45
     * ConnectStation : 181
     * ConnectEndStation : 183
     * SendStartStation : 182
     * SendStation : 182
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
        private int OrganizationId;
        private int ChildId;
        private String ChildName;
        private String FatherName;
        private String FatherPhone;
        private String MotherName;
        private String MotherPhone;
        private String TeacherName;
        private String Teacherphone;
        private String SACardNo;
        private int ConnectLineId;
        private int SendLineId;
        private int ConnectStation;
        private int ConnectEndStation;
        private int SendStartStation;
        private int SendStation;
        private int Selectid;//症状
        private boolean isOperation;//是否以操作
        private int isDU;//是上／下车

        public int getIsDU() {
            return isDU;
        }

        public void setIsDU(int isDU) {
            this.isDU = isDU;
        }

        public boolean getisOperation() {
            return isOperation;
        }

        public void setOperation(boolean operation) {
            isOperation = operation;
        }


        public int getSelectid() {
            return Selectid;
        }

        public void setSelectid(int selectid) {
            this.Selectid = selectid;
        }

        public int getOrganizationId() {
            return OrganizationId;
        }

        public void setOrganizationId(int OrganizationId) {
            this.OrganizationId = OrganizationId;
        }

        public int getChildId() {
            return ChildId;
        }

        public void setChildId(int ChildId) {
            this.ChildId = ChildId;
        }

        public String getChildName() {
            return ChildName;
        }

        public void setChildName(String ChildName) {
            this.ChildName = ChildName;
        }

        public String getFatherName() {
            return FatherName;
        }

        public void setFatherName(String FatherName) {
            this.FatherName = FatherName;
        }

        public String getFatherPhone() {
            return FatherPhone;
        }

        public void setFatherPhone(String FatherPhone) {
            this.FatherPhone = FatherPhone;
        }

        public String getMotherName() {
            return MotherName;
        }

        public void setMotherName(String MotherName) {
            this.MotherName = MotherName;
        }

        public String getMotherPhone() {
            return MotherPhone;
        }

        public void setMotherPhone(String MotherPhone) {
            this.MotherPhone = MotherPhone;
        }

        public String getTeacherName() {
            return TeacherName;
        }

        public void setTeacherName(String TeacherName) {
            this.TeacherName = TeacherName;
        }

        public String getTeacherphone() {
            return Teacherphone;
        }

        public void setTeacherphone(String Teacherphone) {
            this.Teacherphone = Teacherphone;
        }

        public String getSACardNo() {
            return SACardNo;
        }

        public void setSACardNo(String SACardNo) {
            this.SACardNo = SACardNo;
        }

        public int getConnectLineId() {
            return ConnectLineId;
        }

        public void setConnectLineId(int ConnectLineId) {
            this.ConnectLineId = ConnectLineId;
        }

        public int getSendLineId() {
            return SendLineId;
        }

        public void setSendLineId(int SendLineId) {
            this.SendLineId = SendLineId;
        }

        public int getConnectStation() {
            return ConnectStation;
        }

        public void setConnectStation(int ConnectStation) {
            this.ConnectStation = ConnectStation;
        }

        public int getConnectEndStation() {
            return ConnectEndStation;
        }

        public void setConnectEndStation(int ConnectEndStation) {
            this.ConnectEndStation = ConnectEndStation;
        }

        public int getSendStartStation() {
            return SendStartStation;
        }

        public void setSendStartStation(int SendStartStation) {
            this.SendStartStation = SendStartStation;
        }

        public int getSendStation() {
            return SendStation;
        }

        public void setSendStation(int SendStation) {
            this.SendStation = SendStation;
        }

        @Override
        public String toString() {
            return "RerurnValueBean{" +
                    "OrganizationId=" + OrganizationId +
                    ", ChildId=" + ChildId +
                    ", ChildName='" + ChildName + '\'' +
                    ", FatherName='" + FatherName + '\'' +
                    ", FatherPhone='" + FatherPhone + '\'' +
                    ", MotherName='" + MotherName + '\'' +
                    ", MotherPhone='" + MotherPhone + '\'' +
                    ", TeacherName='" + TeacherName + '\'' +
                    ", Teacherphone='" + Teacherphone + '\'' +
                    ", SACardNo='" + SACardNo + '\'' +
                    ", ConnectLineId=" + ConnectLineId +
                    ", SendLineId=" + SendLineId +
                    ", ConnectStation=" + ConnectStation +
                    ", ConnectEndStation=" + ConnectEndStation +
                    ", SendStartStation=" + SendStartStation +
                    ", SendStation=" + SendStation +
                    ", Selectid=" + Selectid +
                    ", isOperation=" + isOperation +
                    ", isDU=" + isDU +
                    '}';
        }
    }
}
