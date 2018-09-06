package com.idream.commons.lib.dto.activity;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "根据主题id展示主题回显返回参数")
public class FindThemeDetailResponseDto {
    @ApiModelProperty(value = "主题id,修改时需填")
    private Integer themeId;
    @NotBlank(message = "主题标题不能为空")
    @Length(max = 30, message = "标题不超过30个字符")
    @ApiModelProperty(value = "主题标题")
    private String themeTitle;
    @NotBlank(message = "主题封面不能为空")
    @ApiModelProperty(value = "主题封面")
    private String image;
    @ApiModelProperty(value = "打卡时间")
    private List<Long> taskTime;
    @ApiModelProperty(value = "是否打卡", notes = "默认1打卡,0不打卡")
    private Boolean isTask;
    @NotBlank(message = "主题内容不能为空")
    @ApiModelProperty(value = "内容介绍")
    private String content;
    @ApiModelProperty(value = "打卡地点")
    private String taskAddress;
    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;
    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
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

    public List<Long> getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(List<Long> taskTime) {
        this.taskTime = taskTime;
    }

    public Boolean getIsTask() {
        return isTask;
    }

    public void setIsTask(Boolean isTask) {
        this.isTask = isTask;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getTask() {
        return isTask;
    }

    public void setTask(Boolean task) {
        isTask = task;
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
