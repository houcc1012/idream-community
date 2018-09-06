package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 成就奖品池
 */
@ApiModel("成就奖品池")
public class AchievementPool {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 成就id
     */
    @ApiModelProperty(value = "成就id")
    private Integer achieveId;

    /**
     * 1称号
     */
    @ApiModelProperty(value = "1称号")
    private Byte type;

    /**
     * 奖品名
     */
    @ApiModelProperty(value = "奖品名")
    private String name;

    /**
     * 奖品值
     */
    @ApiModelProperty(value = "奖品值")
    private String awardValue;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 说明
     */
    @ApiModelProperty(value = "说明")
    private String instructions;

    /**
     * 过期天数
     */
    @ApiModelProperty(value = "过期天数")
    private Integer expireDay;

    /**
     * 时间类型,1永久,2定时
     */
    @ApiModelProperty(value = "时间类型,1永久,2定时")
    private Byte timeType;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 结束展示时间
     */
    @ApiModelProperty(value = "结束展示时间")
    private Date endTime;

    /**
     * 数量,-1表示不限制数据
     */
    @ApiModelProperty(value = "数量,-1表示不限制数据")
    private Integer count;

    /**
     * 是否收集信息,默认0不开启,1开启
     */
    @ApiModelProperty(value = "是否收集信息,默认0不开启,1开启")
    private Boolean infoEnable;

    /**
     * 1上架,2下架
     */
    @ApiModelProperty(value = "1上架,2下架")
    private Byte status;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    private String image;

    /**
     * 生成时间
     */
    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAchieveId() {
        return achieveId;
    }

    public void setAchieveId(Integer achieveId) {
        this.achieveId = achieveId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAwardValue() {
        return awardValue;
    }

    public void setAwardValue(String awardValue) {
        this.awardValue = awardValue == null ? null : awardValue.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions == null ? null : instructions.trim();
    }

    public Integer getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(Integer expireDay) {
        this.expireDay = expireDay;
    }

    public Byte getTimeType() {
        return timeType;
    }

    public void setTimeType(Byte timeType) {
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getInfoEnable() {
        return infoEnable;
    }

    public void setInfoEnable(Boolean infoEnable) {
        this.infoEnable = infoEnable;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
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
}