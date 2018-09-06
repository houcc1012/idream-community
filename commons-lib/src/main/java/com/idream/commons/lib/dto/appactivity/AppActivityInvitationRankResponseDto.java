package com.idream.commons.lib.dto.appactivity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("活动邀请排行榜返回参数")
public class AppActivityInvitationRankResponseDto {

    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("用户头像")
    private String userImage;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("邀请数量")
    private Integer invitationNum;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getInvitationNum() {
        return invitationNum;
    }

    public void setInvitationNum(Integer invitationNum) {
        this.invitationNum = invitationNum;
    }

}
