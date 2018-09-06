package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 *
 */
@ApiModel("")
public class EventType {
    /**
     *
     */
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private String name;

    /**
     * 类型,1基础,2活动,3动态
     */
    @ApiModelProperty(value = "类型,1基础,2活动,3动态")
    private Byte type;

    /**
     * 父级
     */
    @ApiModelProperty(value = "父级")
    private Integer pid;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private Integer extraValue;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private Date createTime;

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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getExtraValue() {
        return extraValue;
    }

    public void setExtraValue(Integer extraValue) {
        this.extraValue = extraValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}