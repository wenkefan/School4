package com.fwk.school4.ui.Jie;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.fwk.school4.R;
import com.fwk.school4.constant.Keyword;
import com.fwk.school4.constant.SpLogin;
import com.fwk.school4.listener.DaoZhanListener;
import com.fwk.school4.model.BanciBean;
import com.fwk.school4.model.ChildBean;
import com.fwk.school4.model.StationBean;
import com.fwk.school4.network.HTTPURL;
import com.fwk.school4.network.api.ChildNetWork;
import com.fwk.school4.network.api.StaionNetWork;
import com.fwk.school4.listener.NetWorkListener;
import com.fwk.school4.ui.BasaActivity;
import com.fwk.school4.ui.adapter.BaseRecyclerAdapter;
import com.fwk.school4.ui.adapter.MapRecyclerViewAdapter;
import com.fwk.school4.utils.GetDateTime;
import com.fwk.school4.utils.LogUtils;
import com.fwk.school4.utils.SharedPreferencesUtils;
import com.fwk.school4.utils.Stationutil;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by fanwenke on 16/12/7.
 */

public class JieStationMapActivity extends BasaActivity implements NetWorkListener, BaseRecyclerAdapter.OnItemListener, DaoZhanListener {

    @InjectView(R.id.station_map_recycler)
    RecyclerView mRecyclerView;
    @InjectView(R.id.title_tv)
    TextView title;
    @InjectView(R.id.tv_main_station)
    TextView mStationNaem;
    @InjectView(R.id.tv_main_station_time)
    TextView mStationTime;
    @InjectView(R.id.tv_next_name)
    TextView nextName;
    @InjectView(R.id.tv_yjtiem)
    TextView yjTime;
    @InjectView(R.id.tv_count)
    TextView renshu;


    private MapRecyclerViewAdapter adapter;

    private SharedPreferencesUtils sp;

    private List<BanciBean.RerurnValueBean> list;

    private DisplayMetrics display;

    private BanciBean.RerurnValueBean bean;

    private int stationPosition = 0;

    private List<String> times;
    @Override
    public int getLayoutId() {
        return R.layout.station_map_activity2;
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean is = sp.getBoolean(Keyword.ISDAOZHAN);
        if (!sp.getBoolean(Keyword.BEGIN)){
            this.finish();
            return;
        }
        if (is) {
            stationPosition = sp.getInt(Keyword.THISSATION);
            setTitleNemaTime();
            adapter.setPostion(stationPosition);
            adapter.notifyDataSetChanged();
            sp.setboolean(Keyword.ISDAOZHAN,false);
        }
    }

    @Override
    public void init() {
        title.setText(getResources().getString(R.string.map_tltile));
        sp = new SharedPreferencesUtils();
        initData();
    }

    private void initData() {//初始化数据
        list = (List<BanciBean.RerurnValueBean>) sp.queryForSharedToObject(Keyword.SP_BANCI_LIST);
        Intent intent = getIntent();
        int position = intent.getIntExtra(Keyword.POTIONIT, 0);

        bean = list.get(position);
        //站点url
        String url = String.format(HTTPURL.API_ZHANDIAN, SpLogin.getKgId(),
                bean.getAttendanceDirections(), bean.getLineId());
        LogUtils.d("站点接口:" + url);
        StaionNetWork staionNetWork = StaionNetWork.newInstance(this);
        staionNetWork.setNetWorkListener(this);
        staionNetWork.setUrl(Keyword.FLAGSTATION, url, StationBean.class);
    }

    private void recyclerInit() {
        times = (List<String>) sp.queryForSharedToObject(Keyword.GETSJTIME);
        if (times == null){
            times = new ArrayList<>();
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        adapter = new MapRecyclerViewAdapter(display, stationPosition,times);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemListener(this);
        adapter.setOnClickListener(this);
    }


    @Override
    public void NetWorkSuccess(int Flag) {
        switch (Flag) {

            case Keyword.FLAGSTATION:
                handler.sendEmptyMessage(Keyword.FLAGSTATION);
                break;

            case Keyword.FLAGCHILD:
                handler.sendEmptyMessage(Keyword.FLAGCHILD);
                break;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case Keyword.FLAGSTATION:
                    String url = String.format(HTTPURL.API_CHILD, SpLogin.getKgId(),
                            bean.getBusScheduleId(), bean.getAttendanceDirections());
                    LogUtils.d("幼儿接口:" + url);
                    ChildNetWork childNetWork = ChildNetWork.newInstance(JieStationMapActivity.this);
                    childNetWork.setNetWorkListener(JieStationMapActivity.this);
                    childNetWork.setUrl(Keyword.FLAGCHILD, url, ChildBean.class);
                    setTitleNemaTime();
                    break;

                case Keyword.FLAGCHILD:

                    recyclerInit();

                    break;
            }
        }
    };

    @Override
    public void setOnItemListener(int position, BaseRecyclerAdapter.ClickableViewHolder holder) {

        int location = 0;
        List<Integer> childCount = (List<Integer>) sp.queryForSharedToObject(Keyword.CHILDCOUNT);
        for (int i = 0; i < position; i++) {
            location += childCount.get(i);
        }
        Intent intent = new Intent(this, JieChildListActivity2.class);
        intent.putExtra(Keyword.JUMPPOSITION, location);
        intent.putExtra(Keyword.STATIONPOSITION, position);
        startActivity(intent);

    }

    private void setTitleNemaTime() {
        List<StationBean.RerurnValueBean> stationList =
                (List<StationBean.RerurnValueBean>) sp.queryForSharedToObject(Keyword.SP_STATION_LIST);
        nextName.setText(stationList.get(stationPosition).getStationName());
        yjTime.setText(GetDateTime.getYJTime(stationList.get(stationPosition).getDuration()));
    }

    @Override
    public void OnClickListener(int position) {
        setSJTime();
        int location = 0;
        Stationutil stationutil =  Stationutil.newInstance();
        stationutil.JumpPosition(position);
        Intent intent = new Intent(this, JieChildListActivity2.class);
        intent.putExtra(Keyword.JUMPPOSITION, location);
        intent.putExtra(Keyword.STATIONPOSITION, position);
        startActivity(intent);
    }

    /**
     * 记录实际到站时间
     */
    private void setSJTime(){
        int H = GetDateTime.getH();
        int M = GetDateTime.getM();
        String Hh = "00";
        String Mm = "00";
        if (H < 10){
            Hh = "0" + H;
        }else {
            Hh = H + "";
        }

        if (M < 10){
            Mm = "0" + M;
        }else {
            Mm = M + "";
        }
        String time = Hh + ":" + Mm;
        times.add(time);
        sp.saveToShared(Keyword.GETSJTIME,times);
    }
}