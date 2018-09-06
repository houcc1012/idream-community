package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.admin.ActivityOperateRecordResponseDto;
import com.idream.commons.lib.model.ActivityCancelRecord;
import com.idream.commons.lib.model.ActivityOperateRecord;
import com.idream.commons.lib.model.ActivityTag;
import com.idream.commons.lib.model.RegionGroupInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther: penghekai
 * @Date: 2018/6/28 14:37
 * @Description:
 */
@ApiModel("管理端活动详情返回参数")
public class AdminActivityDisplayDto {

    @ApiModelProperty(value = "活动id")
    private Integer activityId;
    @ApiModelProperty(value = "活动类型id")
    private Integer typeId;
    @ApiModelProperty(value = "活动标题")
    private String title;
    @ApiModelProperty(value = "副标题")
    private String subtitle;
    @ApiModelProperty(value = "活动封面url")
    private String image;
    @ApiModelProperty(value = "活动内容")
    private String content;
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty("活动级别 1全国,2城市,3社区,4小区")
    private byte level;
    @ApiModelProperty(value = "城市")
    private String city;
    @ApiModelProperty(value = "城市编码")
    private String cityCode;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "默认报名设置集合")
    private List<AdminPublishActivityInfoRuleRequestDto> defaultInfoList;
    @ApiModelProperty(value = "自定义报名设置集合")
    private List<AdminPublishActivityInfoRuleRequestDto> customInfoList;
    @ApiModelProperty(value = "活动类型 1默认官方")
    private Integer type;
    @ApiModelProperty(value = "活动时间类型")
    private Integer timeType;
    @ApiModelProperty(value = "开始时间")
    private Long startTime;
    @ApiModelProperty(value = "结束时间")
    private Long endTime;
    @ApiModelProperty(value = "开始日期")
    private Long startDate;
    @ApiModelProperty(value = "结束日期")
    private Long endDate;
    @ApiModelProperty(value = "日期")
    private List<MultiDate> multiDates;
    @ApiModelProperty(value = "每周的哪几天，如1,2,3")
    private String week;
    @ApiModelProperty(value = "活动标签id集合")
    private List<Integer> tagIds;
    @ApiModelProperty(value = "参加人数,默认0,不限制")
    private Integer count;
    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "省编码")
    private String provinceCode;
    @ApiModelProperty(value = "区域")
    private String district;
    @ApiModelProperty(value = "区域编码")
    private String districtCode;
    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;
    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;
    @ApiModelProperty("是否有取消记录 0没有，1有")
    private byte isCancel;
    @ApiModelProperty("活动取消记录")
    private List<ActivityOperateRecordResponseDto> cancelRecords;
    @ApiModelProperty("是否有审核记录 0没有，1有")
    private byte isCheck;
    @ApiModelProperty("活动审核记录")
    private List<ActivityOperateRecordResponseDto> checkRecords;
    @ApiModelProperty("报名参加活动的人数")
    private Integer willNum;
    @ApiModelProperty("所在大社区以及小社区")
    private List<RegionInfoExtention> regionInfoExtentionDtoList;
    @ApiModelProperty("所有已选中小社区")
    private List<RegionGroupInfo> regionGroupSelectList;
    @ApiModelProperty("二级类型以及子标签")
    private List<AppActivityTypeRelateTagResponseDto> secondTagList;
    @ApiModelProperty("已选中的二级标签")
    private List<ActivityTag> secondTagSelectList;
    @ApiModelProperty("已选中的三级标签")
    private List<ActivityTag> thirdTagSelectList;

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

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
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

    public byte getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(byte isCancel) {
        this.isCancel = isCancel;
    }

    public byte getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(byte isCheck) {
        this.isCheck = isCheck;
    }

    public List<ActivityOperateRecordResponseDto> getCancelRecords() {
        return cancelRecords;
    }

    public void setCancelRecords(List<ActivityOperateRecordResponseDto> cancelRecords) {
        this.cancelRecords = cancelRecords;
    }

    public List<ActivityOperateRecordResponseDto> getCheckRecords() {
        return checkRecords;
    }

    public void setCheckRecords(List<ActivityOperateRecordResponseDto> checkRecords) {
        this.checkRecords = checkRecords;
    }

    public Integer getWillNum() {
        return willNum;
    }

    public void setWillNum(Integer willNum) {
        this.willNum = willNum;
    }

    public List<RegionInfoExtention> getRegionInfoExtentionDtoList() {
        return regionInfoExtentionDtoList;
    }

    public void setRegionInfoExtentionDtoList(List<RegionInfoExtention> regionInfoExtentionDtoList) {
        this.regionInfoExtentionDtoList = regionInfoExtentionDtoList;
    }

    public List<RegionGroupInfo> getRegionGroupSelectList() {
        return regionGroupSelectList;
    }

    public void setRegionGroupSelectList(List<RegionGroupInfo> regionGroupSelectList) {
        this.regionGroupSelectList = regionGroupSelectList;
    }

    public List<AppActivityTypeRelateTagResponseDto> getSecondTagList() {
        return secondTagList;
    }

    public void setSecondTagList(List<AppActivityTypeRelateTagResponseDto> secondTagList) {
        this.secondTagList = secondTagList;
    }

    public List<ActivityTag> getSecondTagSelectList() {
        return secondTagSelectList;
    }

    public void setSecondTagSelectList(List<ActivityTag> secondTagSelectList) {
        this.secondTagSelectList = secondTagSelectList;
    }

    public List<ActivityTag> getThirdTagSelectList() {
        return thirdTagSelectList;
    }

    public void setThirdTagSelectList(List<ActivityTag> thirdTagSelectList) {
        this.thirdTagSelectList = thirdTagSelectList;
    }
}

