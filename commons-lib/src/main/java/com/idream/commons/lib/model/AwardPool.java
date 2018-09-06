package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 兑换奖品表
 */
@ApiModel("兑换奖品表")
public class AwardPool {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "奖品名")
    private String name;

    @ApiModelProperty(value = "1礼品券 2积分券 3折扣券")
    private Byte property;

    @ApiModelProperty(value = "奖品值,json字符串.如7折折扣券 {'discount':'7'}...")
    private String awardValue;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "介绍")
    private String instructions;

    @ApiModelProperty(value = "兑换积分")
    private Integer exchangeScore;

    @ApiModelProperty(value = "兑换次数,每天")
    private Integer exchangeCount;

    @ApiModelProperty(value = "总计兑换次数 -1,表示无限次数")
    private Short totalExchangeConut;

    @ApiModelProperty(value = "过期天数")
    private Integer expireDay;

    @ApiModelProperty(value = "开始展示时间")
    private Date startTime;

    @ApiModelProperty(value = "结束展示时间")
    private Date endTime;

    @ApiModelProperty(value = "数量")
    private Integer count;

    @ApiModelProperty(value = "上下架状态,1上架2下架")
    private Byte status;

    @ApiModelProperty(value = "1-通用券;2-区域券")
    private Byte type;

    @ApiModelProperty(value = "奖券创建用户id")
    private Integer userId;

    @ApiModelProperty(value = "1-平台用户, 2-管理者平台用户")
    private Byte addUserType;

    @ApiModelProperty(value = "书屋ID,默认-1表示通用券")
    private Integer bookId;

    @ApiModelProperty(value = "是否开启信息收集,1开启,0关闭,默认0")
    private Boolean infoEnable;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "卡券描述")
    private String introduce;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getProperty() {
        return property;
    }

    public void setProperty(Byte property) {
        this.property = property;
    }

    public String getAwardValue() {
        return awardValue;
    }

    public void setAwardValue(String awardValue) {
        this.awardValue = awardValue == null ? null : awardValue.trim();
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

    public Integer getExchangeScore() {
        return exchangeScore;
    }

    public void setExchangeScore(Integer exchangeScore) {
        this.exchangeScore = exchangeScore;
    }

    public Integer getExchangeCount() {
        return exchangeCount;
    }

    public void setExchangeCount(Integer exchangeCount) {
        this.exchangeCount = exchangeCount;
    }

    public Short getTotalExchangeConut() {
        return totalExchangeConut;
    }

    public void setTotalExchangeConut(Short totalExchangeConut) {
        this.totalExchangeConut = totalExchangeConut;
    }

    public Integer getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(Integer expireDay) {
        this.expireDay = expireDay;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getAddUserType() {
        return addUserType;
    }

    public void setAddUserType(Byte addUserType) {
        this.addUserType = addUserType;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Boolean getInfoEnable() {
        return infoEnable;
    }

    public void setInfoEnable(Boolean infoEnable) {
        this.infoEnable = infoEnable;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
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