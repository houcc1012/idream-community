package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author hejiang
 */
@ApiModel("查询用户信息返回参数")
public class FindUserInfoDto {

    //用户名
    @ApiModelProperty(value = "用户名")
    private String userName;

    //手机号
    @ApiModelProperty(value = "手机号")
    private String phone;

    //密码
    @ApiModelProperty(value = "密码", hidden = true)
    private String password;

    //昵称
    @ApiModelProperty(value = "昵称")
    private String nickName;

    //微信id
    @ApiModelProperty(value = "微信id")
    private String unionId;

    //1男,2女
    @ApiModelProperty(value = "性别 1男,2女")
    private Integer gender;

    //生日
    @ApiModelProperty(value = "生日")
    private Date birthday;

    //职业id
    @ApiModelProperty(value = "职业id")
    private Integer professionId;

    //头像
    @ApiModelProperty(value = "头像Url")
    private String image;

    //用户角色
    @ApiModelProperty(value = "用户角色")
    private Integer userRole;

    //用户登录的城市,市级
    @ApiModelProperty(value = "用户登录的城市,市级")
    private String location;

    //
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    //
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "职业名称")
    private String professionName;

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Integer professionId) {
        this.professionId = professionId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
