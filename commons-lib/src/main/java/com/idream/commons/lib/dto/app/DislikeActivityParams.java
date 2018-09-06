package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/7/24 15:46
 */
@ApiModel(value = "附近活动趣聊 不感兴趣 请求参数")
public class DislikeActivityParams {
    @ApiModelProperty(value = "趣聊群id")
    private Integer tid;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
}
