package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/12 15:35
 * @Description:
 */
@ApiModel("群信息与成员列表查询")
public class GroupAndMemberListResponseDto {

    @ApiModelProperty("群头像")
    private String icon;

    @ApiModelProperty("群公告")
    private String announcement;

    @ApiModelProperty("禁言类型 0:解除禁言，1:禁言普通成员 3:禁言整个群")
    private int muteType;

    @ApiModelProperty("谁可以修改群资料，0-管理员(默认),1-所有人")
    private int uptinfomode;

    @ApiModelProperty("该群最大人数容量")
    private int maxusers;

    @ApiModelProperty("群描述")
    private String intro;

    @ApiModelProperty("群内总人数")
    private int size;

    @ApiModelProperty("谁可以更新群自定义属性，0-管理员(默认),1-所有人")
    private int upcustommode;

    @ApiModelProperty("群主")
    private String owner;

    @ApiModelProperty("群名称")
    private String tname;

    @ApiModelProperty("被邀请人同意方式，0-需要同意(默认),1-不需要同意")
    private int beinvitemode;

    @ApiModelProperty("群建好后，sdk操作时，0不用验证，1需要验证,2不允许任何人加入")
    private int joinmode;

    @ApiModelProperty("群组id")
    private long tid;

    @ApiModelProperty("群内成员")
    private List<String> members;

    @ApiModelProperty("谁可以邀请他人入群，0-管理员(默认),1-所有人")
    private int invitemode;

    @ApiModelProperty("true:禁言，false:解禁")
    private boolean mute;


    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setMuteType(int muteType) {
        this.muteType = muteType;
    }

    public int getMuteType() {
        return muteType;
    }

    public void setUptinfomode(int uptinfomode) {
        this.uptinfomode = uptinfomode;
    }

    public int getUptinfomode() {
        return uptinfomode;
    }

    public void setMaxusers(int maxusers) {
        this.maxusers = maxusers;
    }

    public int getMaxusers() {
        return maxusers;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIntro() {
        return intro;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setUpcustommode(int upcustommode) {
        this.upcustommode = upcustommode;
    }

    public int getUpcustommode() {
        return upcustommode;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTname() {
        return tname;
    }

    public void setBeinvitemode(int beinvitemode) {
        this.beinvitemode = beinvitemode;
    }

    public int getBeinvitemode() {
        return beinvitemode;
    }

    public void setJoinmode(int joinmode) {
        this.joinmode = joinmode;
    }

    public int getJoinmode() {
        return joinmode;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public long getTid() {
        return tid;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setInvitemode(int invitemode) {
        this.invitemode = invitemode;
    }

    public int getInvitemode() {
        return invitemode;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public boolean getMute() {
        return mute;
    }

}

