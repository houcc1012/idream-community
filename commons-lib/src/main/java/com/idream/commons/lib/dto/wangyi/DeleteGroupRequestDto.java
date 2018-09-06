package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/12 17:28
 * @Description:
 */
@ApiModel("删除群请求参数封装")
public class DeleteGroupRequestDto {
    @ApiModelProperty("群组id,必填")
    @NotBlank(message = "群组id不能为空")
    private String tid;

    @ApiModelProperty("群主accid")
    @NotBlank(message = "群主accid不能为空")
    private String owner;

    public DeleteGroupRequestDto() {
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}

