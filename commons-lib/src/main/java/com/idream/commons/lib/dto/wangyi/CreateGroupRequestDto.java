package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/11 20:49
 * @Description:
 */
@ApiModel("创建群组请求参数封装")
public class CreateGroupRequestDto {

    @ApiModelProperty("群名称,必填")
    @NotBlank(message = "群名称不能为空")
    @Length(max = 64, message = "最大64字符长度")
    private String tname;
    @ApiModelProperty("群主用户帐号,必填")
    @NotBlank(message = "群主用户帐号不能为空")
    @Length(max = 32, message = "最大32字符长度")
    private String owner;

    @ApiModelProperty("群成员不能为空,没有群成可以先把群主添加进去,多个群成员用逗号隔开.必填")
    @NotBlank(message = "群成员不能为空")
    private String members;

    @ApiModelProperty("群组公告")
    @Length(max = 1024, message = "群组公告最大长度1024")
    private String announcement;

    @ApiModelProperty("群描述")
    @Length(max = 512, message = "最大长度512字符")
    private String intro;

    @ApiModelProperty("邀请发送文字. 必填")
    @NotBlank(message = "邀请发送的文字必填")
    @Length(max = 150, message = "最大长度150字符长度")
    private String msg;

    @ApiModelProperty("管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群。其它会返回414. 必填")
    @NotNull
    private Integer magree;

    @ApiModelProperty("群建好后，sdk操作时，0不用验证，1需要验证,2不允许任何人加入。其它返回414. 必填")
    @NotNull
    private Integer joinmode;

    @ApiModelProperty("谁可以邀请他人入群，0-管理员(默认),1-所有人。其它返回414 ")
    private Integer invitemode;

    @ApiModelProperty("谁可以修改群资料，0-管理员(默认),1-所有人。其它返回414 ")
    private Integer uptinfomode;

    @ApiModelProperty("群头像路径")
    private String icon;

    @ApiModelProperty("被邀请人同意方式，0-需要同意(默认),1-不需要同意。其它返回414 ")
    private Integer beinvitemode;

    @ApiModelProperty("当前群组信息在表中的主键id")
    private String custom;

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public CreateGroupRequestDto() {
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }


    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getMagree() {
        return magree;
    }

    public void setMagree(Integer magree) {
        this.magree = magree;
    }

    public Integer getJoinmode() {
        return joinmode;
    }

    public void setJoinmode(Integer joinmode) {
        this.joinmode = joinmode;
    }

    public String getIcon() {
        return icon;
    }

    public Integer getInvitemode() {
        return invitemode;
    }

    public void setInvitemode(Integer invitemode) {
        this.invitemode = invitemode;
    }

    public Integer getUptinfomode() {
        return uptinfomode;
    }

    public void setUptinfomode(Integer uptinfomode) {
        this.uptinfomode = uptinfomode;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getBeinvitemode() {
        return beinvitemode;
    }

    public void setBeinvitemode(Integer beinvitemode) {
        this.beinvitemode = beinvitemode;
    }

}

