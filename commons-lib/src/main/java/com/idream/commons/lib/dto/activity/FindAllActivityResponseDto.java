package com.idream.commons.lib.dto.activity;

import java.util.Date;
import java.util.List;

import com.idream.commons.lib.model.ActivityGroupRelation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author penghekai
 */
@ApiModel(value = "所有活动列表展示返回参数")
public class FindAllActivityResponseDto {

    @ApiModelProperty(value = "活动id")
    private Integer activityId;
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "活动类型")
    private String activityType;
    @ApiModelProperty(value = "活动标题")
    private String title;
    @ApiModelProperty(value = "所在城市")
    private String city;
    @ApiModelProperty(value = "所在区域")
    private String district;
    @ApiModelProperty(value = "所在社区")
    private List<ActivityGroupRelation> regions;
    @ApiModelProperty(value = "时间规则")
    private Integer timeRule;
    @ApiModelProperty(value = "活动日期")
    private List<MultiDate> multiDate;
    @ApiModelProperty(value = "活动周期")
    private String weekDay;
    @ApiModelProperty(value = "活动开始时间")
    private Date startTime;
    @ApiModelProperty(value = "活动结束时间")
    private Date endTime;
    @ApiModelProperty(value = "开始日期")
    private Date startDate;
    @ApiModelProperty(value = "结束日期")
    private Date endDate;
    @ApiModelProperty(value = "发布者")
    private String publisher;
    @ApiModelProperty(value = "发布平台")
    private String platForm;
    @ApiModelProperty(value = "是否创建了趣聊 0否 1是")
    private byte existGroup;
    @ApiModelProperty("群聊名称")
    private String chatName;
    @ApiModelProperty(value = "活动主题数量")
    private Integer themeCount;
    @ApiModelProperty(value = "1创建完成,2提交审核,3审核通过4上架,5下架,6审核失败' 活动状态 1待提交 2审核中,3未发布,4通过：待开始 进行中 已结束")
    private Integer status;
    @ApiModelProperty(value = "1未发布 2待开始,3进行中,4已结束   中台：1待开始  2进行中 3已结束")
    private Integer activityStatus;
    @ApiModelProperty(value = "1待审核 2审核中，3审核成功 4审核失败")
    private Integer checkStatus;
    @ApiModelProperty(value = "是否有取消记录")
    private byte isCancel;
    @ApiModelProperty(value = "活动来源 0后台 1平台")
    private byte fromType;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public byte getExistGroup() {
        return existGroup;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public void setExistGroup(byte existGroup) {
        this.existGroup = existGroup;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<ActivityGroupRelation> getRegions() {
        return regions;
    }

    public void setRegions(List<ActivityGroupRelation> regions) {
        this.regions = regions;
    }


    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getThemeCount() {
        return themeCount;
    }

    public void setThemeCount(Integer themeCount) {
        this.themeCount = themeCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<MultiDate> getMultiDate() {
        return multiDate;
    }

    public void setMultiDate(List<MultiDate> multiDate) {
        this.multiDate = multiDate;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getTimeRule() {
        return timeRule;
    }

    public void setTimeRule(Integer timeRule) {
        this.timeRule = timeRule;
    }

    public String getPlatForm() {
        return platForm;
    }

    public void setPlatForm(String platForm) {
        this.platForm = platForm;
    }

    public byte getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(byte isCancel) {
        this.isCancel = isCancel;
    }

    public byte getFromType() {
        return fromType;
    }

    public void setFromType(byte fromType) {
        this.fromType = fromType;
    }
}
