package com.fwk.school4.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fwk.school4.MyApp;
import com.fwk.school4.R;
import com.fwk.school4.constant.Keyword;
import com.fwk.school4.constant.SpLogin;
import com.fwk.school4.model.BanciBean;
import com.fwk.school4.utils.SharedPreferencesUtils;

import java.util.List;

/**
 * Created by fanwenke on 16/12/5.
 */

public class MainRecyclerViewAdapter extends BaseRecyclerAdapter {



    private SharedPreferencesUtils sp;
    private List<BanciBean.RerurnValueBean> list;
    private Context mContext;

    public MainRecyclerViewAdapter() {
        sp = new SharedPreferencesUtils();
        list = (List<BanciBean.RerurnValueBean>) sp.queryForSharedToObject(Keyword.SP_BANCI_LIST);
    }

    @Override
    public BanciViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new BanciViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_main_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        if (holder instanceof BanciViewHolder) {
            String time[] = list.get(position).getSendStartTime().split("T");
            String times[] = time[1].split(":");
            BanciViewHolder banciHolder = (BanciViewHolder) holder;
            banciHolder.mBanciName.setText("班次："+list.get(position).getBusScheduleName());
            banciHolder.mBanciLuxian.setText("线路："+list.get(position).getBusLineName());
            banciHolder.mBanciTime.setText("时间："+ times[0] + ":" + times[1]);
            banciHolder.mBanciTeacher.setText("老师：" + list.get(position).getTeacherName());
            banciHolder.mBanciSj.setText("司机：" + list.get(position).getDriverName());
            if (list.get(position).getTeacherId() == SpLogin.getWorkerExtensionId()) {
                banciHolder.mBanciLuxian.setTextColor(mContext.getResources().getColor(R.color.black));
                banciHolder.mBanciName.setTextColor(mContext.getResources().getColor(R.color.black));
                banciHolder.mBanciTime.setTextColor(mContext.getResources().getColor(R.color.black));
                banciHolder.mBanciTeacher.setTextColor(mContext.getResources().getColor(R.color.black));
                banciHolder.mBanciSj.setTextColor(mContext.getResources().getColor(R.color.black));
            }
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    public class BanciViewHolder extends ClickableViewHolder {

        public TextView mBanciName;
        public TextView mBanciLuxian;
        public TextView mBanciTime;
        public TextView mBanciTeacher;
        public TextView mBanciSj;

        public BanciViewHolder(View itemView) {
            super(itemView);
            mBanciName = $(R.id.item_main_bcnmae);
            mBanciLuxian = $(R.id.item_main_luxian);
            mBanciTime = $(R.id.item_main_time);
            mBanciTeacher = $(R.id.item_main_techer);
            mBanciSj = $(R.id.item_main_sj);
        }
    }
}
