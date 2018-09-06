package com.idream.commons.lib.dto.marketing;

import com.idream.commons.lib.model.LotteryDetail;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 现场开奖查询
 *
 * @author hejiang
 */
public class SceneOpenAwardInfo {

    @ApiModelProperty(value = "开奖活动ID")
    private Integer id;

    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "地区编码")
    private String adCode;

    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    @ApiModelProperty(value = "开奖开始时间")
    private Date startTime;

    @ApiModelProperty(value = "开奖结束时间")
    private Date endTime;

    @ApiModelProperty(value = "开奖活动状态,1开启,2结束")
    private Integer status;

    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "开奖活动详细信息")
    private List<LotteryDetail> lotteryDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        this.city = city;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
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

    public Integer getStatus() {
        return status;
    }

    public List<LotteryDetail> getLotteryDetails() {
        return lotteryDetails;
    }

    public void setLotteryDetails(List<LotteryDetail> lotteryDetails) {
        this.lotteryDetails = lotteryDetails;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}
