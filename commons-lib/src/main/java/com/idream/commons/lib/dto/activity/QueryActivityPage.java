package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.PagesParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("活动列表首页条件查询请求参数")
public class QueryActivityPage extends PagesParam {
    @ApiModelProperty(value = "活动标题")
    private String activityTitle;
    @ApiModelProperty(value = "活动类型id")
    private Integer typeId;
    @ApiModelProperty(value = "省code")
    private String provinceCode;
    @ApiModelProperty(value = "市code")
    private String cityCode;
    @ApiModelProperty(value = "区code")
    private String adCode;
    @ApiModelProperty(value = "社区名")
    private String communityName;
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty("书屋名字")
    private String bookName;
    @ApiModelProperty(value = "活动状态 1未发布 2待开始,3进行中,4已结束")
    private Byte activityStatus;
    @ApiModelProperty(value = "有无群聊 0无，1有")
    private Byte existGroup;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Byte getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Byte activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Byte getExistGroup() {
        return existGroup;
    }

    public void setExistGroup(Byte existGroup) {
        this.existGroup = existGroup;
    }
}
