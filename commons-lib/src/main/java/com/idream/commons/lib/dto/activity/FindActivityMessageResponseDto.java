package com.idream.commons.lib.dto.activity;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "查看活动精彩返回参数")
public class FindActivityMessageResponseDto {

    @ApiModelProperty("精彩id")
    private Integer lifeId;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("精彩内容")
    private String content;
    @ApiModelProperty("留言图片")
    private List<AppImageParam> imageList;
    @ApiModelProperty("留言时间")
    private Date createTime;
    @ApiModelProperty("点赞数")
    private Integer likeCount;
    @ApiModelProperty("状态 2正常 3已屏蔽")
    private Byte status;

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

    public Integer getLifeId() {
        return lifeId;
    }

    public void setLifeId(Integer lifeId) {
        this.lifeId = lifeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<AppImageParam> getImageList() {
        return imageList;
    }

    public void setImageList(List<AppImageParam> imageList) {
        this.imageList = imageList;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
