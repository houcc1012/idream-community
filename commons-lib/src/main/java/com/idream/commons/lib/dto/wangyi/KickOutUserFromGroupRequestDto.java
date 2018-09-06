package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/12 16:50
 * @Description:
 */
@ApiModel("从群组中踢人请求封装")
public class KickOutUserFromGroupRequestDto {

    @ApiModelProperty("群组id. 必填")
    @NotBlank(message = "群组id不能为空")
    private String tid;

    @ApiModelProperty("群主的accid. 必填")
    @NotBlank(message = "群主的accid不能为空")
    private String owner;

    @ApiModelProperty("被移除人的accid. 必填")
    @NotBlank(message = "被移除人的accid不能为空")
    private String member;

    public KickOutUserFromGroupRequestDto() {
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

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }
}

