package com.idream.commons.lib.dto.app;

import com.idream.commons.lib.dto.activity.AppUserLabelDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author: jeffery
 * @Date: 2018/7/18 19:33
 */
@ApiModel(value = "app端 添加朋友 输入昵称或手机号模糊查询返回参数")
public class SuggestAttentionFriendDto {
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户image")
    private String userImage;

    @ApiModelProperty(value = "用户标签")
    private List<AppUserLabelDto> userLabelList;

    @ApiModelProperty(value = "用户关注 0:1:2 | +关注:已关注:互相关注")
    private Integer attendEachOther;

    @ApiModelProperty(value = "网易accid")
    private String accid;

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public Integer getAttendEachOther() {
        return attendEachOther;
    }

    public void setAttendEachOther(Integer attendEachOther) {
        this.attendEachOther = attendEachOther;
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

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public List<AppUserLabelDto> getUserLabelList() {
        return userLabelList;
    }

    public void setUserLabelList(List<AppUserLabelDto> userLabelList) {
        this.userLabelList = userLabelList;
    }
}
