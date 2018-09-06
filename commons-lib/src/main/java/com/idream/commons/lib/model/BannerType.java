package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * banner类型
 */
@ApiModel("banner类型")
public class BannerType {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 分类,1小程序
     */
    @ApiModelProperty(value = "分类,1小程序")
    private Byte category;

    /**
     * 最大上架数量
     */
    @ApiModelProperty(value = "最大上架数量")
    private Integer maxUpNum;

    /**
     * 生成时间
     */
    @ApiModelProperty(value = "生成时间")
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

    public Byte getCategory() {
        return category;
    }

    public void setCategory(Byte category) {
        this.category = category;
    }

    public Integer getMaxUpNum() {
        return maxUpNum;
    }

    public void setMaxUpNum(Integer maxUpNum) {
        this.maxUpNum = maxUpNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}