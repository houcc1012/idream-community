package com.idream.commons.lib.dto.appactivity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Auther: penghekai
 * @Date: 2018/7/17 14:12
 * @Description:
 */
@ApiModel("活动类型编辑页返回参数")
public class AppMyActivityTypeResponseDto {

    @ApiModelProperty("感兴趣的类型")
    private List<AppActivityUserLikeTypeResponseDto> likeTypeList;

    @ApiModelProperty("其它类型")
    private List<AppActivityUserLikeTypeResponseDto> otherList;

    public List<AppActivityUserLikeTypeResponseDto> getLikeTypeList() {
        return likeTypeList;
    }

    public void setLikeTypeList(List<AppActivityUserLikeTypeResponseDto> likeTypeList) {
        this.likeTypeList = likeTypeList;
    }

    public List<AppActivityUserLikeTypeResponseDto> getOtherList() {
        return otherList;
    }

    public void setOtherList(List<AppActivityUserLikeTypeResponseDto> otherList) {
        this.otherList = otherList;
    }
}

