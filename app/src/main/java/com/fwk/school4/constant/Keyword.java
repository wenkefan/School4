package com.fwk.school4.constant;

/**
 * Created by fanwenke on 16/8/16.
 */
public class Keyword {
    //SP的key
    public static final String APPCONTEXT = "APPCONTEXT";
    public static final String SPLOGIN = "SPLOGIN";

    /**-----------------------Login-----------------------**/
    //username
    public static final String LOGIN_USERNAME = "LOGIN_USERNAME";
    //password
    public static final String LOGIN_PASSWORD = "LOGIN_PASSWORD";
    //kgid
    public static final String LOGIN_KGID = "LOGIN_KGID";
    //kgname
    public static final String LOGIN_KGNAME = "LOGIN_KGNAME";
    //name
    public static final String LOGIN_NAME ="LOGIN_NAME";
    //userid
    public static final String LOGIN_USERID = "LOGIN_USERID";
    //WorkerExtensionId
    public static final String LOGIN_WORKEREXTENSIONID = "LOGIN_WORKEREXTENSIONID";
    //设置是否登录
    public static final String LOGIN_ALREADYLOGIN = "LOGIN_ALREADYLOGIN";

    /**-----------------------SharedPreferences-----------------------**/
    //班次
    public static final String SP_BANCI = "SP_BANCI";
    //班次List
    public static final String SP_BANCI_LIST = "SP_BANCI_LIST";
    //班次ID
    public static final String SP_BANCI_ID = "SP_BANCI_ID";
    //方向
    public static final String SP_ATTENDANCEDIRECTIONS = "SP_ATTENDANCEDIRECTIONS";
    //站点
    public static final String SP_STATION = "SP_STATION";
    //站点List
    public static final String SP_STATION_LIST = "SP_STATION_LIST";
    //显示站点列表
    public static final String SP_STATION_MAP = "SP_STATION_MAP";
    //幼儿
    public static final String SP_CHILD = "SP_CHILD";
    //幼儿List
    public static final String SP_CHILD_LIST = "SP_CHILD_LIST";
    //线路编号
    public static final String SP_XIANLU_ID = "SP_XIANLU_ID";

    //当前站点
    public static final String SP_CURRENT_PAGE = "SP_CURRENT_PAGE";
    //选择的幼儿
    public static final String SP_SELECT_CHILD = "SP_SELECT_CHILD";
    //在List<List<childlist>>中所在的位置
    public static final String SP_CHILD_LISTS = "SP_CHILD_LISTS";
    //在<List<childlist>中所在的位置
    public static final String SP_CHILD_ITERM = "SP_CHILD_ITERM";
    //总共站点数
    public static final String SP_TOTLE_STATION = "SP_TOTLE_STATION";

    //选择的状态
    public static final String SP_SELECT_ID = "SP_SELECT_ID";

    //派车单号
    public static final String SP_PAICHEDANHAO = "SP_PAICHEDANHAO";

    //送幼儿第一次发车 获取派车单号
    public static final String SP_FACHE2 = "SP_FACHE2";


    public static final String SP_ISSENDCAR = "SP_ISSENDCAR";


    /************************************关键字*************************************/


    public static final String BEGIN = "BEGIN";

    public static final int FLAGBANCI = 100;//班次
    public static final int FLAGSTATION = 200;//线路
    public static final int FLAGCHILD = 300;//幼儿
    public static final int FLAGFIRSTFACHE = 600;//第一次发车
    public static final int FLAGENDDAOZHAN = 602;//结束

    public static final int FLAGFACHE = 701;//发车
    public static final int FLAGDAOZHAN = 702;//到站


    public static final int FLAGDOWNCAR = 1101;//上车
    public static final int FLAGUPCAR = 1102;//下车

    public static final String STATIONIDLIST = "STATIONIDLIST";//站点ID list
    public static final String STAIDLIST = "STAIDLIST";//站点上下
    public static final String STATIONMODE = "STATIONMODE";

    public static final String POTIONIT = "POTIONIT";

    public static final String CHILDGROUP = "CHILDGROUP";//分组之后的幼儿list
    public static final String HEADERLOCATION = "HEADERLOCATION";//头位置
    public static final String STATIONNAEM = "STATIONNAEM";//站点名称
    public static final String CHILDCOUNT = "CHILDCOUNT";//每个站点需要操作的人数
    public static final String STATIONPOSITION = "STATIONPOSITION";//当前站点

    public static final String THISSATION = "THISSATION";//第几站
    public static final String JUMPPOSITION = "JUMPPOSITION";//跳转到指定位置

    public static final String ISDAOZHAN = "ISDAOZHAN";//判断是否到站

    public static final String SELECTBANCI = "SELECTBANCI";//选择的班次
    public static final String SELECTCHILD = "SELECTCHILD";//选择中的幼儿

    public static final String MAPLIST = "MAPLIST";//分组之后的map
    public static final String SELECTSTA = "SELECTSTA";//有上车的站点

    public static final String GETSJTIME = "GETSJTIME";//实际到站时间
    /**
     * 保存运行状态
     */
    public static final String NEXTSTANAME = "NEXTSTANAME";//保存下一站名称
    public static final String NEXTTIME = "NEXTTIME";//保存预计到站时间
}
