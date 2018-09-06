package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/16 20:04
 * @Description:
 */
@ApiModel("禁言群组")
public class MuteGroupRequestDto {

    @ApiModelProperty("群组id")
    @NotBlank(message = "群组id能为空")
    private String tid;

    @ApiModelProperty("群主的accid")
    @NotBlank(message = "群主accid")
    private String owner;

    @ApiModelProperty("禁言类型 0:解除禁言，1:禁言普通成员 3:禁言整个群(包括群主) ")
    private String muteType;

    public MuteGroupRequestDto() {
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

    public String getMuteType() {
        return muteType;
    }

    public void setMuteType(String muteType) {
        this.muteType = muteType;
    }
}

