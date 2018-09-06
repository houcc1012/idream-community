package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 积分奖池表
 */
@ApiModel("积分奖池表")
public class IntegrationPool {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "1-通用券;2-区域券")
    private Byte type;

    @ApiModelProperty(value = "奖品名")
    private String name;

    @ApiModelProperty(value = "1礼品券,2积分3空")
    private Byte property;

    @ApiModelProperty(value = "奖品值,json字符串.如7折折扣券 {'discount':'7'}...")
    private String integrationValue;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "介绍")
    private String instructions;

    @ApiModelProperty(value = "过期天数")
    private Integer expireDay;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束展示时间")
    private Date endTime;

    @ApiModelProperty(value = "数量")
    private Integer count;

    @ApiModelProperty(value = "概率")
    private BigDecimal probability;

    @ApiModelProperty(value = "是否收集信息,默认0不开启,1开启")
    private Boolean infoEnable;

    @ApiModelProperty(value = "1上架,2下架")
    private Byte status;

    @ApiModelProperty(value = "奖券创建用户id")
    private Integer userId;

    @ApiModelProperty(value = "1-平台用户, 2-管理者平台用户")
    private Byte addUserType;

    @ApiModelProperty(value = "书屋ID,默认-1表示通用券")
    private Integer bookId;

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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

    public String getIntegrationValue() {
        return integrationValue;
    }

    public void setIntegrationValue(String integrationValue) {
        this.integrationValue = integrationValue == null ? null : integrationValue.trim();
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

    public BigDecimal getProbability() {
        return probability;
    }

    public void setProbability(BigDecimal probability) {
        this.probability = probability;
    }

    public Boolean getInfoEnable() {
        return infoEnable;
    }

    public void setInfoEnable(Boolean infoEnable) {
        this.infoEnable = infoEnable;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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