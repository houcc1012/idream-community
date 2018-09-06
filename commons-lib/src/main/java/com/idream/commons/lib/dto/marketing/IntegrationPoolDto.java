package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Description :
 * Created by DELL2018-003 on 2018/4/11.
 */
@ApiModel("抽奖奖池返回")
public class IntegrationPoolDto {

    @ApiModelProperty(value = "奖池ID")
    private Integer id;

    @ApiModelProperty(value = "奖品类型（1-通用券;2-区域券）")
    private Integer type;

    @ApiModelProperty(value = "奖品属性（平台：1.礼品券，2.积分；中台：1实物券，2满减，3折扣，4代金）")
    private Integer property;

    @ApiModelProperty(value = "奖品名")
    private String name;

    @ApiModelProperty(value = "奖品说明")
    private String description;

    @ApiModelProperty(value = "奖品介绍")
    private String instructions;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "过期天数")
    private String expireDay;

    @ApiModelProperty(value = "上架时间")
    private Date startTime;

    @ApiModelProperty(value = "下架时间")
    private Date endTime;

    @ApiModelProperty(value = "数量")
    @Min(0)
    private Integer count;

    @ApiModelProperty(value = "概率")
    private BigDecimal probability;

    @ApiModelProperty(value = "是否收集信息,默认0不开启,1开启")
    private Boolean infoEnable;

    @ApiModelProperty(value = "1上架,2下架,默认下架", hidden = true)
    private Integer status;

    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "收集信息ID")
    private Integer infoId[];

    @ApiModelProperty(value = "图文描述")
    private String introduce;

    @ApiModelProperty(value = "创建人", hidden = true)
    private Integer userId;

    @ApiModelProperty(value = "奖品属性（-1表示通用券（为空默认为通用券），区域券存具体书屋ID）")
    private Integer bookId;

    @ApiModelProperty(value = "书屋名称")
    private String bookHouseName;

    @ApiModelProperty(value = "添加来源（1平台，2管理者）")
    private Integer addUserType;

    @ApiModelProperty(value = "区编码")
    private String adCode;

    @ApiModelProperty(value = "奖品值,json字符串.如7折折扣券 {'discount':'7'}...")
    private String integrationValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
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

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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

    public String getIntegrationValue() {
        return integrationValue;
    }

    public void setIntegrationValue(String integrationValue) {
        this.integrationValue = integrationValue;
    }

    public String getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(String expireDay) {
        this.expireDay = expireDay;
    }

    public String getBookHouseName() {
        return bookHouseName;
    }

    public void setBookHouseName(String bookHouseName) {
        this.bookHouseName = bookHouseName;
    }
}
