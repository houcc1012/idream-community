package com.idream.commons.lib.dto.wangyi;

import com.idream.commons.lib.dto.token.AuthUserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/17 00:34
 * @Description:
 */
@ApiModel("解散群主")
public class DeleteGroupRequestParam {


    @ApiModelProperty("群组id")
    @NotBlank(message = "群组id不能为空")
    private String tid;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}

