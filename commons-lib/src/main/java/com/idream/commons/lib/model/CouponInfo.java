package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 奖券表
 */
@ApiModel("奖券表")
public class CouponInfo {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "对应奖品池的id, 和获得类型确定一个奖品")
    private Integer awardId;

    @ApiModelProperty(value = "券码")
    private String couponCode;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "奖券类型:默认1-礼品券;2-积分券;3-空")
    private Byte type;

    @ApiModelProperty(value = "奖品值,json字符串.如7折折扣券 {'discount':'7'}...")
    private String couponValue;

    @ApiModelProperty(value = "奖品名称")
    private String awardName;

    @ApiModelProperty(value = "奖品描述")
    private String description;

    @ApiModelProperty(value = "说明")
    private String instructions;

    @ApiModelProperty(value = "过期时间")
    private Date expireDate;

    @ApiModelProperty(value = "是否开启补充人信息,1开启,0不开启,默认0")
    private Boolean infoAble;

    @ApiModelProperty(value = "填写完成,1完成,0未完成")
    private Boolean infoCompleted;

    @ApiModelProperty(value = "兑换状态,1未兑换,2兑换成功,3过期")
    private Byte status;

    @ApiModelProperty(value = "是否删除 1-是;0-否")
    private Byte deleteFlag;

    @ApiModelProperty(value = "获得渠道,1-抽奖,2-积分，3-现场开奖")
    private Byte fromType;

    @ApiModelProperty(value = "获得渠道描述,与记录表沉余")
    private String fromDescription;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "兑换人ID,指是哪个管理员兑换的")
    private Integer operateUserId;

    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode == null ? null : couponCode.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(String couponValue) {
        this.couponValue = couponValue == null ? null : couponValue.trim();
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName == null ? null : awardName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions == null ? null : instructions.trim();
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Boolean getInfoAble() {
        return infoAble;
    }

    public void setInfoAble(Boolean infoAble) {
        this.infoAble = infoAble;
    }

    public Boolean getInfoCompleted() {
        return infoCompleted;
    }

    public void setInfoCompleted(Boolean infoCompleted) {
        this.infoCompleted = infoCompleted;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Byte getFromType() {
        return fromType;
    }

    public void setFromType(Byte fromType) {
        this.fromType = fromType;
    }

    public String getFromDescription() {
        return fromDescription;
    }

    public void setFromDescription(String fromDescription) {
        this.fromDescription = fromDescription == null ? null : fromDescription.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(Integer operateUserId) {
        this.operateUserId = operateUserId;
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