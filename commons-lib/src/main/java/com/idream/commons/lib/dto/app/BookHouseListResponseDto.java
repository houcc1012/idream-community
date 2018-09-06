package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "书屋验证(书屋列表)")
public class BookHouseListResponseDto {

    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    @ApiModelProperty(value = "社区名")
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
