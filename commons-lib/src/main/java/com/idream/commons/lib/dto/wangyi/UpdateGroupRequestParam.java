package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/16 09:31
 * @Description:
 */
public class UpdateGroupRequestParam {

    @ApiModelProperty("群组id")
    @NotBlank(message = "tid不能为空")
    private String tid;

    @ApiModelProperty("群名称")
    private String tname;

    @ApiModelProperty("群公告")
    private String announcement;

    @ApiModelProperty("群描述")
    private String intro;

    @ApiModelProperty("群建好后，sdk操作时，0不用验证，1需要验证,2不允许任何人加入。其它返回414")
    private Integer joinmode;

    @ApiModelProperty("群头像路径")
    private String icon;

    @ApiModelProperty("管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群。其它会返回414. 必填")
    private Integer magree;

    @ApiModelProperty("谁可以邀请他人入群，0-管理员(默认),1-所有人。其它返回414 ")
    private Integer invitemode;

    @ApiModelProperty("谁可以修改群资料，0-管理员(默认),1-所有人。其它返回414 ")
    private Integer uptinfomode;

    @ApiModelProperty("被邀请人同意方式，0-需要同意(默认),1-不需要同意。其它返回414 ")
    private Integer beinvitemode;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
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

    public Integer getJoinmode() {
        return joinmode;
    }

    public void setJoinmode(Integer joinmode) {
        this.joinmode = joinmode;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getMagree() {
        return magree;
    }

    public void setMagree(Integer magree) {
        this.magree = magree;
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

    public Integer getBeinvitemode() {
        return beinvitemode;
    }

    public void setBeinvitemode(Integer beinvitemode) {
        this.beinvitemode = beinvitemode;
    }
}

