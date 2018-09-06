package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/21 17:34
 * @Description:
 */
@ApiModel("群组详情")
public class QueryGroupAndUsersDetailrequestParam {

    @ApiModelProperty("群组id")
    private String tid;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}

