package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/4/28.
 */
@ApiModel(value = "我的动态列表分组")
public class AppMyCommunityLifeByTimeDto {

    @ApiModelProperty(value = "年份")
    private Integer yearNum;

    @ApiModelProperty(value = "动态组列表")
    private List<AppMyCommunityLifeDto> appMyCommunityLifeList;

    public List<AppMyCommunityLifeDto> getAppMyCommunityLifeList() {
        return appMyCommunityLifeList;
    }

    public void setAppMyCommunityLifeList(List<AppMyCommunityLifeDto> appMyCommunityLifeList) {
        this.appMyCommunityLifeList = appMyCommunityLifeList;
    }

    public Integer getYearNum() {
        return yearNum;
    }

    public void setYearNum(Integer yearNum) {
        this.yearNum = yearNum;
    }
}
