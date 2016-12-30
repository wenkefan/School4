package com.fwk.school4.utils;

import com.fwk.school4.constant.Keyword;
import com.fwk.school4.model.ChildBean;
import com.fwk.school4.model.StaBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fanwenke on 16/12/27.
 */

public class ChildData {

    private static SharedPreferencesUtils sp = new SharedPreferencesUtils();

    /**
     * 送幼儿
     * <p>
     * 上车   选择之后重新分组
     *
     * @param map      整体站点幼儿数据
     * @param staBean  选择幼儿当前站点数据
     * @param mItem    选择幼儿的位置数
     * @param position 选择的类型
     *                 <p>
     *                 第一步，查找选中的幼儿是否为上车，
     *                 第二步，上车后，找到幼儿的下车站点
     *                 第三步，查找当前的map中是否有当前下车站点
     */
    public static void setSongData(Map<String, List<ChildBean.RerurnValueBean>> map, StaBean staBean, int mItem, int position) {
        //第一步
        List<StaBean> stationlist = (List<StaBean>) sp.queryForSharedToObject(Keyword.STAIDLIST);
        List<StaBean> bean = (List<StaBean>) sp.queryForSharedToObject(Keyword.SELECTSTA);
        boolean flag = false;
        if (position == 1) {


            int stationId = map.get(staBean.getStrid()).get(mItem).getSendStation();//下车站点ID
            LogUtils.d("staid--" + stationId);
            String strId = stationId + "02";
            StaBean station = new StaBean();
            for (int i = 0; i < stationlist.size(); i++) {
                if (stationlist.get(i).getStrid().equals(strId)) {
                    station = stationlist.get(i);
                    break;
                }
            }

            List<ChildBean.RerurnValueBean> list = map.get(strId);
            if (list == null) {
                list = new ArrayList<>();
                list.add(map.get(staBean.getStrid()).get(mItem));
                for (int i = 0; i < bean.size(); i++) {
                    if (bean.get(i).getId() == station.getId()) {
                        bean.add(i + 1, station);
                        flag = true;
                        break;
                    }
                }
                if (!flag){
                    bean.add(station);
                }
            } else {
                list.add(map.get(staBean.getStrid()).get(mItem));
            }

            map.put(strId, list);

        }

        map.get(staBean.getStrid()).get(mItem).setSelectid(position);
        sp.saveToShared(Keyword.SELECTSTA, bean);
        sp.saveToShared(Keyword.MAPLIST, map);
    }
    /**
     * 接幼儿
     * <p>
     * 上车   选择之后重新分组
     *
     * @param map      整体站点幼儿数据
     * @param staBean  选择幼儿当前站点数据
     * @param mItem    选择幼儿的位置数
     * @param position 选择的类型
     *                 <p>
     *                 第一步，查找选中的幼儿是否为上车，
     *                 第二步，上车后，找到幼儿的下车站点
     *                 第三步，查找当前的map中是否有当前下车站点
     */
    public static void setJieData(Map<String, List<ChildBean.RerurnValueBean>> map, StaBean staBean, int mItem, int position) {
        //第一步
        List<StaBean> stationlist = (List<StaBean>) sp.queryForSharedToObject(Keyword.STAIDLIST);
        List<StaBean> bean = (List<StaBean>) sp.queryForSharedToObject(Keyword.SELECTSTA);
        boolean flag = false;
        if (position == 1) {


            int stationId = map.get(staBean.getStrid()).get(mItem).getConnectEndStation();//下车站点ID
            String strId = stationId + "02";
            StaBean station = new StaBean();
            for (int i = 0; i < stationlist.size(); i++) {
                if (stationlist.get(i).getStrid().equals(strId)) {
                    station = stationlist.get(i);
                    break;
                }
            }

            List<ChildBean.RerurnValueBean> list = map.get(strId);
            if (list == null) {
                list = new ArrayList<>();
                list.add(map.get(staBean.getStrid()).get(mItem));
                for (int i = 0; i < bean.size(); i++) {
                    if (bean.get(i).getId() == station.getId()) {
                        bean.add(i + 1, station);
                        flag = true;
                        break;
                    }
                }
                if (!flag){
                    bean.add(station);
                }
            } else {
                list.add(map.get(staBean.getStrid()).get(mItem));
            }
            map.put(strId, list);

        }


        map.get(staBean.getStrid()).get(mItem).setSelectid(position);
        sp.saveToShared(Keyword.SELECTSTA, bean);
        sp.saveToShared(Keyword.MAPLIST, map);
    }
}
