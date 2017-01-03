package com.fwk.school4.weight;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.fwk.school4.constant.Keyword;
import com.fwk.school4.model.BanciBean;
import com.fwk.school4.ui.Jie.JieStationMapActivity;
import com.fwk.school4.ui.MainActivity;
import com.fwk.school4.ui.Song.SongStationMapActivity;
import com.fwk.school4.utils.LogUtils;
import com.fwk.school4.utils.SharedPreferencesUtils;

/**
 * Created by fanwenke on 16/12/14.
 */

public class MainDialog {
    /**
     * 是本人的班次  点击时的弹窗
     * @param context
     * @param name
     * @param fangxiang
     * @param position
     */
    public static void ShowJRBanci(final Activity context, String name, final int fangxiang,final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示").setMessage("是否发车班次：" + name);
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

                context.startActivity(intent);
                context.finish();
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
        builder.setTitle("警告").setMessage("你不是班次：" + name + "的随车老师\n是否代理" + teachername + "老师");
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

                context.startActivity(intent);
                context.finish();
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
        builder.setTitle("警告").setMessage("班次：" + name + "正在运行中...");
        builder.setNegativeButton("重新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferencesUtils sp = new SharedPreferencesUtils();
                sp.setboolean(Keyword.BEGIN,false);
                context.requestBanci();
                dialogInterface.dismiss();
                sp.saveToShared(Keyword.GETSJTIME,null);
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

                context.startActivity(intent);
                context.finish();
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}
