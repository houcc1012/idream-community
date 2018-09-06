package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel("活动信息")
public class CommunityActivityResponseDto {

    @ApiModelProperty(value = "活动id")
    private Integer id;

    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    @ApiModelProperty(value = "活动主题")
    private String title;

    @ApiModelProperty(value = "活动子标题")
    private String subtitle;

    @ApiModelProperty(value = "活动图片")
    private String image;

    @ApiModelProperty(value = "活动内容")
    private String content;

    @ApiModelProperty(value = "活动状态")
    private Integer status;

    @ApiModelProperty(value = "活动开始时间")
    private Date startTime;

    @ApiModelProperty(value = "活动结束时间")
    private Date endTime;

    @ApiModelProperty(value = "活动发起人")
    private Integer userId;

    @ApiModelProperty(value = "活动类别id")
    private Integer typeId;

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "活动城市名")
    private String city;

    @ApiModelProperty(value = "活动创造时间")
    private Date createTime;

    @ApiModelProperty(value = "活动更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "社区经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "社区纬度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "社区地点")
    private String address;

    @ApiModelProperty(value = "社区省份")
    private String province;

    @ApiModelProperty(value = "社区名称")
    private String communityName;

    @ApiModelProperty(value = "社区县/区")
    private String district;

    @ApiModelProperty(value = "判断每个活动相对当前用户的状态 1:2:3 | 立即参加:进入社群:活动结束")
    private Integer exitStatus = 1;

    @ApiModelProperty(value = "2对经纬度之间的距离")
    private BigDecimal distance;

    @ApiModelProperty(value = "时间类型")
    private Integer timeType;

    @ApiModelProperty(value = "周期时间")
    private String weekDay;


    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Integer getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(Integer exitStatus) {
        this.exitStatus = exitStatus;
    }

    public Integer getTimeType() {
        return timeType;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

}
