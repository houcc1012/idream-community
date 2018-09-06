package com.idream.commons.lib.dto.user;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 */
@ApiModel("前台用户查询")
public class AdminClientUserListParams extends PagesParam {

    @ApiModelProperty(value = "手机号", required = false)
    private String phone;

    @ApiModelProperty(value = "用户角色 1管理,2组织,3商户,4业主,5租户,6游客", required = false)
    private String userRole;

    @ApiModelProperty(value = "认证状态 1审核中,2审核通过,3不通过", required = false)
    private Integer status;

    @ApiModelProperty(value = "0-全部 1-非禁言，2-禁言", required = false)
    private Integer isComplainted;

    @ApiModelProperty(value = "0-全部 1-有认证社区，2-无认证社区", required = false)
    private Integer isHasCommunity;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsComplainted() {
        return isComplainted;
    }

    public void setIsComplainted(Integer isComplainted) {
        this.isComplainted = isComplainted;
    }

    public Integer getIsHasCommunity() {
        return isHasCommunity;
    }

    public void setIsHasCommunity(Integer isHasCommunity) {
        this.isHasCommunity = isHasCommunity;
    }
}
