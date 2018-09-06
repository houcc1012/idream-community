package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author penghekai
 */
@ApiModel(value = "查询主题留言返回参数")
public class FindMessageResponseDto {

    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "留言内容")
    private String message;
    @ApiModelProperty(value = "留言时间")
    private String time;
    @ApiModelProperty(value = "点赞数")
    private String likeCount;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

}
