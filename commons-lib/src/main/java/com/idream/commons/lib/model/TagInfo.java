package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 标签表
 *
 * @author idream
 *         描述： 自动生成的代码
 */
@ApiModel("标签表")
public class TagInfo {

    @ApiModelProperty(value = "标签id")
    private Integer id;

    @ApiModelProperty(value = "标签名称")
    private String label;

    @ApiModelProperty(value = "活动类型")
    private Integer typeId;

    private Integer attribution;

    //
    private Integer pid;

    //
    private Date createTime;

    //
    private Date updateTime;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getTypeId() {
        return this.typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getAttribution() {
        return this.attribution;
    }

    public void setAttribution(Integer attribution) {
        this.attribution = attribution;
    }

    public Integer getPid() {
        return this.pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


}
