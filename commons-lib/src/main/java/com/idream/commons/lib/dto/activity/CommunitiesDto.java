package com.idream.commons.lib.dto.activity;

import java.math.BigDecimal;

import com.idream.commons.lib.model.CommunityInfo;

public class CommunitiesDto {
    private CommunityInfo communityInfo;

    private BigDecimal distance;

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public CommunityInfo getCommunityInfo() {
        return communityInfo;
    }

    public void setCommunityInfo(CommunityInfo communityInfo) {
        this.communityInfo = communityInfo;
    }


}
