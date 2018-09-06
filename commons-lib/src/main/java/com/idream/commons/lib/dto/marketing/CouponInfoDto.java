package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Description :
 * Created by DELL2018-003 on 2018/4/12.
 */
@ApiModel("抽奖中奖记录")
public class CouponInfoDto {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "poolId")
    private Integer poolId;

    @ApiModelProperty(value = "券码")
    private String couponCode;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "用户手机号")
    private String phone;

    @ApiModelProperty(value = "1.实物券，2满减，3折扣，4代金，5积分券 ")
    private Integer property;

    @ApiModelProperty(value = "1礼品券 2积分券 3折扣券")
    private Integer type;

    @ApiModelProperty(value = "获得渠道,1-抽奖,2-积分，3-现场开奖")
    private Integer fromType;

    @ApiModelProperty(value = "奖品名称")
    private String awardName;

    @ApiModelProperty(value = "奖品描述")
    private String description;

    @ApiModelProperty(value = "奖品说明")
    private String instructions;

    @ApiModelProperty(value = "过期时间")
    private Date expireDate;

    @ApiModelProperty(value = "是否开启补充人信息,1开启,0不开启,默认0")
    private Boolean infoAble;

    @ApiModelProperty(value = "填写完成,1完成,0未完成")
    private Boolean infoCompleted;

    @ApiModelProperty(value = "兑换状态,1未兑换,2兑换成功,3过期")
    private Integer status;

    @ApiModelProperty(value = "获得渠道描述")
    private String fromDescription;

    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    @ApiModelProperty(value = "兑奖时间")
    private Date updateTime;

    @ApiModelProperty(value = "使用时间")
    private Date useTime;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "兑奖消耗积分")
    private int exchangeScore;
    @ApiModelProperty(value = "活动名称,开奖活动获得的奖券有该值")
    private String activityName;

    @ApiModelProperty(value = "开奖社区")
    private String communityName;

    @ApiModelProperty(value = "奖品值,json字符串.如7折折扣券 {\"discount\":\"7\"}")
    private String couponValue;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode == null ? null : couponCode.trim();
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

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getFromDescription() {
        return fromDescription;
    }

    public void setFromDescription(String fromDescription) {
        this.fromDescription = fromDescription;
    }

    public Integer getPoolId() {
        return poolId;
    }

    public void setPoolId(Integer poolId) {
        this.poolId = poolId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getFromType() {
        return fromType;
    }

    public void setFromType(Integer fromType) {
        this.fromType = fromType;
    }

    public int getExchangeScore() {
        return exchangeScore;
    }

    public void setExchangeScore(int exchangeScore) {
        this.exchangeScore = exchangeScore;
    }

    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(String couponValue) {
        this.couponValue = couponValue;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}
