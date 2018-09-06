package com.idream.commons.lib.dto.activity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询主题下的留言明细返回参数")
public class FindThemeMessageResponseDto {

    @ApiModelProperty("活动标题")
    private String activityTitle;
    @ApiModelProperty("主题标题")
    private String themeTitle;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("留言信息")
    private String message;
    @ApiModelProperty("留言时间")
    private Date createTime;
    @ApiModelProperty("点赞数")
    private Integer likeCount;

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getThemeTitle() {
        return themeTitle;
    }

    public void setThemeTitle(String themeTitle) {
        this.themeTitle = themeTitle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

}
