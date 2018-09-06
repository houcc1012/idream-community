package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Unity地标信息表
 */
@ApiModel("Unity地标信息表")
public class UnityGeographyInfo {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 社区id
     */
    @ApiModelProperty(value = "社区id")
    private Integer regionId;

    /**
     * 小区ID
     */
    @ApiModelProperty(value = "小区ID")
    private Integer communityId;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    /**
     * Unity编号
     */
    @ApiModelProperty(value = "Unity编号")
    private String unityId;

    /**
     * 建筑id
     */
    @ApiModelProperty(value = "建筑id")
    private String buildingId;

    /**
     * 建筑名称
     */
    @ApiModelProperty(value = "建筑名称")
    private String buildingName;

    /**
     * 类型 1-小区;2-写字楼,3-公告牌;4-体育场....
     */
    @ApiModelProperty(value = "类型 1-小区;2-写字楼,3-公告牌;4-体育场....")
    private Byte type;

    /**
     * 内容,根据不同类型存储不同的文本,可以是json字符串
     */
    @ApiModelProperty(value = "内容,根据不同类型存储不同的文本,可以是json字符串")
    private String content;

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

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
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

    public String getUnityId() {
        return unityId;
    }

    public void setUnityId(String unityId) {
        this.unityId = unityId == null ? null : unityId.trim();
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId == null ? null : buildingId.trim();
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName == null ? null : buildingName.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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