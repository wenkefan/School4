package com.fwk.school4.weight;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.fwk.school4.constant.Keyword;
import com.fwk.school4.listener.FacheListener;
import com.fwk.school4.model.BanciBean;
import com.fwk.school4.ui.Jie.JieStationMapActivity;
import com.fwk.school4.ui.MainActivity;
import com.fwk.school4.ui.Song.SongStationMapActivity;
import com.fwk.school4.utils.SharedPreferencesUtils;

/**
 * Created by fanwenke on 16/12/14.
 */

public class MainDialog {
    static FacheListener listener;
    public static void setBackListener(FacheListener listeners){
        listener = listeners;
    }
    /**
     * 是本人的班次  点击时的弹窗
     * @param context
     * @param name
     * @param fangxiang
     * @param position
     */
    public static void ShowJRBanci(final Activity context, String name, final int fangxiang,final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示").setMessage("\"" +name + "\"" + "是否发车");
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent;
                if (fangxiang == 1) {
                    //接幼儿
                    intent = new Intent(context, JieStationMapActivity.class);
                    intent.putExtra(Keyword.POTIONIT, position);

                } else {
                    //送幼儿
                    intent = new Intent(context, SongStationMapActivity.class);
                    intent.putExtra(Keyword.POTIONIT, position);

                }
                listener.BackListener(intent);
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    /**
     * 点击别人的班次时的弹窗
     */
    public static void ShowDLBanci(final Activity context,String name,String teachername,final int fangxiang,final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("警告").setMessage(name + "的随车老师是：" + teachername+"\n"+"是否代理发车");
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent;
                if (fangxiang == 1) {
                    //接幼儿
                    intent = new Intent(context, JieStationMapActivity.class);
                    intent.putExtra(Keyword.POTIONIT, position);

                } else {
                    //送幼儿
                    intent = new Intent(context, SongStationMapActivity.class);
                    intent.putExtra(Keyword.POTIONIT, position);

                }

                listener.BackListener(intent);
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    /**
     * 推出重新进入时
     */
    public static void Beagin(final MainActivity context, BanciBean.RerurnValueBean bean){
        String name = bean.getBusScheduleName();
        final int fangxiang = bean.getAttendanceDirections();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle("警告").setMessage(name + "正在运行中...");
        builder.setNegativeButton("重新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferencesUtils sp = new SharedPreferencesUtils();
                context.requestBanci();
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("继续", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent;
                if (fangxiang == 1) {
                    //接幼儿
                    intent = new Intent(context, JieStationMapActivity.class);

                } else {
                    //送幼儿
                    intent = new Intent(context, SongStationMapActivity.class);
                }
                intent.putExtra(Keyword.POTIONIT, -1);
                context.startActivity(intent);
                context.finish();
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}
