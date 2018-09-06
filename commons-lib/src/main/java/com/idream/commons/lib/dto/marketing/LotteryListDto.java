package com.idream.commons.lib.dto.marketing;

import com.idream.commons.lib.model.LotteryInformationRelation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Description :
 * Created by DELL2018-003 on 2018/4/13.
 */
@ApiModel(value = "开奖列表")
public class LotteryListDto {

    @ApiModelProperty(value = "lotteryInfoId")
    private Integer lotteryId;

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
    private String communityName;

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
    private String activityName;

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

    /**
     * 认证项
     */
    @ApiModelProperty(value = "认证项")
    private List<LotteryInformationRelation> lotteryInformationRelationList;


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

    public List<LotteryInformationRelation> getLotteryInformationRelationList() {
        return lotteryInformationRelationList;
    }

    public void setLotteryInformationRelationList(List<LotteryInformationRelation> lotteryInformationRelationList) {
        this.lotteryInformationRelationList = lotteryInformationRelationList;
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

    public Integer getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Integer lotteryId) {
        this.lotteryId = lotteryId;
    }
}
