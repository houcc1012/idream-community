package com.idream.commons.lib.dto.app;

import java.util.List;

import com.idream.commons.lib.dto.PagesDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "热门社区(我的社区和附近社区)返回dto")
public class MyNeighborCommunityResponseDto {

    @ApiModelProperty(value = "我的社区列表")
    private PagesDto<List<CommunityNameListResponseDto>> communityNameListResponseDtoList;

    @ApiModelProperty(value = "附近社区列表")
    //private PagesDto<List<NeighborCommunityListResponseDto>> neighborCommunityListResponseDtoList;
    private PagesDto<List<NeighborCommunityRegionResponseDto>> neighborCommunityRegionResponseDtoList;

    public PagesDto<List<CommunityNameListResponseDto>> getCommunityNameListResponseDtoList() {
        return communityNameListResponseDtoList;
    }

    public void setCommunityNameListResponseDtoList(
            PagesDto<List<CommunityNameListResponseDto>> communityNameListResponseDtoList) {
        this.communityNameListResponseDtoList = communityNameListResponseDtoList;
    }

    public PagesDto<List<NeighborCommunityRegionResponseDto>> getNeighborCommunityRegionResponseDtoList() {
        return neighborCommunityRegionResponseDtoList;
    }

    public void setNeighborCommunityRegionResponseDtoList(
            PagesDto<List<NeighborCommunityRegionResponseDto>> neighborCommunityRegionResponseDtoList) {
        this.neighborCommunityRegionResponseDtoList = neighborCommunityRegionResponseDtoList;
    }

	/*public PagesDto<List<NeighborCommunityListResponseDto>> getNeighborCommunityListResponseDtoList() {
        return neighborCommunityListResponseDtoList;
	}

	public void setNeighborCommunityListResponseDtoList(
			PagesDto<List<NeighborCommunityListResponseDto>> neighborCommunityListResponseDtoList) {
		this.neighborCommunityListResponseDtoList = neighborCommunityListResponseDtoList;
	}*/

}
