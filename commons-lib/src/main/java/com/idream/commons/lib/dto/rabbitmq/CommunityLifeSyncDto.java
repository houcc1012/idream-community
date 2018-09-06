package com.idream.commons.lib.dto.rabbitmq;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author hejiang
 */
public class CommunityLifeSyncDto implements Serializable {

    private int lifeId;

    //发布用户的ID
    private int userId;

    //隐私级别
    private Byte privacyLevel;

    //指定社区ID
    private List<Integer> regionIds;

    //发布时间
    private Date createTime;

    public CommunityLifeSyncDto() {
    }

    public CommunityLifeSyncDto(int lifeId, int userId, Byte privacyLevel, List<Integer> regionIds, Date createTime) {
        this.lifeId = lifeId;
        this.userId = userId;
        this.privacyLevel = privacyLevel;
        this.regionIds = regionIds;

        this.createTime = createTime;
    }

    public List<Integer> getRegionIds() {
        return regionIds;
    }

    public void setRegionIds(List<Integer> regionIds) {
        this.regionIds = regionIds;
    }

    public Byte getPrivacyLevel() {
        return privacyLevel;
    }

    public void setPrivacyLevel(Byte privacyLevel) {
        this.privacyLevel = privacyLevel;
    }

    public int getLifeId() {
        return lifeId;
    }

    public void setLifeId(int lifeId) {
        this.lifeId = lifeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
