package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Description :
 * Created by houcc
 */
@ApiModel("前台展示可兑换列表")
public class AwardPoolDto {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "奖品名")
    private String name;

    @ApiModelProperty(value = "奖品图片")
    private String image;

    @ApiModelProperty(value = "奖品说明")
    private String description;

    @ApiModelProperty(value = "奖品介绍")
    private String instructions;

    @ApiModelProperty(value = "积分值")
    private Integer exchangeScore;

    @ApiModelProperty(value = "兑换次数,每天")
    private Integer exchangeCount;

    @ApiModelProperty(value = "过期天数")
    @Min(0)
    private Integer expireDay;

    @ApiModelProperty(value = "上架时间 格式yyyy-MM-dd")
    private Date startTime;

    @ApiModelProperty(value = "下架时间 格式yyyy-MM-dd")
    private Date endTime;

    @ApiModelProperty(value = "数量")
    private Integer count;

    @ApiModelProperty(value = "是否收集信息,默认0不开启,1开启")
    private Boolean infoEnable;

    @ApiModelProperty(required = true, value = "1上架,2下架")
    private Integer status;

    @ApiModelProperty(value = "奖品描述")
    private String introduce;

    @ApiModelProperty(value = "总计兑换次数 -1,表示无限次数")
    private Integer totalExchangeCount;

    @ApiModelProperty(value = "奖品类型")
    private Integer type;

    @ApiModelProperty(value = "奖品属性（1礼品券 2满减 3折扣 4代金券 5积分）")
    @NotNull(message = "奖品类型不能为空")
    private Integer property;

    @ApiModelProperty(value = "距离")
    private double distance;

    @ApiModelProperty(value = "距离近的书屋名称")
    private String bookName;
    @ApiModelProperty(value = "奖品属性（-1表示通用券，区域券传regionID）")
    private Integer bookId;

    @ApiModelProperty(value = "券规则")
    private String awardValue;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(Integer expireDay) {
        this.expireDay = expireDay;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getExchangeCount() {
        return exchangeCount;
    }

    public void setExchangeCount(Integer exchangeCount) {
        this.exchangeCount = exchangeCount;
    }

    public Integer getExchangeScore() {
        return exchangeScore;
    }

    public void setExchangeScore(Integer exchangeScore) {
        this.exchangeScore = exchangeScore;
    }

    public Boolean getInfoEnable() {
        return infoEnable;
    }

    public void setInfoEnable(Boolean infoEnable) {
        this.infoEnable = infoEnable;
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


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getTotalExchangeCount() {
        return totalExchangeCount;
    }

    public void setTotalExchangeCount(Integer totalExchangeCount) {
        this.totalExchangeCount = totalExchangeCount;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAwardValue() {
        return awardValue;
    }

    public void setAwardValue(String awardValue) {
        this.awardValue = awardValue;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
