package com.fwk.school4.model;

import java.io.Serializable;

/**
 * Created by fanwenke on 16/12/12.
 */

public class StationModeBean implements Serializable {

    private int id;
    private String nameDown;
    private String nameUp;
    private String stationiddown;
    private String stationidup;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getStationiddown() {
        return stationiddown;
    }

    public String getStationidup() {
        return stationidup;
    }

    public void setStationiddown(String stationiddown) {
        this.stationiddown = stationiddown;
    }

    public void setStationidup(String stationidup) {
        this.stationidup = stationidup;
    }

    public String getNameDown() {
        return nameDown;
    }

    public void setNameDown(String nameDown) {
        this.nameDown = nameDown;
    }

    public String getNameUp() {
        return nameUp;
    }

    public void setNameUp(String nameUp) {
        this.nameUp = nameUp;
    }

    @Override
    public String toString() {
        return "StationModeBean{" +
                "id=" + id +
                ", nameDown='" + nameDown + '\'' +
                ", nameUp='" + nameUp + '\'' +
                ", stationiddown='" + stationiddown + '\'' +
                ", stationidup='" + stationidup + '\'' +
                '}';
    }
}
