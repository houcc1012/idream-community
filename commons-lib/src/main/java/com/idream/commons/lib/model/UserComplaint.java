package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户投诉
 */
@ApiModel("用户投诉")
public class UserComplaint {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 举报id
     */
    @ApiModelProperty(value = "举报id")
    private Integer fromUserId;

    /**
     * 举报人昵称
     */
    @ApiModelProperty(value = "举报人昵称")
    private String fromUserNickName;

    /**
     * 被举报人id
     */
    @ApiModelProperty(value = "被举报人id")
    private Integer businessId;

    /**
     * 1用户,2群
     */
    @ApiModelProperty(value = "1用户,2群")
    private Byte businessType;

    /**
     * 举报类型
     */
    @ApiModelProperty(value = "举报类型")
    private Integer typeId;

    /**
     * 举报内容
     */
    @ApiModelProperty(value = "举报内容")
    private String content;

    /**
     * 状态,1提交,2通过,3失败
     */
    @ApiModelProperty(value = "状态,1提交,2通过,3失败")
    private Byte status;

    /**
     * 图像
     */
    @ApiModelProperty(value = "图像")
    private String images;

    /**
     * 生成时间
     */
    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserNickName() {
        return fromUserNickName;
    }

    public void setFromUserNickName(String fromUserNickName) {
        this.fromUserNickName = fromUserNickName == null ? null : fromUserNickName.trim();
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Byte getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Byte businessType) {
        this.businessType = businessType;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}