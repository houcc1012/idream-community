package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/18 09:19
 * @Description:
 */
@ApiModel("群组禁言列表")
public class GroupMuteListResponseDto {

    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("昵称")
    private String memo;

    @ApiModelProperty("角色 : 1-群主;2-管路员;3-吃瓜群众")
    private Integer groupIdentity;
    @ApiModelProperty("头像")
    private String icon;
    @ApiModelProperty("性别")
    private Integer gender;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getGroupIdentity() {
        return groupIdentity;
    }

    public void setGroupIdentity(Integer groupIdentity) {
        this.groupIdentity = groupIdentity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}

