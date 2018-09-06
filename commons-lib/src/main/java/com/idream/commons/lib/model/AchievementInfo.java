package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 成就信息表
 */
@ApiModel("成就信息表")
public class AchievementInfo {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 事件id
     */
    @ApiModelProperty(value = "事件id")
    private Integer eventId;

    /**
     * 成就名称
     */
    @ApiModelProperty(value = "成就名称")
    private String name;

    /**
     * 成就描述
     */
    @ApiModelProperty(value = "成就描述")
    private String description;

    /**
     * 说明
     */
    @ApiModelProperty(value = "说明")
    private String instructions;

    /**
     * 类型,1
     */
    @ApiModelProperty(value = "类型,1")
    private Byte type;

    /**
     * 成就未完成图片
     */
    @ApiModelProperty(value = "成就未完成图片")
    private String blackImage;

    /**
     * 成就完成图片
     */
    @ApiModelProperty(value = "成就完成图片")
    private String brightImage;

    /**
     * 称号id
     */
    @ApiModelProperty(value = "称号id")
    private Integer designationId;

    /**
     * 次数
     */
    @ApiModelProperty(value = "次数")
    private Integer count;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sorted;

    /**
     * 状态1上架,2下架
     */
    @ApiModelProperty(value = "状态1上架,2下架")
    private Byte status;

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

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getBlackImage() {
        return blackImage;
    }

    public void setBlackImage(String blackImage) {
        this.blackImage = blackImage == null ? null : blackImage.trim();
    }

    public String getBrightImage() {
        return brightImage;
    }

    public void setBrightImage(String brightImage) {
        this.brightImage = brightImage == null ? null : brightImage.trim();
    }

    public Integer getDesignationId() {
        return designationId;
    }

    public void setDesignationId(Integer designationId) {
        this.designationId = designationId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSorted() {
        return sorted;
    }

    public void setSorted(Integer sorted) {
        this.sorted = sorted;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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