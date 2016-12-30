package com.fwk.school4.network;

/**
 * Created by Administrator on 2016/7/21.
 */
public class HTTPURL {
    //private static final String API_URL = "http://192.168.1.168:8091/WebServices/MobileAttendanceService.ashx?Option=";
    private static final String API_URL = "http://manage.youery.com/WebServices/MobileAttendanceService.ashx?";
    //    private static final String API_URL = "http://192.168.1.116:8083/WebServices/MobileAttendanceService.ashx?";
//    private static final String API_URL = "http://192.168.1.148:8081/WebServices/MobileAttendanceService.ashx?";

    /**
     * 获取基础信息（线路列表、巴士列表、司机列表）
     */
    public static final String API_BASIC_INFO = API_URL + "BasicInfo&kgid=%1$s";
    /**
     * 获取线路信息（包括幼儿信息）
     */
    public static final String API_LOAD_ATTENDANCE = API_URL + "LoadAttendance&kgId=%1$s&lineId=%2$s&teacherId=%3$s&busId=%4$s&driverId=%5$s&shuttleType=%6$s";
    /**
     * 获取派车单位信息
     */
    public static final String API_START_ATTENDANCE = API_URL + "Departure&kgId=%1$s&lineId=%2$s&teacherId=%3$s&busId=%4$s&driverId=%5$s&shuttleType=%6$s";
    /**
     * 上传考勤记录
     */
    public static final String API_UPLOAD_ATTENDANCE_RECORD = API_URL + "AttendanceRecord&kgId=%1$s&childId=%2$s&shuttleType=%3$s"
            + "&askForLeavetype=%4$s&currentTime=%5$s&explain=%6$s";
    /**
     * 审核
     */
    public static final String API_AUDIT = API_URL + "Audit&kgId=%1$s&busOrderId=%2$s&userId=%3$s&workCardNo=%4$s&auditTime=%5$s";
    /**
     * 发送晚点通知
     */
    public static final String API_SEND_NOTIFICATION = API_URL + "SendNotification&kgId=%1$s&lineId=%2$s&stationId=%3$s&Content=%4$s&shuttleType=%5$s";

    /**
     * 登陆
     */
    public static final String API_LOGIN = API_URL + "Option=Login&userName=%1$s&userPwd=%2$s";

    //获取园所信息
    public static final String API_YUANSUO = API_URL + "Option=GetKindergartenByOrgId&Id=";
    //获取线路信息
    public static final String API_XIANLU = API_URL + "Option=GetLineInfoByOrgId&Id=";
    //获取老师信息
    public static final String API_TEACHER = API_URL + "Option=GetAttendanceMobileUserByOrgId&Id=";
    //获取校车信息
    public static final String API_XIAOCHE = API_URL + "Option=GetSchoolBusByOrgId&Id=";
    //获取司机信息
    public static final String API_SIJI = API_URL + "Option=GetDriverByOrgId&Id=";
    //班次
    public static final String API_BANCI = API_URL + "Option=GetBusScheduleListByOrgId&Id=%1$d";
    //站点
    public static final String API_ZHANDIAN = API_URL + "Option=GetStationInfo&Id=%1$s&shuttleType=%2$s&lineId=%3$s";
    // 54.根据班次获取幼儿
    public static final String API_CHILD = API_URL + "Option=GetChildsByBusScheduleId&Id=%1$s&BusScheduleId=%2$s&shuttleType=%3$s";//&BusScheduleId=4&shuttleType=2（接送）


    //发车、结束
    /**
     * 发车字段为：班次编号、kgid、发车时间、类型(1发车、2停车)
     * 停车字段为：派车单号、kgid、发车时间、类型(1发车、2停车)
     */
    public static final String API_OPEN = API_URL + "Option=AddBusOrderBySchedule&busScheduleId=%1$s&kgId=%2$s&tTime=%3$s&exerciseType=%4$s";

    //中间过程的发车、到站
    /**
     * kgid /  站点、派车单、行驶类型(2发车、1到站)、时间
     */
    public static final String API_PROCESS = API_URL + "Option=AddStopStation&kgId=%1$s&StationId=%2$s&BusOrderId=%3$s&Exercisetype=%4$s&datetime=%5$s";

    //上下车记录
    /**
     * 字段：派车单号、幼儿编号、站点、时间、状态、kgid、上下车类型（1、上车；2、下车）
     */
    public static final String API_STUDENT_OPEN_DOWN = API_URL + "Option=GetChildsSwipeCardRecord&busOrderId=%1$s&childId=%2$s&stationNum=%3$s&takeTime=%4$s&status=%5$s&orgId=%6$s&takeType=%7$s";

}
