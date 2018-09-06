package com.idream.commons.lib.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author charles.wei
 */
@ApiModel("权限信息")
public class RoleInfo {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "生成时间")
    private Date createTime;
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
