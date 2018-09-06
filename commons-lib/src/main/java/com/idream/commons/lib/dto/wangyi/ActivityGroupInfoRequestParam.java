package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/21 18:27
 * @Description:
 */
public class ActivityGroupInfoRequestParam {


    @ApiModelProperty("群组id")
    @NotNull(message = "群组id不能为空")
    private Integer tid;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
}

