package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/17 00:02
 * @Description:
 */
@ApiModel("主动退群")
public class LeaveGroupRequestDto {

    @ApiModelProperty("群组id , 必填")
    @NotBlank(message = "群组id不能为空")
    private String tid;

    @ApiModelProperty("要退群用户的accid")
    @NotBlank(message = "accid不能为空")
    private String accid;

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
}

