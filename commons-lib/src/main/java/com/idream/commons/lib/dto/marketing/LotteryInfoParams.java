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
public class LotteryInfoParams {

    /**
     *
     */
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 社区id
     */
    @ApiModelProperty(value = "区编码")
    private String adCode;

    /**
     * 社区id
     */
    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    /**
     * 社区id
     */
    @ApiModelProperty(value = "社区名称")
    private Integer communityName;

    /**
     * 城市ID
     */
    @ApiModelProperty(value = "城市ID")
    private String cityId;

    /**
     * 城市
     */
    @ApiModelProperty(value = "城市名")
    private String city;

    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动名称")
    private Integer activityName;

    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动状态")
    private Integer activityStatus;

    /**
     * 生成时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 开奖明细
     */
    @ApiModelProperty(value = "开奖明细")
    private List<LotteryDetailParams> lotteryDetailList;

    /**
     * 奖品明细
     */
    @ApiModelProperty(value = "奖品明细")
    private List<LotteryPoolParams> lotteryPoolList;

    @ApiModelProperty(value = "开奖类型")
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getCommunityName() {
        return communityName;
    }

    public void setCommunityName(Integer communityName) {
        this.communityName = communityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getActivityName() {
        return activityName;
    }

    public void setActivityName(Integer activityName) {
        this.activityName = activityName;
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

    public List<LotteryPoolParams> getLotteryPoolList() {
        return lotteryPoolList;
    }

    public void setLotteryPoolList(List<LotteryPoolParams> lotteryPoolList) {
        this.lotteryPoolList = lotteryPoolList;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
