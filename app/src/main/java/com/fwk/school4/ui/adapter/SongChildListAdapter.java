package com.fwk.school4.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.fwk.school4.R;
import com.fwk.school4.constant.Keyword;
import com.fwk.school4.listener.OnItemClickListener;
import com.fwk.school4.model.ChildBean;
import com.fwk.school4.utils.LogUtils;
import com.fwk.school4.utils.SharedPreferencesUtils;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by fanwenke on 16/12/8.
 */

public class SongChildListAdapter extends BaseAdapter implements StickyListHeadersAdapter, SectionIndexer {

    private SharedPreferencesUtils sp;
    private List<ChildBean.RerurnValueBean> childList;
    private List<String> stationName;//站点名称

    private List<Integer> headerLocationList;//头位置
    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }
    private OnItemClickListener onItemClickListener = null;

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        childList = (List<ChildBean.RerurnValueBean>) sp.queryForSharedToObject(Keyword.CHILDGROUP);
    }

    public SongChildListAdapter() {
        sp = new SharedPreferencesUtils();
        childList = (List<ChildBean.RerurnValueBean>) sp.queryForSharedToObject(Keyword.CHILDGROUP);
        headerLocationList = (List<Integer>) sp.queryForSharedToObject(Keyword.HEADERLOCATION);
        stationName = (List<String>) sp.queryForSharedToObject(Keyword.STATIONNAEM);
    }
    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder = null;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_station_info, null);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.btnChangeTheTimeOfArrival = (Button) convertView.findViewById(R.id.btn_change_the_time_of_arrival);
            holder.diTop = convertView.findViewById(R.id.di_top);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        holder.tvName.setText(stationName.get(position));
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return getSectionForPosition(position);
    }

    @Override
    public int getCount() {
        return childList.size();
    }

    @Override
    public Object getItem(int i) {
        return childList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_children_info, null);
            viewHolder.tvChildName = (TextView) view.findViewById(R.id.tv_name);
            viewHolder.imIcon = (ImageView) view.findViewById(R.id.iv_status);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvChildName.setText(childList.get(position).getChildName());
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if( onItemClickListener!=null ) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
        if (childList.get(position).getSelectid() == 0) {
            viewHolder.imIcon.setBackgroundResource(R.mipmap.ic_edit_children);
        } else {
            switch (childList.get(position).getSelectid()) {
                case 1:
                    viewHolder.imIcon.setBackgroundResource(R.mipmap.ic_children_status_get_car);
                    break;
                case 2:
                    viewHolder.imIcon.setBackgroundResource(R.mipmap.ic_sick_leave);
                    break;
                case 3:
                    viewHolder.imIcon.setBackgroundResource(R.mipmap.ic_private_affair_leave);
                    break;
                case 4:
                    viewHolder.imIcon.setBackgroundResource(R.mipmap.ic_parents_escorts_a_child);
                    break;
                default:
                    break;
            }
        }
        return view;
    }

    @Override
    public Object[] getSections() {
        return stationName.toArray();
    }

    @Override
    public int getPositionForSection(int i) {
        return headerLocationList.get(i);
    }

    @Override
    public int getSectionForPosition(int position) {
        for (int i = 0; i < headerLocationList.size(); i++) {
            if (position < headerLocationList.get(i)) {
                return i - 1;
            }
        }
        return headerLocationList.size() - 1;
    }

    class HeaderViewHolder {
        TextView tvName, btnChangeTheTimeOfArrival;
        View diTop;
    }

    class ViewHolder {
        TextView tvChildName;
        private ImageView imIcon;
    }
}
