package com.fwk.school4.ui.Song;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fwk.school4.R;
import com.fwk.school4.animation.ExpandableStickyListHeadersListViewAnimation;
import com.fwk.school4.constant.Keyword;
import com.fwk.school4.listener.OnItemClickListener;
import com.fwk.school4.model.ChildBean;
import com.fwk.school4.model.StationBean;
import com.fwk.school4.ui.BasaActivity;
import com.fwk.school4.ui.ShangcheActivity;
import com.fwk.school4.ui.adapter.SongChildListAdapter;
import com.fwk.school4.utils.SharedPreferencesUtils;
import com.fwk.school4.utils.ToastUtil;

import java.util.List;

import butterknife.InjectView;
import se.emilsjolander.stickylistheaders.ExpandableStickyListHeadersListView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView.OnHeaderClickListener;

/**
 * Created by fanwenke on 16/12/8.
 */

public class SongChildListActivity extends BasaActivity implements OnHeaderClickListener, OnItemClickListener {

    @InjectView(R.id.title_tv)
    TextView title;
    @InjectView(R.id.eslhlv_list)
    ExpandableStickyListHeadersListView list;
    @InjectView(R.id.btn_fache)
    Button fache;

    private SharedPreferencesUtils sp = new SharedPreferencesUtils();
    private SongChildListAdapter adapter;
    private int position;//站点位置
    private int jumpPosition;
    private List<StationBean.RerurnValueBean> stationlist;
    private boolean isJieShu = false;

    public SongChildListActivity() {

        stationlist = (List<StationBean.RerurnValueBean>) sp.queryForSharedToObject(Keyword.SP_STATION_LIST);
    }

    @Override
    public int getLayoutId() {
        return R.layout.jie_child_list;
    }

    @Override
    public void init() {
        title.setText(getResources().getString(R.string.song));
        Intent intent = getIntent();
        position = intent.getIntExtra(Keyword.STATIONPOSITION, 0);
        jumpPosition = intent.getIntExtra(Keyword.JUMPPOSITION,0);
        adapter = new SongChildListAdapter();
        list.setDrawingListUnderStickyHeader(true);
        list.setAreHeadersSticky(true);
        list.setOnHeaderClickListener(this);
        list.setAnimExecutor(new ExpandableStickyListHeadersListViewAnimation());
        list.setAdapter(adapter);
        list.setSelection(jumpPosition);
        adapter.setOnItemClickListener(this);
        if (position == stationlist.size() - 1) {
            fache.setText("结束");
            isJieShu = true;
        }
    }

    @Override
    public void onHeaderClick(StickyListHeadersListView l, View header, int itemPosition, long headerId, boolean currentlySticky) {

    }

    public void fache(View view) {

        if (isJieShu){
            ToastUtil.show("结束了");
            sp.setboolean(Keyword.BEGIN,false);
            this.finish();
        } else {
            position++;
            sp.setInt(Keyword.THISSATION, position);
            sp.setboolean(Keyword.ISDAOZHAN, true);
            this.finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<ChildBean.RerurnValueBean> been = (List<ChildBean.RerurnValueBean>) sp.queryForSharedToObject(Keyword.CHILDGROUP);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                int selet = data.getIntExtra(Keyword.SP_SELECT_ID, 0);
                int position = data.getIntExtra(Keyword.SELECTCHILD, 0);
                been.get(position).setSelectid(selet);
                sp.saveToShared(Keyword.CHILDGROUP, been);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ShangcheActivity.class);
        intent.putExtra(Keyword.SELECTCHILD, position);
        startActivityForResult(intent, 1);
    }
}
