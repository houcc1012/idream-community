package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * Description :
 * Created by DELL2018-003 on 2018/4/13.
 */
@ApiModel(value = "开奖列表")
public class AddLotteryParams {

    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "区编码")
    private String adCode;

    @ApiModelProperty(value = "社区id")
    @NotNull(message = "社区id不能为空")
    private Integer communityId;

    @ApiModelProperty(value = "社区名称")
    private String communityName;

    @ApiModelProperty(value = "城市ID")
    private String cityId;

    @ApiModelProperty(value = "城市名")
    private String city;

    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "活动状态")
    private Byte activityStatus;

    @ApiModelProperty(value = "开始时间")
    @NotNull(message = "开始时间不能为空")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    @NotNull(message = "结束时间不能为空")
    private Date endTime;

    @ApiModelProperty(value = "开奖明细")
    @Valid
    private List<LotteryDetailParams> lotteryDetailList;

    @ApiModelProperty(value = "开奖类型")
    @NotNull(message = "开奖类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "是否绑定活动，默认false")
    private boolean banded;

    @ApiModelProperty(value = "介绍")
    @NotBlank(message = "介绍不能为空")
    @Size(max = 5000, message = "介绍不能超过5000字")
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

    public Byte getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Byte activityStatus) {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public boolean isBanded() {
        return banded;
    }

    public void setBanded(boolean banded) {
        this.banded = banded;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
