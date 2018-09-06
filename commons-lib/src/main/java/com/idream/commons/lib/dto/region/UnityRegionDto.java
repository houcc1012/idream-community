package com.idream.commons.lib.dto.region;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("3d社区模型")
public class UnityRegionDto {
    @ApiModelProperty(value = "大社区id")
    private String id;
    @ApiModelProperty(value = "版本号")
    private String versionId;
    @ApiModelProperty(value = "大社区名")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
