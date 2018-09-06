package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/13 21:33
 * @Description:
 */
@ApiModel("移除管理员参数请求封装")
public class RemoveManagerFromGroupRequestDto {

    @ApiModelProperty("群组id")
    @NotBlank(message = "群组参数不能为空")
    private String tid;

    @ApiModelProperty("群主accid")
    @NotBlank(message = "群组accid不能为空")
    private String owner;

    @ApiModelProperty("要移除的管理员,多个管理员用逗号隔开")
    @NotBlank(message = "管理员不能为空")
    private String members;

    public RemoveManagerFromGroupRequestDto() {
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

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }
}

