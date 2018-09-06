package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "活动列表 社区和活动返回dto")
public class ActivityInfoListResponseDto {

    //城市
    @ApiModelProperty(value = "城市")
    private String city;

    //判断定位当前城市是否有活动列表
    //1:有活动,2:无活动,3:无定位权限
    @ApiModelProperty(value = "判断定位当前城市是否有活动列表 1:2:3 | 有活动:无活动:无定位权限")
    private Integer listStatus;

    //活动列表list
    @ApiModelProperty(value = "活动列表list")
    private List<CommunityActivityResponseDto> communityActivityResponseDtoList;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getListStatus() {
        return listStatus;
    }

    public void setListStatus(Integer listStatus) {
        this.listStatus = listStatus;
    }

    public List<CommunityActivityResponseDto> getCommunityActivityResponseDtoList() {
        return communityActivityResponseDtoList;
    }

    public void setCommunityActivityResponseDtoList(List<CommunityActivityResponseDto> communityActivityResponseDtoList) {
        this.communityActivityResponseDtoList = communityActivityResponseDtoList;
    }


}
