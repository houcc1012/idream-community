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
 * Created by DELL2018-003 on 2018/4/11.
 */
@ApiModel("后台兑奖奖池")
public class AdminAwardPoolDto {

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
    private String expireDay;

    @ApiModelProperty(value = "上架时间 格式yyyy-MM-dd")
    private Date startTime;

    @ApiModelProperty(value = "下架时间 格式yyyy-MM-dd")
    private Date endTime;

    @ApiModelProperty(value = "数量")
    private Integer count;

    @ApiModelProperty(value = "是否收集信息,默认0不开启,1开启")
    private Boolean infoEnable;

    @ApiModelProperty(value = "1上架,2下架")
    private Integer status;

    @ApiModelProperty(value = "生成时间", hidden = true)
    private Date createTime;

    @ApiModelProperty(value = "修改时间", hidden = true)
    private Date updateTime;

    @ApiModelProperty(value = "收集信息ID")
    private Integer infoId[];

    @ApiModelProperty(value = "奖品描述")
    private String introduce;

    @ApiModelProperty(value = "总计兑换次数 -1,表示无限次数")
    private Integer totalExchangeConut;

    @ApiModelProperty(value = "创建人", hidden = true)
    private Integer userId;

    @ApiModelProperty(value = "奖品属性（-1表示通用券，区域券传regionID）")
    private Integer bookId;

    @ApiModelProperty(value = "书屋名")
    private String bookName;

    @ApiModelProperty(value = "奖品类型（1-通用券;2-区域券）")
    private Integer type;

    @ApiModelProperty(value = "奖品属性（1礼品券）")
    private Integer property;

    @ApiModelProperty(value = "添加来源（1平台，2管理者）")
    private Integer addUserType;

    @ApiModelProperty(value = "区编码")
    private String adCode;

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

    public Integer[] getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer[] infoId) {
        this.infoId = infoId;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getTotalExchangeConut() {
        return totalExchangeConut;
    }

    public void setTotalExchangeConut(Integer totalExchangeConut) {
        this.totalExchangeConut = totalExchangeConut;
    }

    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    public Integer getAddUserType() {
        return addUserType;
    }

    public void setAddUserType(Integer addUserType) {
        this.addUserType = addUserType;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
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

    public String getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(String expireDay) {
        this.expireDay = expireDay;
    }
}
