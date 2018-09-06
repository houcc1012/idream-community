package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/25 18:52
 * @Description:
 */
@ApiModel("根据活动id查询群组")
public class QueryGroupByActivityId {


    @ApiModelProperty("群组id")
    private String tid;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}

