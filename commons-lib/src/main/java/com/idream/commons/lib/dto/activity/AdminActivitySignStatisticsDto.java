package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
@ApiModel("查询活动报名数据返回")
public class AdminActivitySignStatisticsDto {
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "报名客户端 1-ios ;2-andriod; 3-微信小程序；4-web 5 unkown")
    private Integer deviceType;
    @ApiModelProperty(value = "报名时间")
    private Date signDate;
    @ApiModelProperty(value = "活动标题")
    private String activityTitle;
    @ApiModelProperty(value = "发布书屋")
    private String publishBook;

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

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getPublishBook() {
        return publishBook;
    }

    public void setPublishBook(String publishBook) {
        this.publishBook = publishBook;
    }
}
