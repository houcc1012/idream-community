package com.idream.commons.lib.dto.marketing;

import com.idream.commons.lib.model.CommunityInfo;

/**
 * Description :
 * Created by DELL2018-003 on 2018/4/18.
 */
public class CommunityParams {

    private int id;

    private CommunityInfo communityInfo;

    private double distance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CommunityInfo getCommunityInfo() {
        return communityInfo;
    }

    public void setCommunityInfo(CommunityInfo communityInfo) {
        this.communityInfo = communityInfo;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
