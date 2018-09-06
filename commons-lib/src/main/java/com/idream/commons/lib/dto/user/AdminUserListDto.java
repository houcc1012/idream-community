package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author hejiang
 */
@ApiModel("管理端")
public class AdminUserListDto {

    @ApiModelProperty(value = "id")
    private int id;

    @ApiModelProperty(value = "账号名称")
    private String accountName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
