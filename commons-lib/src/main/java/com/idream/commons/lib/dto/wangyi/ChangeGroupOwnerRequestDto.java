package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/14 09:10
 * @Description:
 */
@ApiModel("移交群主请求参数封装")
public class ChangeGroupOwnerRequestDto {

    @ApiModelProperty("群组id. 必填")
    @NotBlank(message = "群组id不能为空")
    private String tid;


    @ApiModelProperty("群主accid. 必填")
    @NotBlank(message = "群主accid不能为空")
    private String owner;


    @ApiModelProperty("新群主accid. 必填")
    @NotBlank(message = "新群主accid不能为空")
    private String newowner;


    @ApiModelProperty("原群主去向(1:群主解除群主后离开群，2：群主解除群主后成为普通成员。其它414 ). 必填")
    @NotBlank(message = "不能为空")
    private String leave;

    public ChangeGroupOwnerRequestDto() {
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

    public String getNewowner() {
        return newowner;
    }

    public void setNewowner(String newowner) {
        this.newowner = newowner;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }
}

