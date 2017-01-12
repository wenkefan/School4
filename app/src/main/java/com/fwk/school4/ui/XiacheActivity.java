package com.fwk.school4.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fwk.school4.R;
import com.fwk.school4.constant.Keyword;
import com.fwk.school4.model.ChildBean;
import com.fwk.school4.model.StationModeBean;
import com.fwk.school4.ui.adapter.BaseRecyclerAdapter;
import com.fwk.school4.ui.adapter.ShangCheRecyclerAdapter;
import com.fwk.school4.ui.adapter.XiaCheRecyclerAdapter;
import com.fwk.school4.utils.SharedPreferencesUtils;
import com.fwk.school4.weight.CenterItemDialog;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by fanwenke on 16/12/19.
 */

public class XiacheActivity extends BasaActivity implements View.OnClickListener, CenterItemDialog.OnItemClickListener, BaseRecyclerAdapter.OnItemListener {
    @InjectView(R.id.title_tv)
    TextView title;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_ask_for_leave_status)
    TextView tvAskForLeaveStatus;
    @InjectView(R.id.tv_father_name)
    TextView tvFatherName;
    @InjectView(R.id.tv_father_phone)
    TextView tvFatherPhone;
    @InjectView(R.id.tv_mother_name)
    TextView tvMotherName;
    @InjectView(R.id.tv_mother_phone)
    TextView tvMotherPhone;
    @InjectView(R.id.tv_select_station)
    TextView tvSelectStation;
    @InjectView(R.id.rv_select_type)
    RecyclerView rv;

    private ChildBean.RerurnValueBean bean;
    private SharedPreferencesUtils sp = new SharedPreferencesUtils();
    private SharedPreferencesUtils spData = new SharedPreferencesUtils();
//    private boolean isZhuangtai = false;
    private CenterItemDialog dialog = null;
    private List<StationModeBean> stationModeBeen;
    private LinearLayoutManager manager;
    private XiaCheRecyclerAdapter adapter;

    public XiacheActivity() {

        stationModeBeen = (List<StationModeBean>) spData.queryForSharedToObject(Keyword.STATIONIDLIST);
    }

    @Override
    public int getLayoutId() {
        return R.layout.schoolcar_dialog;
    }

    @Override
    public void init() {
        Intent intent = getIntent();
        bean = (ChildBean.RerurnValueBean) intent.getSerializableExtra("bean");
        manager = new LinearLayoutManager(this);
        initView();
        setGetOnBusData();
    }

    private void initView() {
        title.setText(getResources().getString(R.string.select));
        tvName.setText(bean.getChildName());
        tvFatherName.setText(bean.getFatherName());
        tvFatherPhone.setText(bean.getFatherPhone());
        tvMotherName.setText(bean.getMotherName());
        tvMotherPhone.setText(bean.getMotherPhone());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(manager);
        adapter = new XiaCheRecyclerAdapter();
        rv.setAdapter(adapter);
        adapter.setOnItemListener(this);
    }

    @OnClick({R.id.btn_confirm, R.id.tv_ask_for_leave_status, R.id.tv_select_station})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                    Intent intent = new Intent();
                    intent.putExtra(Keyword.SP_SELECT_ID, 5);
                    setResult(RESULT_OK, intent);
                    finish();
                break;
            case R.id.tv_ask_for_leave_status:
                dialog.show();
                break;
            case R.id.tv_select_station:
                break;
        }
    }

    /**
     * 请假状态
     */
    private String[] askForLeaveStatus = null;

    private void setGetOnBusData() {
        if (true) {

            askForLeaveStatus = new String[]{"已上车（已刷卡、未带卡）", "病假", "事假", "家长接送"};

        } else if (false) {

            askForLeaveStatus = new String[]{"已下车（已刷卡、未带卡）"};

        }

        tvAskForLeaveStatus.setOnClickListener(this);
        dialog = new CenterItemDialog(this);
        dialog.setTitle(R.string.manual_operation_dialog_title_choise_children_status);
        dialog.setOnItemClickListener(this);
        dialog.setItems(askForLeaveStatus);

    }

    @Override
    public void onItemClick(int requestCode, int position) {
        if (requestCode == 1) {


        } else {
            tvAskForLeaveStatus.setText(askForLeaveStatus[position]);
            switch (position) {
                case 0:
                    //手动上下车
                    SelectState = ChildState1;
//                    isZhuangtai = true;
                    break;
                case 1:
                    //病假
                    SelectState = ChildState2;
//                    isZhuangtai = true;
                    break;
                case 2:
                    //事假
                    SelectState = ChildState3;
//                    isZhuangtai = true;
                    break;
                case 3:
                    //家长接送
                    SelectState = ChildState4;
//                    isZhuangtai = true;
                    break;
                default:
                    break;
            }
        }
    }

    private static final int ChildState1 = 1;
    private static final int ChildState2 = 2;
    private static final int ChildState3 = 3;
    private static final int ChildState4 = 4;
    private int SelectState = 1;


    @Override
    public void setOnItemListener(int position, BaseRecyclerAdapter.ClickableViewHolder holder) {
        SelectState = position + 1;
        adapter.setItme(position);
        adapter.notifyDataSetChanged();
    }
}
