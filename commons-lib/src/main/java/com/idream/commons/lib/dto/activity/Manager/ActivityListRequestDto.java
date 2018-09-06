package com.idream.commons.lib.dto.activity.Manager;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: penghekai
 * @Date: 2018/7/6 09:37
 * @Description:
 */
@ApiModel(value = "活动列表首页条件查询请求参数")
public class ActivityListRequestDto extends PagesParam {

    @ApiModelProperty(value = "用户id", hidden = true)
    private Integer userId;
    @ApiModelProperty(value = "活动标题")
    private String activityTitle;
    @ApiModelProperty(value = "活动类型id")
    private Integer typeId;
    @ApiModelProperty(value = "社区id")
    private Integer regionId;
    @ApiModelProperty(value = "用户所属书屋id", hidden = true)
    private Integer bookId;
    @ApiModelProperty(value = "活动状态 1未发布 2待开始,3进行中,4已结束,5待提交")
    private Byte activityStatus;
    @ApiModelProperty(value = "审核状态 1审核中 2审核成功,3审核失败")
    private Byte checkStatus;
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

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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

    public Byte getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Byte checkStatus) {
        this.checkStatus = checkStatus;
    }
}

