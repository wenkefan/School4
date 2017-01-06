package com.fwk.school4.ui.Song;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fwk.school4.R;
import com.fwk.school4.constant.Keyword;
import com.fwk.school4.constant.SpLogin;
import com.fwk.school4.listener.NetWorkListener;
import com.fwk.school4.model.ChildBean;
import com.fwk.school4.model.FristFaChe;
import com.fwk.school4.model.StaBean;
import com.fwk.school4.model.StationBean;
import com.fwk.school4.model.StationFADAOBean;
import com.fwk.school4.model.UpDownCar;
import com.fwk.school4.network.HTTPURL;
import com.fwk.school4.network.api.CarDZNetWork;
import com.fwk.school4.network.api.DownCarNetWork;
import com.fwk.school4.network.api.EndNetWork;
import com.fwk.school4.network.api.UpCarNetWork;
import com.fwk.school4.ui.BasaActivity;
import com.fwk.school4.ui.ShangcheActivity;
import com.fwk.school4.ui.XiacheActivity;
import com.fwk.school4.ui.adapter.JieChildListAdapter2;
import com.fwk.school4.utils.ChildData;
import com.fwk.school4.utils.GetDateTime;
import com.fwk.school4.utils.LogUtils;
import com.fwk.school4.utils.SharedPreferencesUtils;
import com.fwk.school4.utils.ToastUtil;

import java.util.List;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by fanwenke on 16/12/23.
 */

public class SongChildListActivity2 extends BasaActivity implements JieChildListAdapter2.OnItemAdapterListener, NetWorkListener {

    @InjectView(R.id.title_tv)
    TextView title;
    @InjectView(R.id.rv_recyle_activity)
    RecyclerView rv;
    @InjectView(R.id.btn_fache)
    Button btn;
    private String[] askForLeaveStatus = new String[]{"已上车", "病假", "事假", "家长接送"};
    private LinearLayoutManager manager;
    private JieChildListAdapter2 adapter;
    private SharedPreferencesUtils sp = new SharedPreferencesUtils();

    private Map<String, List<ChildBean.RerurnValueBean>> map;//幼儿map
    private StaBean staBean;//选中幼儿所在的站点
    private int mItem;//站点中幼儿的位置数
    private int position;
    private int jumpPosition;
    private List<StationBean.RerurnValueBean> stationlist;
    private boolean isJieShu = false;

    public SongChildListActivity2() {

        stationlist = (List<StationBean.RerurnValueBean>) sp.queryForSharedToObject(Keyword.SP_STATION_LIST);
    }

    @Override
    public int getLayoutId() {
        return R.layout.jie_child_list2;
    }

    @Override
    public void init() {
        title.setText(R.string.song);

        Intent intent = getIntent();
        position = intent.getIntExtra(Keyword.STATIONPOSITION, 0);
        jumpPosition = intent.getIntExtra(Keyword.JUMPPOSITION, 0);


        manager = new LinearLayoutManager(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(manager);
        adapter = new JieChildListAdapter2();
        rv.setAdapter(adapter);
        adapter.setOnItemAdapterListener(this);
        if (position == stationlist.size() - 1) {
            btn.setText("结束");
            isJieShu = true;
        }
    }

    @Override
    public void setOnItemListener(StaBean staid, int position) {
        //手动选择幼儿状态
        staBean = staid;
        mItem = position;
        map = (Map<String, List<ChildBean.RerurnValueBean>>) sp.queryForSharedToObject(Keyword.MAPLIST);
        List<ChildBean.RerurnValueBean> list = map.get(staid.getStrid());
        ChildBean.RerurnValueBean bean = list.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", bean);
        if (staid.getType() == 1) {
            Intent intent = new Intent(this, ShangcheActivity.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, 3);
        }
        if (staid.getType() == 2) {
            Intent intent = new Intent(this, XiacheActivity.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, 4);
        }
    }
    private int childPosition;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 3) {
                //上车重新分组
                childPosition = data.getIntExtra(Keyword.SP_SELECT_ID, 0);
                if (map.get(staBean.getStrid()).get(mItem).getSelectid() == childPosition) {
                    ToastUtil.show(map.get(staBean.getStrid()).get(mItem).getChildName() + askForLeaveStatus[position - 1]);
                } else {
                    showDialog();
                    /**
                     * 字段：派车单号、幼儿编号、站点、时间、状态、kgid、上下车类型（1、上车；2、下车）
                     */
                    String url = String.format(
                            HTTPURL.API_STUDENT_OPEN_DOWN,
                            sp.getInt(Keyword.SP_PAICHEDANHAO),
                            map.get(staBean.getStrid()).get(mItem).getChildId(),
                            staBean.getId(),
                            GetDateTime.getdatetime(),
                            position,
                            SpLogin.getKgId(),
                            1);
                    LogUtils.d("上车接口---：" + url);
                    DownCarNetWork downCarNetWork = DownCarNetWork.newInstance(this);
                    downCarNetWork.setNetWorkListener(this);
                    downCarNetWork.setUrl(Keyword.FLAGDOWNCAR,url, UpDownCar.class);

                }
            } else if (requestCode == 4) {
                //下车重新分组
                childPosition = data.getIntExtra(Keyword.SP_SELECT_ID, 0);
                if (ChildData.setXiache(map, staBean, mItem, childPosition) == 0) {
                    ToastUtil.show(map.get(staBean.getStrid()).get(mItem).getChildName() + "已下车");
                    return;
                }
                /**
                 * 字段：派车单号、幼儿编号、站点、时间、状态、kgid、上下车类型（1、上车；2、下车）
                 */
                String url = String.format(
                        HTTPURL.API_STUDENT_OPEN_DOWN,
                        sp.getInt(Keyword.SP_PAICHEDANHAO),
                        map.get(staBean.getStrid()).get(mItem).getChildId(),
                        staBean.getId(),
                        GetDateTime.getdatetime(),
                        position,
                        SpLogin.getKgId(),
                        2);
                LogUtils.d("下车接口：" + url);
                UpCarNetWork upCarNetWork = UpCarNetWork.newInstance(this);
                upCarNetWork.setNetWorkListener(this);
                upCarNetWork.setUrl(Keyword.FLAGUPCAR,url, UpDownCar.class);
            }
        }
    }

    @OnClick(R.id.btn_fache)
    public void onClick(View view) {
        if (isJieShu) {
            /**
             * 发车字段为：班次编号、kgid、发车时间、类型(1发车、2停车)
             * 停车字段为：派车单号、kgid、发车时间、类型(1发车、2停车)
             */
            showDialog();
            String url = String.format(HTTPURL.API_OPEN, sp.getInt(Keyword.SP_PAICHEDANHAO), SpLogin.getKgId(), GetDateTime.getdatetime(), 2);
            LogUtils.d("结束URL：" + url);
            EndNetWork endNetWork = EndNetWork.newInstance(this);
            endNetWork.setNetWorkListener(this);
            endNetWork.setUrl(Keyword.FLAGENDDAOZHAN, url, FristFaChe.class);
        } else {
            showDialog();
            String url = String.format(HTTPURL.API_PROCESS, SpLogin.getKgId(),stationlist.get(position).getStationId(),sp.getInt(Keyword.SP_PAICHEDANHAO),2, GetDateTime.getdatetime());
            LogUtils.d("发车URL：" + url);
            CarDZNetWork carDZNetWork = CarDZNetWork.newInstance(this);
            carDZNetWork.setNetWorkListener(this);
            carDZNetWork.setUrl(Keyword.FLAGDAOZHAN,url, StationFADAOBean.class);
        }
    }

    @Override
    public void NetWorkSuccess(int Flag) {
        switch (Flag){
            case Keyword.FLAGDAOZHAN:
                handler.sendEmptyMessage(Keyword.FLAGDAOZHAN);
                break;
            case Keyword.FLAGENDDAOZHAN:
                handler.sendEmptyMessage(Keyword.FLAGENDDAOZHAN);
                break;
            case Keyword.FLAGDOWNCAR:
                handler.sendEmptyMessage(Keyword.FLAGDOWNCAR);
                break;
            case Keyword.FLAGUPCAR:
                handler.sendEmptyMessage(Keyword.FLAGUPCAR);
                break;
        }
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            closeDialog();
            switch (msg.what){
                case Keyword.FLAGDAOZHAN:
                    position++;
                    sp.setInt(Keyword.THISSATION, position);
                    sp.setboolean(Keyword.ISDAOZHAN, true);
                    finish();
                    break;
                case Keyword.FLAGENDDAOZHAN:
                    ToastUtil.show("结束了");
                    sp.setboolean(Keyword.BEGIN, false);
                    finish();
                    break;
                case Keyword.FLAGDOWNCAR:
                    ChildData.setJieData(map, staBean, mItem, childPosition);
                    adapter.getData();
                    adapter.notifyDataSetChanged();
                    break;
                case Keyword.FLAGUPCAR:
                    adapter.getData();
                    adapter.notifyDataSetChanged();
                    ToastUtil.show(map.get(staBean.getStrid()).get(mItem).getChildName() + "下车");
                    break;
            }
        }
    };
}
