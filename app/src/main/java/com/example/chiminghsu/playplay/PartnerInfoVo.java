package com.example.chiminghsu.playplay;

import java.io.Serializable;

/**
 * Created by Chiming Hsu on 2017/4/25.
 */

public class PartnerInfoVo implements Serializable {
    private String memberId;
    private String positionInfo;
    private int bigHeadID=0;

    public PartnerInfoVo(String memberId,String positionInfo,int bigHeadID) {
        this.bigHeadID = bigHeadID;
        this.memberId = memberId;
        this.positionInfo = positionInfo;
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
