package com.idream.commons.lib.dto.activity;

import java.util.List;

public class ActivityInfoListDto {

    //城市
    private String city;

    //判断定位当前城市是否有活动列表
    //1:有活动,2:无活动
    private Integer listStatus;

    //活动列表list
    private List<ActivityDataDto> activityInfoList;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getListStatus() {
        return listStatus;
    }

    public void setListStatus(Integer listStatus) {
        this.listStatus = listStatus;
    }

    public List<ActivityDataDto> getActivityInfoList() {
        return activityInfoList;
    }

    public void setActivityInfoList(List<ActivityDataDto> activityInfoList) {
        this.activityInfoList = activityInfoList;
    }
}
