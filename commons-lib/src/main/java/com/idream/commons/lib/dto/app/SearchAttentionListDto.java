package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/7/17 15:28
 */
@ApiModel(value = "app端 聊天搜索 联系人(关注的朋友) 返回dto")
public class SearchAttentionListDto {

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "网易accid")
    private String accid;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户image")
    private String userImage;

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
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
}
