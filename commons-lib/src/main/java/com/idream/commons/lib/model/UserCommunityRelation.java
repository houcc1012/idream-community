package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户社区绑定
 */
@ApiModel("用户社区绑定")
public class UserCommunityRelation {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    /**
     * 社区id
     */
    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    /**
     * 第几个原住民
     */
    @ApiModelProperty(value = "第几个原住民")
    private Integer localNumber;

    /**
     * 1,普通认证,2为高级认证(暂时不用 )
     */
    @ApiModelProperty(value = "1,普通认证,2为高级认证(暂时不用 )")
    private Byte type;

    /**
     * 1审核中,2审核通过,3不通过
     */
    @ApiModelProperty(value = "1审核中,2审核通过,3不通过")
    private Byte status;

    /**
     * 户主类型,1业主,2租户,3嘉宾,4未知
     */
    @ApiModelProperty(value = "户主类型,1业主,2租户,3嘉宾,4未知")
    private Byte userType;

    /**
     * 认证来源,1位置,2书屋
     */
    @ApiModelProperty(value = "认证来源,1未知,2书屋")
    private Byte fromType;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getLocalNumber() {
        return localNumber;
    }

    public void setLocalNumber(Integer localNumber) {
        this.localNumber = localNumber;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public Byte getFromType() {
        return fromType;
    }

    public void setFromType(Byte fromType) {
        this.fromType = fromType;
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