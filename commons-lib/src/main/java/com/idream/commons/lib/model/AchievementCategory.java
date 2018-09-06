package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 成就分类
 */
@ApiModel("成就分类")
public class AchievementCategory {
    /**
     *
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sorted;

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getSorted() {
        return sorted;
    }

    public void setSorted(Integer sorted) {
        this.sorted = sorted;
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