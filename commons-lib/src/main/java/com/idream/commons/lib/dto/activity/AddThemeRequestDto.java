package com.idream.commons.lib.dto.activity;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author penghekai
 */
@ApiModel(value = "创建主题请求参数")
public class AddThemeRequestDto {

    @ApiModelProperty(value = "活动id")
    private Integer activityId;
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @NotBlank(message = "主题标题不能为空")
    @Length(max = 30, message = "标题不超过30个字符")
    @ApiModelProperty(value = "主题标题")
    private String themeTitle;
    @NotBlank(message = "主题封面不能为空")
    @ApiModelProperty(value = "主题封面")
    private String image;
    @ApiModelProperty(value = "是否需要打卡")
    private boolean isTaskRecord;
    @ApiModelProperty(value = "打卡时间")
    private List<Long> taskTime;
    @NotBlank(message = "主题内容不能为空")
    @ApiModelProperty(value = "主题内容")
    private String content;
    @ApiModelProperty(value = "打卡地点")
    private String taskAddress;
    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;
    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getThemeTitle() {
        return themeTitle;
    }

    public void setThemeTitle(String themeTitle) {
        this.themeTitle = themeTitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isTaskRecord() {
        return isTaskRecord;
    }

    public void setTaskRecord(boolean isTaskRecord) {
        this.isTaskRecord = isTaskRecord;
    }

    public List<Long> getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(List<Long> taskTime) {
        this.taskTime = taskTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTaskAddress() {
        return taskAddress;
    }

    public void setTaskAddress(String taskAddress) {
        this.taskAddress = taskAddress;
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
}
