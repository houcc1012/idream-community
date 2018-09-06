package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 事件信息
 */
@ApiModel("事件信息")
public class EventInfo {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 事件名称
     */
    @ApiModelProperty(value = "事件名称")
    private String name;

    /**
     * 1成就,2任务
     */
    @ApiModelProperty(value = "1成就,2任务")
    private Byte category;

    /**
     * 1永久,2临时
     */
    @ApiModelProperty(value = "1永久,2临时")
    private Byte state;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 事件状态1上架,2下架
     */
    @ApiModelProperty(value = "事件状态1上架,2下架")
    private Byte status;

    /**
     * 1周期,0累计
     */
    @ApiModelProperty(value = "1周期,0累计")
    private Boolean continueAble;

    /**
     * 周期类型
     */
    @ApiModelProperty(value = "周期类型")
    private Byte cycleType;

    /**
     * 周期次数
     */
    @ApiModelProperty(value = "周期次数")
    private Integer cycleNum;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getCategory() {
        return category;
    }

    public void setCategory(Byte category) {
        this.category = category;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getContinueAble() {
        return continueAble;
    }

    public void setContinueAble(Boolean continueAble) {
        this.continueAble = continueAble;
    }

    public Byte getCycleType() {
        return cycleType;
    }

    public void setCycleType(Byte cycleType) {
        this.cycleType = cycleType;
    }

    public Integer getCycleNum() {
        return cycleNum;
    }

    public void setCycleNum(Integer cycleNum) {
        this.cycleNum = cycleNum;
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