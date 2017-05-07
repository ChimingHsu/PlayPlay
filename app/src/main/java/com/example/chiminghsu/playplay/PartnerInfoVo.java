package com.example.chiminghsu.playplay;

import android.widget.LinearLayout;

import java.io.Serializable;

/**
 * Created by Chiming Hsu on 2017/4/25.
 */

public class PartnerInfoVo implements Serializable {
    private String memberId;
    private String positionInfo;
    private int bigHeadID=0;
    private LinearLayout itemLyout;



    public PartnerInfoVo(String memberId,String positionInfo,int bigHeadID) {
        this.bigHeadID = bigHeadID;
        this.memberId = memberId;
        this.positionInfo = positionInfo;
    }

    public void setBigHeadID(int bigHeadID) {
        this.bigHeadID = bigHeadID;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setPositionInfo(String positionInfo) {
        this.positionInfo = positionInfo;
    }

    public void setItemLyout(LinearLayout itemLyout) {
        this.itemLyout = itemLyout;
    }

    public LinearLayout getItemLyout() {
        return itemLyout;
    }

    public String getPositionInfo() {
        return positionInfo;
    }

    public String getMemberId() {
        return memberId;
    }

    public int getBigHeadID() {
        return bigHeadID;
    }

}
