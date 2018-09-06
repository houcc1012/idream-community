package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: PublishDto.java</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.idream.com</p >
 *
 * @author penghekai
 * @version 1.0
 */
@ApiModel(value = "活动发布接收实体")
public class PublishDto {

    @ApiModelProperty(value = "活动id")
    private Integer activityId;
    @ApiModelProperty(value = "活动类型id")
    private Integer typeId;
    @NotBlank(message = "活动标题不能为空")
    @Length(max = 30, message = "活动标题不超过30个字符")
    @ApiModelProperty(value = "活动标题")
    private String title;
    @NotBlank(message = "活动副标题不能为空")
    @Length(max = 140, message = "活动副标题不超过140个字符")
    @ApiModelProperty(value = "副标题")
    private String subtitle;
    @NotBlank(message = "活动封面不能为空")
    @ApiModelProperty(value = "活动封面url")
    private String image;
    @NotBlank(message = "活动内容不能为空")
    @ApiModelProperty(value = "活动内容")
    @Length(max = 10000, message = "活动内容过长")
    private String content;
    @ApiModelProperty(value = "用户id", hidden = true)
    private Integer userId;
    @ApiModelProperty("活动级别 1全国,2城市,3社区")
    private byte level;
    @ApiModelProperty(value = "城市")
    private String city;
    @ApiModelProperty(value = "城市编码")
    private String cityCode;
    @ApiModelProperty(value = "高级社区id")
    private List<Integer> regionIds;
    @ApiModelProperty(value = "小区id")
    private List<Integer> regionGroupIds;
    @Length(max = 50, message = "活动地址不超过50个字符")
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "默认报名设置集合")
    private List<AdminPublishActivityInfoRuleRequestDto> defaultInfoList;
    @ApiModelProperty(value = "自定义报名设置集合")
    private List<AdminPublishActivityInfoRuleRequestDto> customInfoList;
    @ApiModelProperty(value = "活动类型 1默认官方")
    private Integer type;
    @NotNull(message = "活动时间类型不能为空")
    @ApiModelProperty(value = "活动时间类型")
    private Integer timeType;
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    @ApiModelProperty(value = "开始日期")
    private Date startDate;
    @ApiModelProperty(value = "结束日期")
    private Date endDate;
    @ApiModelProperty(value = "日期")
    private List<MultiDate> multiDates;
    @ApiModelProperty(value = "每周的哪几天，如1,2,3")
    private String week;
    @ApiModelProperty(value = "活动标签id集合")
    @Size(min = 1, message = "活动标签不能为空")
    private List<Integer> tagIds;
    @NotNull(message = "报名人数不能为空")
    @ApiModelProperty(value = "参加人数,默认0,不限制")
    @Max(value = 9999, message = "报名人数不能超过9999")
    private Integer count;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public byte getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public List<Integer> getRegionIds() {
        return regionIds;
    }

    public void setRegionIds(List<Integer> regionIds) {
        this.regionIds = regionIds;
    }

    public List<Integer> getRegionGroupIds() {
        return regionGroupIds;
    }

    public void setRegionGroupIds(List<Integer> regionGroupIds) {
        this.regionGroupIds = regionGroupIds;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<AdminPublishActivityInfoRuleRequestDto> getDefaultInfoList() {
        return defaultInfoList;
    }

    public void setDefaultInfoList(List<AdminPublishActivityInfoRuleRequestDto> defaultInfoList) {
        this.defaultInfoList = defaultInfoList;
    }

    public List<AdminPublishActivityInfoRuleRequestDto> getCustomInfoList() {
        return customInfoList;
    }

    public void setCustomInfoList(List<AdminPublishActivityInfoRuleRequestDto> customInfoList) {
        this.customInfoList = customInfoList;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTimeType() {
        return timeType;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
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

    public List<MultiDate> getMultiDates() {
        return multiDates;
    }

    public void setMultiDates(List<MultiDate> multiDates) {
        this.multiDates = multiDates;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}