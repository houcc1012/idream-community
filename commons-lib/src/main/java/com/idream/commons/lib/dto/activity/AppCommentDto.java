package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/4/28.
 */
@ApiModel(value = "评论列表")
public class AppCommentDto {

    @ApiModelProperty(value = "评论id")
    private Integer id;

    @ApiModelProperty(value = "评论人ID")
    private Integer userId;

    @ApiModelProperty(value = "评论人昵称")
    private Integer nickName;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "回复详情")
    private List<AppCommentDetailDto> commentDetailList;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<AppCommentDetailDto> getCommentDetailList() {
        return commentDetailList;
    }

    public void setCommentDetailList(List<AppCommentDetailDto> commentDetailList) {
        this.commentDetailList = commentDetailList;
    }

    public Integer getNickName() {
        return nickName;
    }

    public void setNickName(Integer nickName) {
        this.nickName = nickName;
    }
}
