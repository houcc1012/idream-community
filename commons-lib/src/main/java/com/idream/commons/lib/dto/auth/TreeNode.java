package com.idream.commons.lib.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author charles.wei
 */
@ApiModel("基础信息")
public class TreeNode {
    @ApiModelProperty(value = "id")
    protected int id;
    @ApiModelProperty(value = "父id")
    protected int pid;
    @ApiModelProperty(value = "code")
    protected String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
