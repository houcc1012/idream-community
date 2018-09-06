package com.idream.commons.lib.dto.token;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author: hejiang
 * @Description: 登录用户信息
 * @Date: 17:42 2018/3/22
 */
@ApiModel("用户信息")
@ApiIgnore
public class AuthUserInfo {

    //用户主键
    @ApiModelProperty(value = "用户主键", required = true)
    private Integer userId;

    //昵称
    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;

    //姓名
    @ApiModelProperty(value = "姓名", required = true)
    private String userName;

    //电话
    @ApiModelProperty(value = "电话", required = true)
    private String phone;

    @ApiModelProperty(value = "gender", required = true)
    private String gender;

    @ApiModelProperty(value = "city", required = true)
    private String city;

    @ApiModelProperty(value = "用户类型 1-前台用户;2-管理端用户", required = true)
    private Byte userType;

    @ApiModelProperty(value = "用户角色,1管理,2组织,3商户,4业主,5租户,6游客, 7-普通用户", required = true)
    private Byte userRole;

    public Byte getUserRole() {
        return userRole;
    }

    public void setUserRole(Byte userRole) {
        this.userRole = userRole;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
