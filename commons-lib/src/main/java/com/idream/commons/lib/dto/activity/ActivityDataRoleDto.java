package com.idream.commons.lib.dto.activity;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * Title: ActivityCardParamsDto.java
 * Description:
 * Copyright: Copyright (c) 2017
 * Company: www.idream.com
 *
 * @author huayuliang
 * @version 1.0
 */
@ApiModel(value = "用户参与活动于当前用户权限")
public class ActivityDataRoleDto {
    @ApiModelProperty(value = "活动数据")
    private List data;
    @ApiModelProperty(value = "用户权限")
    private Integer userRole;

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }


}
