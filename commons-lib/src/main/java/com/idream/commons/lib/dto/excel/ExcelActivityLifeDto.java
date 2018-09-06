package com.idream.commons.lib.dto.excel;

import java.util.Date;

public class ExcelActivityLifeDto {
    private Integer userId;
    private String typeName;
    private String content;
    private String strImage;
    private Integer activityId;
    private Date createTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStrImage() {
        return strImage;
    }

    public void setStrImage(String strImage) {
        this.strImage = strImage;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
