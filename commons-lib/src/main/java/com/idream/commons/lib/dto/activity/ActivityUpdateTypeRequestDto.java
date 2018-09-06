package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: penghekai
 * @Date: 2018/6/19 16:01
 * @Description:
 */
@ApiModel("编辑活动类型请求参数")
public class ActivityUpdateTypeRequestDto extends ActivityTypeAddRequestDto {
    private Integer id;

    @ApiModelProperty("类型id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

