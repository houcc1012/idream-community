package com.idream.commons.lib.dto.activity;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p>Title: CommunityDto1.java</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.idream.com</p >
 *
 * @author penghekai
 * @version 1.0
 */
public class CommunityResponseDto {

    @ApiModelProperty(value = "1-周围的社区; 2-已经发布活动的社区", required = true)
    private Byte type;

    @ApiModelProperty(value = "社区信息", required = true)
    private List<CommunityDto> communityDtos;

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public List<CommunityDto> getCommunityDtos() {
        return communityDtos;
    }

    public void setCommunityDtos(List<CommunityDto> communityDtos) {
        this.communityDtos = communityDtos;
    }

}
