package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: ZhengGaosheng
 * @date: 2018/7/17 14:41
 * @description:
 */
@ApiModel("活动详情底部状态")
public class ActivityDetailBottomResponseDto {

    @ApiModelProperty("是否有聊天群组 0:没有, 1:有")
    private Integer chat;

    @ApiModelProperty("是否搜藏 0:没有, 1:有")
    private Integer collection;

    @ApiModelProperty("是否参加该活动 0:没有, 1有")
    private Integer joined;

    @ApiModelProperty("活动状态: 1:未开始, 2:进行中, 3:已结束")
    private Integer activityStatus;

    @ApiModelProperty("是否加入聊天群组. 0:没有,1加入")
    private Integer joinedChatGroupStatus;

    @ApiModelProperty("当前活动已经参加了多少人")
    private String currentActivityCountPeople;

    @ApiModelProperty("当前活动允许参加的最大人数.  0表示参加人数不受限制")
    private Integer count;

    public String getCurrentActivityCountPeople() {
        return currentActivityCountPeople;
    }

    public void setCurrentActivityCountPeople(String currentActivityCountPeople) {
        this.currentActivityCountPeople = currentActivityCountPeople;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getJoinedChatGroupStatus() {
        return joinedChatGroupStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Integer getChat() {
        return chat;
    }

    public void setChat(Integer chat) {
        this.chat = chat;
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }

    public Integer getJoined() {
        return joined;
    }

    public void setJoined(Integer joined) {
        this.joined = joined;
    }

    public void setJoinedChatGroupStatus(Integer joinedChatGroupStatus) {
        this.joinedChatGroupStatus = joinedChatGroupStatus;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }
}

