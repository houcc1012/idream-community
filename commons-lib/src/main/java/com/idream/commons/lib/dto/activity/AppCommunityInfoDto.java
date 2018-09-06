package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/5/2.
 */
@ApiModel(value = "APP社区信息")
public class AppCommunityInfoDto {

    @ApiModelProperty("社区Id")
    private Integer communityId;

    @ApiModelProperty("社区名称")
    private String communityName;


    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}
