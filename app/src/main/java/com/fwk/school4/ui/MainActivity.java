package com.fwk.school4.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.fwk.school4.R;
import com.fwk.school4.constant.Keyword;
import com.fwk.school4.constant.SpBanci;
import com.fwk.school4.constant.SpLogin;
import com.fwk.school4.listener.FacheListener;
import com.fwk.school4.model.BanciBean;
import com.fwk.school4.model.FristFaChe;
import com.fwk.school4.network.HTTPURL;
import com.fwk.school4.network.api.BanCinetwork;
import com.fwk.school4.listener.NetWorkListener;
import com.fwk.school4.network.api.FristNetWork;
import com.fwk.school4.ui.Jie.JieStationMapActivity;
import com.fwk.school4.ui.Song.SongStationMapActivity;
import com.fwk.school4.ui.adapter.BaseRecyclerAdapter;
import com.fwk.school4.ui.adapter.MainRecyclerViewAdapter;
import com.fwk.school4.utils.GetDateTime;
import com.fwk.school4.utils.LogUtils;
import com.fwk.school4.utils.SharedPreferencesUtils;
import com.fwk.school4.utils.SharedPreferencesUtils2;
import com.fwk.school4.utils.ToastUtil;
import com.fwk.school4.weight.MainDialog;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by fanwenke on 16/12/5.
 */

public class MainActivity extends BasaActivity implements NetWorkListener, BaseRecyclerAdapter.OnItemListener, FacheListener {

    private MainRecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private SharedPreferencesUtils sp;
    private SharedPreferencesUtils2 spData;
    private String FristURL;

    @InjectView(R.id.man_recyclerView)
    RecyclerView mRecyclerView;

    @InjectView(R.id.title_tv)
    TextView title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        sp = new SharedPreferencesUtils();
        spData = new SharedPreferencesUtils2();
        title.setText(getResources().getString(R.string.main_tltile));
        if (sp.getBoolean(Keyword.BEGIN)) {
            MainDialog.Beagin(this, (BanciBean.RerurnValueBean) sp.queryForSharedToObject(Keyword.SELECTBANCI));

        } else {
            //请求班次
            requestBanci();
        }

    }

    private void recyclerView() {
        //配置RecyclerView
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MainRecyclerViewAdapter();
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemListener(this);
    }

    public void requestBanci() {
        //请求班次接口
        BanCinetwork banCinetwork = BanCinetwork.newInstance(this);
        banCinetwork.setNetWorkListener(this);
        String url = String.format(HTTPURL.API_BANCI, SpLogin.getKgId());
        LogUtils.d("班次接口:" + url);
        banCinetwork.setUrl(Keyword.FLAGBANCI, url, BanciBean.class);
    }

    @Override
    public void NetWorkSuccess(int Flag) {
        switch (Flag) {
            case Keyword.FLAGBANCI:
                mHandler.sendEmptyMessage(Keyword.FLAGBANCI);
                break;
            case Keyword.FLAGFIRSTFACHE:
                mHandler.sendEmptyMessage(Keyword.FLAGFIRSTFACHE);
                break;
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Keyword.FLAGBANCI:

                    recyclerView();
                    break;
                case Keyword.FLAGFIRSTFACHE:
                    sp.removData();
                    sp.saveToShared(Keyword.SELECTBANCI, bean);
                    sp.setboolean(Keyword.BEGIN, true);
                    startActivity(intent);
                    finish();
                    break;
                case 0x01:
                    Intent intent1 = new Intent(MainActivity.this, JieStationMapActivity.class);
                    intent1.putExtra(Keyword.POTIONIT, -1);
                    startActivity(intent1);
                    finish();
                    break;
                case 0x02:
                    Intent intent2 = new Intent(MainActivity.this, SongStationMapActivity.class);
                    intent2.putExtra(Keyword.POTIONIT, -1);
                    startActivity(intent2);
                    finish();
                    break;
            }
        }
    };
    private BanciBean.RerurnValueBean bean;

    @Override
    public void setOnItemListener(int position, BaseRecyclerAdapter.ClickableViewHolder holder) {

        BanciBean.RerurnValueBean bean =
                ((List<BanciBean.RerurnValueBean>) (spData.queryForSharedToObject(Keyword.SP_BANCI_LIST))).get(position);
        SpBanci.save(bean.getBusScheduleId(), bean.getLineId(), bean.getAttendanceDirections());
        this.bean = bean;
        switch (bean.getStatus()) {
            case 0:
                spData.setInt(Keyword.SP_ATTENDANCEDIRECTIONS, bean.getAttendanceDirections());
                if (bean.getOriginal()) {

                    MainDialog.ShowJRBanci(this, bean.getBusScheduleName(), bean.getAttendanceDirections(), position);
                } else {
                    MainDialog.ShowDLBanci(this, bean.getBusScheduleName(), bean.getTeacherName(),
                            bean.getAttendanceDirections(), position);
                }
                MainDialog.setBackListener(this);
                /**
                 * 发车字段为：班次编号、kgid、发车时间、类型(1发车、2停车)
                 * 停车字段为：派车单号、kgid、发车时间、类型(1发车、2停车)
                 */
                FristURL = String.format(HTTPURL.API_OPEN, bean.getBusScheduleId(), SpLogin.getKgId(), GetDateTime.getdatetime(),
                        1, SpLogin.getWorkerExtensionId());

                break;
            case 1:
                if (bean.getModifierId() == SpLogin.getWorkerExtensionId()) {
                    if (bean.getAttendanceDirections() == 1) {
                        mHandler.sendEmptyMessage(0x01);
                        //接幼儿
                    } else {
                        mHandler.sendEmptyMessage(0x02);
                        //送幼儿
                    }
                } else {
                    ToastUtil.show("本班次正在运行中...");
                }
                break;
            case 2:
                ToastUtil.show("本班次今日已结束");
                break;
        }
    }

    private Intent intent;

    @Override
    public void BackListener(Intent intent) {
        LogUtils.d("发车接口:" + FristURL);
        FristNetWork fristNetWork = FristNetWork.newInstance(this);
        fristNetWork.setNetWorkListener(this);
        fristNetWork.setUrl(Keyword.FLAGFIRSTFACHE, FristURL, FristFaChe.class);
        this.intent = intent;
    }
}
