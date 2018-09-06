package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description :
 * @Created by xiaogang on 2018/5/8.
 */
@ApiModel(value = "APP我关注的好友搜索条件")
public class AppMyAttendParam extends PagesParam {

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Integer authUserId;

    public Integer getAuthUserId() {
        return authUserId;
    }

    public void setAuthUserId(Integer authUserId) {
        this.authUserId = authUserId;
    }
}
