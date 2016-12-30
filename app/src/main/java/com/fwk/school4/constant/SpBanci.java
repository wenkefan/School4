package com.fwk.school4.constant;

import android.content.SharedPreferences;

/**
 * Created by fanwenke on 16/8/19.
 */
public class SpBanci extends Bean {
    public static void save(int BusScheduleId, int LineId, int AttendanceDirections){
        SharedPreferences.Editor editor = SP.edit();
        editor.putInt(Keyword.SP_BANCI_ID,BusScheduleId);
        editor.putInt(Keyword.SP_XIANLU_ID,LineId);
        editor.putInt(Keyword.SP_ATTENDANCEDIRECTIONS,AttendanceDirections);
        editor.commit();
    }
    //获取班次ID
    public static int getBanciId(){
        return SP.getInt(Keyword.SP_BANCI_ID,0);
    }
    //获取线路ID
    public static int getXianluId(){
        return SP.getInt(Keyword.SP_XIANLU_ID,0);
    }
    //获取线路方向
    public static int getAttendancedirections(){
        return SP.getInt(Keyword.SP_ATTENDANCEDIRECTIONS,0);
    }
}
