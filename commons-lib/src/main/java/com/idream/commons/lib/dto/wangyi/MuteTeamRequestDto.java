package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/14 09:55
 * @Description:
 */
@ApiModel("修改消息提醒开关")
public class MuteTeamRequestDto {

    @ApiModelProperty("群组id")
    @NotBlank(message = "群组id不能为空")
    private String tid;

    @ApiModelProperty("用户accid")
    @NotBlank(message = "用户accid不能为空")
    private String accid;

    @ApiModelProperty("1：关闭消息提醒，2：打开消息提醒，其他值无效 ")
    @NotBlank(message = "不能为空")
    private String ope;

    public MuteTeamRequestDto() {
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getOpe() {
        return ope;
    }

    public void setOpe(String ope) {
        this.ope = ope;
    }
}

