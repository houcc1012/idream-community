package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 标签表
 *
 * @author idream
 *         描述： 自动生成的代码
 */
@ApiModel("标签树")
public class TagInfoTree {

    //
    private Integer id;

    //标签
    private String label;

    //
    private Integer pid;

    @ApiModelProperty(value = "为3表标签")
    private Integer kind;
    //
    private Date createTime;

    //
    private Date updateTime;

    private List<TagInfoTree> children;
    //自定义前端字段
    private boolean active = false;

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

    public List<TagInfoTree> getChildren() {
        return children;
    }

    public void setChildren(List<TagInfoTree> children) {
        this.children = children;
    }

    @ApiModelProperty(value = "false，true")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }
}
