package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Description :
 * Created by DELL2018-003 on 2018/4/13.
 */
@ApiModel(value = "开奖列表")
public class LotteryInfoDto {

    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "区编码")
    private String adCode;

    @ApiModelProperty(value = "区名称")
    private String adName;

    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    @ApiModelProperty(value = "社区名称")
    private String communityName;

    @ApiModelProperty(value = "城市ID")
    private String cityId;

    @ApiModelProperty(value = "城市名")
    private String city;

    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    @ApiModelProperty(value = "开奖类型")
    private Integer type;

    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "活动状态")
    private Integer activityStatus;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "开奖明细")
    private List<LotteryDetailParams> lotteryDetailList;

    @ApiModelProperty(value = "开奖状态(1.进行中，2.未开始，3.已结束)")
    private Integer lotteryStatus;

    @ApiModelProperty(value = "介绍")
    private String instructions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<LotteryDetailParams> getLotteryDetailList() {
        return lotteryDetailList;
    }

    public void setLotteryDetailList(List<LotteryDetailParams> lotteryDetailList) {
        this.lotteryDetailList = lotteryDetailList;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLotteryStatus() {
        return lotteryStatus;
    }

    public void setLotteryStatus(Integer lotteryStatus) {
        this.lotteryStatus = lotteryStatus;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
