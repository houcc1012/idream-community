package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/12 16:02
 * @Description:
 */
@ApiModel("群信息与成员列表查询请求参数封装")
public class QueryGroupAndUsersListRequestDto {

    @ApiModelProperty("群id,批量查询用逗号隔开.必填")
    @NotBlank(message = "群id必填")
    private String tids;

    @ApiModelProperty("1表示带上群成员列表，0表示不带群成员列表，只返回群信息,必填")
    @NotNull(message = "ope不能为空")
    private Integer ope;

    public QueryGroupAndUsersListRequestDto() {
    }

    public String getTids() {
        return tids;
    }

    public void setTids(String tids) {
        this.tids = tids;
    }

    public Integer getOpe() {
        return ope;
    }

    public void setOpe(Integer ope) {
        this.ope = ope;
    }
}

