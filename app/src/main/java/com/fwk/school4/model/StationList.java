package com.fwk.school4.model;

import java.io.Serializable;

/**
 * Created by fanwenke on 16/8/19.
 */
public class StationList implements Serializable {
    private int stationId;//站点ID
    private int duration;//下一站预计时间
    private String StationName;//站点名称

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStationName() {
        return StationName;
    }

    public void setStationName(String stationName) {
        StationName = stationName;
    }
}
