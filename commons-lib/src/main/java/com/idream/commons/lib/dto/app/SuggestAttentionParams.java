package com.idream.commons.lib.dto.app;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/7/17 14:09
 */
@ApiModel(value = "app端 添加朋友 推荐关注请求dto")
public class SuggestAttentionParams extends PagesParam {
    @ApiModelProperty(value = "用户ID", hidden = true)
    private Integer authUserId;

    public Integer getAuthUserId() {
        return authUserId;
    }

    public void setAuthUserId(Integer authUserId) {
        this.authUserId = authUserId;
    }
}
