package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/14 11:09
 * @Description:
 */
@ApiModel("禁言群成员请求参数封装")
public class MuteUserRequestDto {
    @ApiModelProperty("群组id . 必填")
    @NotBlank(message = "群组id不能为空")
    private String tid;

    @ApiModelProperty("群主accid . 必填")
    @NotBlank(message = "群主accid不能为空")
    private String owner;

    @ApiModelProperty("禁言对象的accid . 必填")
    @NotBlank(message = "accid不能为空")
    private String accid;

    @ApiModelProperty("禁用动作,1-禁言，0-解禁 . 必填")
    @NotBlank(message = "不能为空")
    private String mute;

    public MuteUserRequestDto() {
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

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getMute() {
        return mute;
    }

    public void setMute(String mute) {
        this.mute = mute;
    }
}

