package com.idream.commons.lib.dto.region;

import com.idream.commons.lib.dto.marketing.LotteryDetailTimeDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @Author: houcc
 * @Date: 2018/7/13
 */
@ApiModel(value = "开奖宝箱返回dto")
public class UnityLotteryDto {
    @ApiModelProperty(value = "宝箱编号 lotteryId")
    private Integer lotteryId;
    @ApiModelProperty(value = "活动名称")
    private String activityName;
    @ApiModelProperty(value = "社区id")
    private Integer communityId;
    @ApiModelProperty(value = "活动内容")
    private String content;
    @ApiModelProperty(value = "开奖时间段")
    private List<LotteryDetailTimeDto> details;

    public Integer getLotteryId() {
        return lotteryId;
    }

    public void setId(Integer lotteryId) {
        this.lotteryId = lotteryId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public List<LotteryDetailTimeDto> getDetails() {
        return details;
    }

    public void setDetails(List<LotteryDetailTimeDto> details) {
        this.details = details;
    }
}
