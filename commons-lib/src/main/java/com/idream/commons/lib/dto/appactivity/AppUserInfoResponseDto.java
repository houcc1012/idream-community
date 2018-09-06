package com.idream.commons.lib.dto.appactivity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户参加活动最近录入信息")
public class AppUserInfoResponseDto {

    @ApiModelProperty("信息规则id")
    private Integer infoId;
    @ApiModelProperty("字段")
    private String name;
    @ApiModelProperty("详情")
    private String detail;
    @ApiModelProperty("年龄标签")
    private String ageInfo;
    @ApiModelProperty("填写时间")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAgeInfo() {
        return ageInfo;
    }

    public void setAgeInfo(String ageInfo) {
        this.ageInfo = ageInfo;
    }

}
