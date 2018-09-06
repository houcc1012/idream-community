package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Auther: penghekai
 * @Date: 2018/6/28 18:29
 * @Description:
 */
@ApiModel("社区管理者查询请求参数")
public class ActivityAdminManagerRequestDto {

    @ApiModelProperty("社区id")
    private List<Integer> regionIds;

    public List<Integer> getRegionIds() {
        return regionIds;
    }

    public void setRegionIds(List<Integer> regionIds) {
        this.regionIds = regionIds;
    }
}

