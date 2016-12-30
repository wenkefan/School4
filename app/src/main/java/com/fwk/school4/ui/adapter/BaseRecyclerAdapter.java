package com.fwk.school4.ui.adapter;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by fanwenke on 16/12/6.
 */

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseRecyclerAdapter.ClickableViewHolder> {


    public interface OnItemListener {

        public void setOnItemListener(int position, ClickableViewHolder holder);

    }

    private OnItemListener onItemListener;

    public void setOnItemListener(OnItemListener onItemListener){
        this.onItemListener = onItemListener;
    }

    @Override
    public void onBindViewHolder(final ClickableViewHolder holder, final int position) {
        holder.getParentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemListener != null){
                    onItemListener.setOnItemListener(position, holder);
                }
            }
        });
    }

    public class ClickableViewHolder extends RecyclerView.ViewHolder{

        private View parentView;

        public ClickableViewHolder(View itemView) {
            super(itemView);
            this.parentView = itemView;
        }

        public View getParentView(){
            return parentView;
        }

        public <T extends View> T $(@IdRes int id){
            return (T) parentView.findViewById(id);
        }

    }
}
