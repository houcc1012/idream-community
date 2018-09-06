package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/13 00:31
 * @Description:
 */
@ApiModel("群组添加管理员请求参数封装")
public class AddGroupManagerRequestDto {

    @ApiModelProperty("群id.  必填")
    @NotBlank(message = "群id不能为空")
    private String tid;

    @ApiModelProperty("群主accid.  必填")
    @NotBlank(message = "群主accid不能为空")
    private String owner;

    @ApiModelProperty("管理员,一次最多添加10个管理员.  必填")
    @NotBlank(message = "群id不能为空")
    private String members;

    public AddGroupManagerRequestDto() {
    }

    public AddGroupManagerRequestDto(@NotBlank(message = "群id不能为空") String tid, @NotBlank(message = "群主accid不能为空") String owner, @NotBlank(message = "群id不能为空") String members) {
        this.tid = tid;
        this.owner = owner;
        this.members = members;
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

