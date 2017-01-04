package com.fwk.school4.ui.Jie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fwk.school4.R;
import com.fwk.school4.constant.Keyword;
import com.fwk.school4.model.ChildBean;
import com.fwk.school4.model.StaBean;
import com.fwk.school4.model.StationBean;
import com.fwk.school4.ui.BasaActivity;
import com.fwk.school4.ui.ShangcheActivity;
import com.fwk.school4.ui.XiacheActivity;
import com.fwk.school4.ui.adapter.JieChildListAdapter2;
import com.fwk.school4.utils.ChildData;
import com.fwk.school4.utils.SharedPreferencesUtils;
import com.fwk.school4.utils.ToastUtil;

import java.util.List;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by fanwenke on 16/12/23.
 */

public class JieChildListActivity2 extends BasaActivity implements JieChildListAdapter2.OnItemAdapterListener {

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
    ;
    private Map<String, List<ChildBean.RerurnValueBean>> map;//幼儿map
    private StaBean staBean;//选中幼儿所在的站点
    private int mItem;//站点中幼儿的位置数
    private int position;
    private int jumpPosition;
    private List<StationBean.RerurnValueBean> stationlist;
    private boolean isJieShu = false;

    public JieChildListActivity2() {

        stationlist = (List<StationBean.RerurnValueBean>) sp.queryForSharedToObject(Keyword.SP_STATION_LIST);
    }

    @Override
    public int getLayoutId() {
        return R.layout.jie_child_list2;
    }

    @Override
    public void init() {
        title.setText(R.string.jie);

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
            startActivityForResult(intent, 1);
        }
        if (staid.getType() == 2) {
            Intent intent = new Intent(this, XiacheActivity.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, 2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                //上车重新分组
                int position = data.getIntExtra(Keyword.SP_SELECT_ID, 0);
                if (map.get(staBean.getStrid()).get(mItem).getSelectid() == position) {
                    ToastUtil.show(map.get(staBean.getStrid()).get(mItem).getChildName() + askForLeaveStatus[position - 1]);
                } else {
                    ChildData.setJieData(map, staBean, mItem, position);
                    adapter.getData();
                    adapter.notifyDataSetChanged();
                }
            } else if (requestCode == 2) {
                //下车重新分组
                int postion = data.getIntExtra(Keyword.SP_SELECT_ID,0);
                if (ChildData.setXiache(map, staBean, mItem, postion) == 0){
                    ToastUtil.show(map.get(staBean.getStrid()).get(mItem).getChildName() + "已下车");
                    return;
                }
                adapter.getData();
                adapter.notifyDataSetChanged();
                ToastUtil.show(map.get(staBean.getStrid()).get(mItem).getChildName() + "下车");
            }
        }
    }

    @OnClick(R.id.btn_fache)
    public void onClick(View view) {
        if (isJieShu) {
            ToastUtil.show("结束了");
            sp.setboolean(Keyword.BEGIN, false);
            this.finish();
        } else {
            position++;
            sp.setInt(Keyword.THISSATION, position);
            sp.setboolean(Keyword.ISDAOZHAN, true);
            this.finish();
        }
    }
}
