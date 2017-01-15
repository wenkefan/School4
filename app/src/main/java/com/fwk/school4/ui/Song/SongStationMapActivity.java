package com.fwk.school4.ui.Song;

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
import com.fwk.school4.listener.NetWorkListener;
import com.fwk.school4.model.BanciBean;
import com.fwk.school4.model.ChildBean;
import com.fwk.school4.model.StateStationBean;
import com.fwk.school4.model.StationBean;
import com.fwk.school4.model.StationFADAOBean;
import com.fwk.school4.network.HTTPURL;
import com.fwk.school4.network.api.CarFCNetWork;
import com.fwk.school4.network.api.ChildNetWork;
import com.fwk.school4.network.api.StaionNetWork;
import com.fwk.school4.ui.BasaActivity;
import com.fwk.school4.ui.Jie.JieChildListActivity2;
import com.fwk.school4.ui.Jie.JieStationMapActivity;
import com.fwk.school4.ui.adapter.BaseRecyclerAdapter;
import com.fwk.school4.ui.adapter.MapRecyclerViewAdapter;
import com.fwk.school4.utils.GetDateTime;
import com.fwk.school4.utils.LogUtils;
import com.fwk.school4.utils.SharedPreferencesUtils;
import com.fwk.school4.utils.SharedPreferencesUtils2;
import com.fwk.school4.utils.Stationutil;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by fanwenke on 16/12/7.
 */

public class SongStationMapActivity extends BasaActivity implements NetWorkListener, BaseRecyclerAdapter.OnItemListener, DaoZhanListener {

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
    TextView mCount;

    private MapRecyclerViewAdapter adapter;

    private SharedPreferencesUtils sp;
    private SharedPreferencesUtils2 spData;

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
    public void init() {
        title.setText(getResources().getString(R.string.map_tltile));
        sp = new SharedPreferencesUtils();
        spData = new SharedPreferencesUtils2();
        display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        Intent intent = getIntent();
        int position = intent.getIntExtra(Keyword.POTIONIT, 0);
        if (position == -1) {
            setData();
        } else {
            initData(position);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean is = sp.getBoolean(Keyword.ISDAOZHAN);
        if (!sp.getBoolean(Keyword.BEGIN)) {
            this.finish();
            return;
        }
        if (is) {
            stationPosition = sp.getInt(Keyword.THISSATION);
            setTitleNemaTime();
            adapter.setPostion(stationPosition);
            adapter.setNumberSX();
            adapter.notifyDataSetChanged();
        } else {
            try {
                StateStationBean stateStationBean = (StateStationBean) sp.queryForSharedToObject(Keyword.STATESTATIONBEAN);
                Intent intent = new Intent(SongStationMapActivity.this, SongChildListActivity2.class);
                intent.putExtra(Keyword.JUMPPOSITION, stateStationBean.isJUMPPOSITION());
                intent.putExtra(Keyword.STATIONPOSITION, stateStationBean.getPosition());
                intent.putExtra(Keyword.SELECTSTATIONID, stateStationBean.getStationSelId());
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initData(int position) {//初始化数据
        list = (List<BanciBean.RerurnValueBean>) spData.queryForSharedToObject(Keyword.SP_BANCI_LIST);
        bean = list.get(position);
        //站点url
        String url = String.format(HTTPURL.API_ZHANDIAN, SpLogin.getKgId(),
                bean.getAttendanceDirections(), bean.getLineId());
        LogUtils.d("站点接口:" + url);
        StaionNetWork staionNetWork = StaionNetWork.newInstance(this);
        staionNetWork.setNetWorkListener(this);
        staionNetWork.setUrl(Keyword.FLAGSTATION, url, StationBean.class);
    }

    private void setData() {//继续运行加载数据
        stationPosition = sp.getInt(Keyword.THISSATION);
        nextName.setText(sp.getString(Keyword.NEXTSTANAME));
        yjTime.setText(sp.getString(Keyword.NEXTTIME));
        mCount.setText(sp.getInt(Keyword.CARNUMBER) + "");
        recyclerInit();
    }

    private void recyclerInit() {
        times = (List<String>) sp.queryForSharedToObject(Keyword.GETSJTIME);
        if (times == null) {
            times = new ArrayList<>();
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new MapRecyclerViewAdapter(display, stationPosition, times);
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
            case Keyword.FLAGFACHE:
                handler.sendEmptyMessage(Keyword.FLAGFACHE);
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
                    ChildNetWork childNetWork = ChildNetWork.newInstance(SongStationMapActivity.this);
                    childNetWork.setNetWorkListener(SongStationMapActivity.this);
                    childNetWork.setUrl(Keyword.FLAGCHILD, url, ChildBean.class);
                    setTitleNemaTime();
                    break;

                case Keyword.FLAGCHILD:

                    recyclerInit();

                    break;
                case Keyword.FLAGFACHE:
                    StateStationBean stateStationBean = new StateStationBean();
                    stateStationBean.setJUMPPOSITION(true);
                    stateStationBean.setStationSelId(stationSelId);
                    stateStationBean.setPosition(Position);
                    sp.saveToShared(Keyword.STATESTATIONBEAN, stateStationBean);
                    sp.setboolean(Keyword.ISDAOZHAN, false);
                    setSJTime();
                    Intent intent = new Intent(SongStationMapActivity.this, SongChildListActivity2.class);
                    intent.putExtra(Keyword.JUMPPOSITION, true);
                    intent.putExtra(Keyword.STATIONPOSITION, Position);
                    intent.putExtra(Keyword.SELECTSTATIONID, stationSelId);
                    startActivity(intent);

                    break;
            }
        }
    };

    @Override
    public void setOnItemListener(int position, BaseRecyclerAdapter.ClickableViewHolder holder) {
        Intent intent = new Intent(this, SongChildListActivity2.class);
        intent.putExtra(Keyword.JUMPPOSITION, false);
        intent.putExtra(Keyword.STATIONPOSITION, position);
        startActivity(intent);
    }

    private void setTitleNemaTime() {
        List<StationBean.RerurnValueBean> stationList =
                (List<StationBean.RerurnValueBean>) spData.queryForSharedToObject(Keyword.SP_STATION_LIST);
        nextName.setText(stationList.get(stationPosition).getStationName());
        yjTime.setText(GetDateTime.getYJTime(stationList.get(stationPosition).getDuration()));
        mCount.setText(sp.getInt(Keyword.CARNUMBER) + "");
        sp.setString(Keyword.NEXTSTANAME, stationList.get(stationPosition).getStationName());
        sp.setString(Keyword.NEXTTIME, GetDateTime.getYJTime(stationList.get(stationPosition).getDuration()));
    }

    private int Position;
    private int stationSelId;

    @Override
    public void OnClickListener(int position) {
        List<StationBean.RerurnValueBean> stationList = (List<StationBean.RerurnValueBean>) spData.queryForSharedToObject(Keyword.SP_STATION_LIST);
        this.Position = position;
        this.stationSelId = stationList.get(position).getStationId();
        String url = String.format(HTTPURL.API_PROCESS, SpLogin.getKgId(), stationList.get(position).getStationId(), spData.getInt(Keyword.SP_PAICHEDANHAO), 1, GetDateTime.getdatetime());
        LogUtils.d("到站URL：" + url);
        CarFCNetWork carFCNetWork = CarFCNetWork.newInstance(this);
        carFCNetWork.setNetWorkListener(this);
        carFCNetWork.setUrl(Keyword.FLAGFACHE, url, StationFADAOBean.class);
    }

    /**
     * 记录实际到站时间
     */
    private void setSJTime() {
        int H = GetDateTime.getH();
        int M = GetDateTime.getM();
        String Hh;
        String Mm;
        if (H < 10) {
            Hh = "0" + H;
        } else {
            Hh = H + "";
        }

        if (M < 10) {
            Mm = "0" + M;
        } else {
            Mm = M + "";
        }
        String time = Hh + ":" + Mm;
        times.add(time);
        sp.saveToShared(Keyword.GETSJTIME, times);
    }
}
