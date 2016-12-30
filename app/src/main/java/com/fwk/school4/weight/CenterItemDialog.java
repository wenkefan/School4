package com.fwk.school4.weight;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.fwk.school4.R;
import com.fwk.school4.utils.ScreenUtil;


public class CenterItemDialog extends Dialog implements View.OnClickListener {
	
	private Context mContext = null;
	private String[] mItems = null;
	private int requestCode = Integer.MIN_VALUE;
	private int titleResId ;
	
	public String[] getmItems() {
		return mItems;
	}

	public void setItems(String[] items) {
		this.mItems = items;
	}

	private LinearLayout mLlytItems = null;
	private TextView tvTitle = null;
	private Button mBtnCancel = null;
	private OnItemClickListener mListener = null;

	public CenterItemDialog(Context context) {
		this(context, R.style.Dialog_Black_Transparent );
		this.mContext = context;
	}
	
	public CenterItemDialog(Context context, int theme) {
		super(context, theme);
		this.mContext = context;
	}

	public CenterItemDialog(Context context, String[] items ) {
		this(context, R.style.Dialog_Black_Transparent );
		this.mItems = items;
		this.mContext = context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.schoolcar_dialog_center_buttons);
		Window window = getWindow();
		window.setGravity(Gravity.CENTER);
		initView();
		addView();
	}

	private void initView(){
		tvTitle = (TextView) findViewById(R.id.tv_title);
		mBtnCancel = (Button) findViewById(R.id.btn_cancel);
		mLlytItems = (LinearLayout) findViewById(R.id.llyt_items);
		mBtnCancel.setOnClickListener(this);
		if( titleResId!=0 ) {
			tvTitle.setText(titleResId);
		}
	}
	
	private void addView() {
		if( mItems==null ){
			return ;
		}
		mLlytItems.removeAllViews();
		for ( int i = 0; i < mItems.length; i++ ) {
			Button btn = new Button( mContext );
			btn.setTag("btn_item_"+i);
			btn.setText(mItems[i]);
			btn.setTextSize( TypedValue.COMPLEX_UNIT_SP, 14 );
			btn.setTextColor(Color.BLACK);
			btn.setBackgroundDrawable(null);
			btn.setGravity(Gravity.CENTER_VERTICAL);
			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, ScreenUtil.dip2px(40) );
			btn.setLayoutParams(params);
			btn.setOnClickListener(this);
			View divider = new View(mContext);
			divider.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, ScreenUtil.dip2px(0.5f)));
			divider.setBackgroundResource(R.color.divider_light);
			mLlytItems.addView(btn);
			mLlytItems.addView(divider);
		}
	}
	
	@Override
	public void setTitle(int titleResId) {
		this.titleResId = titleResId;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_cancel:
			this.dismiss();
			break;
		default:
			if( this.mListener!=null ) {
				try {
					//添加 try catch , 当 View 的 Tag 为 btn_item_ 的按钮才能执行onItemClick()
					Button btn = (Button) v;
					String tag = btn.getTag().toString();
					tag = tag.replace("btn_item_", "");
					mListener.onItemClick(requestCode, Integer.valueOf(tag));
					this.dismiss();
				} catch (Exception e) {}
			}
			break;
		}
	}
	
	/**
	 * 点击事件监听类
	 */
	public interface OnItemClickListener {
		/**
		 * 选项的点击事件
		 * @param v 点击的 View 对象
		 * @param txt 选项中显示的文本
		 * @param index	在选项列表中的索引位置
		 */
		void onItemClick(int requestCode, int position);
	}
	
	/**
	 * 设置选项点击监听器
	 */
	public void setOnItemClickListener( int requestCode, OnItemClickListener listener ){
		this.requestCode = requestCode;
		this.mListener = listener;
	}
	
	public void setOnItemClickListener( OnItemClickListener listener ){
		this.mListener = listener;
	}
}
