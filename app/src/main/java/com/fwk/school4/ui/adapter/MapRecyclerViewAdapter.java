package com.fwk.school4.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fwk.school4.MyApp;
import com.fwk.school4.R;
import com.fwk.school4.constant.Keyword;
import com.fwk.school4.listener.DaoZhanListener;
import com.fwk.school4.model.StationBean;
import com.fwk.school4.utils.SharedPreferencesUtils;

import java.util.List;

/**
 * Created by fanwenke on 16/12/7.
 */

public class MapRecyclerViewAdapter extends BaseRecyclerAdapter implements View.OnClickListener {

    private Context mContext;
    private SharedPreferencesUtils sp;
    private List<StationBean.RerurnValueBean> list;
    private List<Integer> childCount;
    private DisplayMetrics display;
    private int stationPosition;

    DaoZhanListener listener;

    public void setOnClickListener(DaoZhanListener listener){
        this.listener = listener;
    }

    public MapRecyclerViewAdapter(DisplayMetrics display, int stationPosition) {
        this.display = display;
        this.stationPosition = stationPosition;
        sp = new SharedPreferencesUtils();
        list = (List<StationBean.RerurnValueBean>) sp.queryForSharedToObject(Keyword.SP_STATION_LIST);
        childCount = (List<Integer>) sp.queryForSharedToObject(Keyword.CHILDCOUNT);
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = View.inflate(mContext, R.layout.station_map_adapter, null);
        return new MapViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        if (holder instanceof MapViewHolder) {
            String str = String.format(mContext.getString(R.string.station_count), 0, childCount.get(position));
            String str1 = String.format(mContext.getString(R.string.station_shangcherenshu),0,2);
            MapViewHolder viewHolder = (MapViewHolder) holder;
            setLayoutParams(viewHolder, 0.95f);

            viewHolder.name.setText(list.get(position).getStationName());
            viewHolder.daozhan.setVisibility(View.GONE);
            viewHolder.count.setText(str);
            viewHolder.daozhan.setOnClickListener(this);
            viewHolder.ysc.setText(str1);
            viewHolder.yxc.setText(str1);
            AnimationSet set = new AnimationSet(true);
            AlphaAnimation animation = new AlphaAnimation(1, 0.2f);
            animation.setRepeatCount(Animation.INFINITE);
            animation.setRepeatMode(Animation.REVERSE);
            animation.setDuration(1000);
            set.addAnimation(animation);
            if (stationPosition == position){
                viewHolder.ring.setAnimation(set);
            }
            if (position == 0) {
                viewHolder.start.setVisibility(View.GONE);
            }
            if (position == getItemCount() - 1) {
                viewHolder.end.setVisibility(View.GONE);
            }
            if (position <= stationPosition) {
                viewHolder.ring.setImageResource(R.drawable.ring2);
                viewHolder.start.setBackgroundColor(0xff669900);
            }
            if (position < stationPosition){
                viewHolder.end.setBackgroundColor(0xff669900);
            }
            if (stationPosition == position){
                viewHolder.daozhan.setVisibility(View.VISIBLE);
            }
            viewHolder.sjsj.setText("10:30");
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public void onClick(View view) {

        listener.OnClickListener(stationPosition);
    }

    public class MapViewHolder extends ClickableViewHolder {

        private View start, end;
        private ImageView ring;
        private TextView name, count, ysc, yxc, sjsj;
        private CardView cardView;
        private RelativeLayout left;
        private Button daozhan;

        public MapViewHolder(View itemView) {
            super(itemView);
            start = $(R.id.view_start);
            end = $(R.id.view_end);
            ring = $(R.id.ring);
            name = $(R.id.tx_name);
            cardView = $(R.id.cardview);
            count = $(R.id.tv_count);
            left = $(R.id.rl_left);
            daozhan = $(R.id.btn_daozhan);

            ysc = $(R.id.tv_shangcherenshu);
            yxc = $(R.id.tv_xiacherenshu);
            sjsj = $(R.id.tv_sjsj);
        }

    }

    private void setLayoutParams(MapViewHolder viewHolder, float widths) {
        //根据屏幕设置cardView的宽度
        int width = display.widthPixels;
        ViewGroup.LayoutParams layoutParams = viewHolder.cardView.getLayoutParams();
        layoutParams.width = (int) ((width - dp2px(50)) * widths);
        viewHolder.cardView.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParam = viewHolder.name.getLayoutParams();
        layoutParam.width = layoutParams.width / 3;
        viewHolder.name.setLayoutParams(layoutParam);
    }
    public void setPostion(int postion){
        stationPosition = postion;
        notifyDataSetChanged();
    }
    public static int dp2px(float dpValue) {
        final float scale = MyApp.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
