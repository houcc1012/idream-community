package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "web端用户列表返回参数")
public class AdminClientUserListDto {
    @ApiModelProperty(value = "用户Id")
    private Integer id;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "身份证")
    private String identity;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "用户角色 1管理,2组织,3商户,4业主,5租户,6游客")
    private Integer userRole;

    @ApiModelProperty(value = "用户激活渠道 1-ios ;2-andriod; 3-微信小程序；4-web")
    private Integer activateChannel;

    @ApiModelProperty(value = "管理者是否已授权 1-是; 0-否")
    private Byte managerIsAuthorize;

    @ApiModelProperty(value = "0-非禁言，1-禁言")
    private Integer complainted;

    @ApiModelProperty(value = "禁言Id")
    private Integer handleId;

    @ApiModelProperty(value = "认证的社区")
    private List<UserCommunityRelationDto> communities;

    public List<UserCommunityRelationDto> getCommunities() {
        return communities;
    }

    public void setCommunities(List<UserCommunityRelationDto> communities) {
        this.communities = communities;
    }


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Byte getManagerIsAuthorize() {
        return managerIsAuthorize;
    }

    public void setManagerIsAuthorize(Byte managerIsAuthorize) {
        this.managerIsAuthorize = managerIsAuthorize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }


    public Integer getActivateChannel() {
        return activateChannel;
    }

    public void setActivateChannel(Integer activateChannel) {
        this.activateChannel = activateChannel;
    }

    public Integer getComplainted() {
        return complainted;
    }

    public void setComplainted(Integer complainted) {
        this.complainted = complainted;
    }

    public Integer getHandleId() {
        return handleId;
    }

    public void setHandleId(Integer handleId) {
        this.handleId = handleId;
    }
}
