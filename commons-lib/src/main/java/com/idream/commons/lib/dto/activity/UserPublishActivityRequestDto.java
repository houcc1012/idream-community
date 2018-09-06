package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: penghekai
 * @Date: 2018/7/10 18:33
 * @Description:
 */
@ApiModel("查询管理者发布的活动")
public class UserPublishActivityRequestDto extends PagesParam {

    @ApiModelProperty("用户id")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

