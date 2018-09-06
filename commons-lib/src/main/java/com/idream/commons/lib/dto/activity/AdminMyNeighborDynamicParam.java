package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/4/28.
 */
@ApiModel(value = "我的邻里动态列表详情查询参数")
public class AdminMyNeighborDynamicParam extends PagesParam {

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty(value = "管理者ID", hidden = true)
    private Integer userId;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty("社区名")
    private String communityName;

    @ApiModelProperty(value = "查询类型：空为所有生活动态，1为我管理的社区生活动态")
    private Integer type;

    @ApiModelProperty(value = "管理书屋ID", hidden = true)
    private Integer bookId;

    @ApiModelProperty(value = "状态 2-正常;3-已屏蔽")
    private Byte status;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}
