package com.fwk.school4.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fwk.school4.R;
import com.fwk.school4.model.ChildBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanwenke on 16/12/26.
 */

public class ChildRecyAdapter extends BaseRecyclerAdapter {
    private Context context;
    private List<ChildBean.RerurnValueBean> list = new ArrayList<>();


    public ChildRecyAdapter(List<ChildBean.RerurnValueBean> list) {
        this.list = list;
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ChildViewHolde(LayoutInflater.from(parent.getContext()).inflate(R.layout.childrecyadapter,parent,false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        if (holder instanceof ChildViewHolde){
            ChildViewHolde holde = (ChildViewHolde) holder;
            holde.tv.setText(list.get(position).getChildName());
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ChildViewHolde extends ClickableViewHolder {

        private TextView tv;

        public ChildViewHolde(View itemView) {
            super(itemView);
            tv = $(R.id.tv_child_name);
        }
    }
}
