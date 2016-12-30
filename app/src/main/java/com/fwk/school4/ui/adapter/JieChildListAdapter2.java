package com.fwk.school4.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fwk.school4.MyApp;
import com.fwk.school4.R;
import com.fwk.school4.constant.Keyword;
import com.fwk.school4.model.ChildBean;
import com.fwk.school4.model.StaBean;
import com.fwk.school4.utils.LogUtils;
import com.fwk.school4.utils.SharedPreferencesUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by fanwenke on 16/12/23.
 */

public class JieChildListAdapter2 extends BaseRecyclerAdapter {
    private Context context;
    private Map<String, List<ChildBean.RerurnValueBean>> map;
    private SharedPreferencesUtils sp = new SharedPreferencesUtils();
    private List<StaBean> staBeen;
    private LinearLayoutManager manager;
    private ChildRecyAdapter adapter;
    private List<ChildBean.RerurnValueBean> list;

    public void getData(){
        map = (Map<String, List<ChildBean.RerurnValueBean>>) sp.queryForSharedToObject(Keyword.MAPLIST);
        staBeen = (List<StaBean>) sp.queryForSharedToObject(Keyword.SELECTSTA);
    }


    public JieChildListAdapter2() {
        map = (Map<String, List<ChildBean.RerurnValueBean>>) sp.queryForSharedToObject(Keyword.MAPLIST);
        staBeen = (List<StaBean>) sp.queryForSharedToObject(Keyword.SELECTSTA);
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolde(LayoutInflater.from(MyApp.getContext()).inflate(R.layout.recyadapter, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, final int position) {
        if (holder instanceof ViewHolde) {
            ViewHolde viewHolde = (ViewHolde) holder;
            viewHolde.tv.setText(staBeen.get(position).getName());
            list = map.get(staBeen.get(position).getStrid());
            manager = new LinearLayoutManager(context);
            viewHolde.rv.setHasFixedSize(true);
            viewHolde.rv.setLayoutManager(manager);
            adapter = new ChildRecyAdapter(list);
            viewHolde.rv.setAdapter(adapter);
            adapter.setOnItemListener(new OnItemListener() {
                @Override
                public void setOnItemListener(int itme, ClickableViewHolder holder) {
                    onItemAdapterListener.setOnItemListener(staBeen.get(position), itme);
                }
            });
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return staBeen.size();
    }


    public class ViewHolde extends ClickableViewHolder {

        private TextView tv;
        private RecyclerView rv;

        public ViewHolde(View itemView) {
            super(itemView);
            tv = $(R.id.tv_station);
            rv = $(R.id.rv_childlist);
        }
    }

    public interface OnItemAdapterListener {
        void setOnItemListener(StaBean staid, int position);
    }

    private OnItemAdapterListener onItemAdapterListener;

    public void setOnItemAdapterListener(OnItemAdapterListener onItemAdapterListener) {
        this.onItemAdapterListener = onItemAdapterListener;
    }
}
