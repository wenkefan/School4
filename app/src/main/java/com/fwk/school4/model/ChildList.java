package com.fwk.school4.model;

import java.io.Serializable;

/**
 * Created by fanwenke on 16/8/19.
 */
public class ChildList implements Serializable {
    private int childid;//幼儿ID
    private String childname;//幼儿名称
    private String fathername;//父亲名称
    private String fatherphone;//父亲电话
    private String mothername;//妈妈名称
    private String motherphone;//妈妈电话
    private String SACarId;//幼儿卡号
    private int connectStation;//接站点
    private int SendStation;//送站点
    private int Selectid;//症状
    private boolean isOperation;//是否以操作

    private int listid;
    private int itme;

    public int getListid() {
        return listid;
    }

    public void setListid(int listid) {
        this.listid = listid;
    }

    public int getItme() {
        return itme;
    }

    public void setItme(int itme) {
        this.itme = itme;
    }

    public int getSelectid() {
        return Selectid;
    }

    public void setSelectid(int selectid) {
        Selectid = selectid;
    }

    public boolean getOperation() {
        return isOperation;
    }

    public void setOperation(boolean operation) {
        isOperation = operation;
    }

    public int getChildid() {
        return childid;
    }

    public void setChildid(int childid) {
        this.childid = childid;
    }

    public String getChildname() {
        return childname;
    }

    public void setChildname(String childname) {
        this.childname = childname;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getFatherphone() {
        return fatherphone;
    }

    public void setFatherphone(String fatherphone) {
        this.fatherphone = fatherphone;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getMotherphone() {
        return motherphone;
    }

    public void setMotherphone(String motherphone) {
        this.motherphone = motherphone;
    }

    public String getSACarId() {
        return SACarId;
    }

    public void setSACarId(String SACarId) {
        this.SACarId = SACarId;
    }

    public int getConnectStation() {
        return connectStation;
    }

    public void setConnectStation(int connectStation) {
        this.connectStation = connectStation;
    }

    public int getSendStation() {
        return SendStation;
    }

    public void setSendStation(int sendStation) {
        SendStation = sendStation;
    }
}
