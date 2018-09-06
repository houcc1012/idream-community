package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/12 09:41
 * @Description:
 */
@ApiModel("创建群组返回封装")
public class CreateGroupResponseDto {

    @ApiModelProperty("创建群组成功返回的群组id")
    private String tid;

    public CreateGroupResponseDto() {
    }

    public CreateGroupResponseDto(String tid) {
        this.tid = tid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}

