package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 模型社区信息表
 */
@ApiModel("模型社区信息表")
public class UnityRegionInfo {
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
     * 模型的社区id
     */
    @ApiModelProperty(value = "模型的社区id")
    private String unityRegionId;

    /**
     * 模型社区名称
     */
    @ApiModelProperty(value = "模型社区名称")
    private String unityRegionName;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private String version;

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

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getUnityRegionId() {
        return unityRegionId;
    }

    public void setUnityRegionId(String unityRegionId) {
        this.unityRegionId = unityRegionId == null ? null : unityRegionId.trim();
    }

    public String getUnityRegionName() {
        return unityRegionName;
    }

    public void setUnityRegionName(String unityRegionName) {
        this.unityRegionName = unityRegionName == null ? null : unityRegionName.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}