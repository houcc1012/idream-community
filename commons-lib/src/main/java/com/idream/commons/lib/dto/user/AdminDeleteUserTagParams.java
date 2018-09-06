package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by DELL2018-005 on 2018/6/2.
 */
@ApiModel(value = "删除用户标签请求参数")
public class AdminDeleteUserTagParams {
    @ApiModelProperty(value = "标签Id")
    private Integer id;

    @ApiModelProperty(value = "父级标签id")
    private Integer pid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
