package com.fwk.school4.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fwk.school4.R;

/**
 * Created by fanwenke on 16/12/27.
 */

public class ShangCheRecyclerAdapter extends BaseRecyclerAdapter {
    private Context context;
    private String[] askForLeaveStatus = new String[]{"已上车（已刷卡、未带卡）", "病假", "事假", "家长接送"};
    private int item = 0;
    public ShangCheRecyclerAdapter(int selectid) {
        if (selectid > 0) {
            this.item = selectid;
        }
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ShoudongViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.shoudong_recylcer_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        if (holder instanceof ShoudongViewHolder){
            ShoudongViewHolder holde = (ShoudongViewHolder) holder;
            holde.adrio.setImageResource(R.mipmap.btn_radio_y);
            holde.adrio.setVisibility(View.INVISIBLE);
            holde.type.setText(askForLeaveStatus[position]);
            if (item == position){
                holde.adrio.setVisibility(View.VISIBLE);
            }
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return askForLeaveStatus.length;
    }

    public class ShoudongViewHolder extends ClickableViewHolder {
        private ImageView adrio;
        private TextView type;

        public ShoudongViewHolder(View itemView) {
            super(itemView);
            adrio = $(R.id.iv_radio);
            type = $(R.id.tv_type);
        }
    }
    public void setItme(int position){
        item = position;
    }
}
