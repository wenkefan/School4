package com.fwk.school4.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2016/8/10.
 */
public class GetDateTime {
    public static String getdatetime() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }

    //获取 时
    public static int getH() {
        Calendar c = Calendar.getInstance();
        int time = 0;
        time = c.get(Calendar.HOUR_OF_DAY);

        return time;
    }

    //获取 分
    public static int getM() {
        Calendar c = Calendar.getInstance();
        int time = 0;
        time = c.get(Calendar.MINUTE);

        return time;
    }

    public static String getHM(String datetime) {
        String[] time = datetime.split("T");
        String[] hm = time[1].split(":");
        String h = hm[0];
        String m = hm[1];
        return h + ":" + m;
    }

    public static String getHM2(String datetime) {
        String[] time = datetime.split("T");
        String[] hm = time[1].split(":");
        String h = hm[0];
        String m = hm[1];
        return h + m + "";
    }

    public static boolean getBoolean(String time1, String time2) {
        String[] datatime1 = getHM(time1).split(":");
        String[] datatime2 = getHM(time2).split(":");
        int a = Integer.valueOf(datatime1[0]) * 60 + Integer.valueOf(datatime1[1]);
        int b = Integer.valueOf(datatime2[0]) * 60 + Integer.valueOf(datatime2[1]);
        int yujitime = b - a;
        int c = getH() * 60 + getM();
        int shijitime = c - a;
        return shijitime >= 0 && shijitime <= yujitime ? true : false;
    }

    public static String getYJTime(int time) {
        int H = getH();
        int M = getM() + time;
        if (M >= 60) {
            H = H + 1;
            M = M - 60;
        }
        return M < 10 ? H + ":0" + M : H + ":" + M;
    }

    public static int getFacheTime() {
        return getH() * 60 + getM();
    }

    public static int getDaozhanTime() {
        return getH() * 60 + getM();
    }

    public static boolean isTodaozhan(int fachetime, int daozhantime, int jiangetime) {
        if (daozhantime - fachetime >= jiangetime) {
            return true;
        } else {
            return false;
        }
    }

    public static int CompareMax(String time1) {
        String[] datatime1 = getHM(time1).split(":");
        int a = Integer.valueOf(datatime1[0]) * 60 + Integer.valueOf(datatime1[1]);
        return a;
    }
}
