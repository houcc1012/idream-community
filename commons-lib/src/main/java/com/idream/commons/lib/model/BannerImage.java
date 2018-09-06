package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 *
 */
@ApiModel("")
public class BannerImage {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 图片链接
     */
    @ApiModelProperty(value = "图片链接")
    private String imageUrl;

    /**
     * bannerId
     */
    @ApiModelProperty(value = "bannerId")
    private Integer bannerId;

    /**
     * 跳转链接
     */
    @ApiModelProperty(value = "跳转链接")
    private String jumpLink;

    /**
     * 默认
     */
    @ApiModelProperty(value = "默认,1图片,2单个活动,3多个活动")
    private Byte type;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getJumpLink() {
        return jumpLink;
    }

    public void setJumpLink(String jumpLink) {
        this.jumpLink = jumpLink == null ? null : jumpLink.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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