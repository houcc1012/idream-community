package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Description :
 * Created by DELL2018-003 on 2018/4/11.
 */
@ApiModel("抽奖奖池参数")
public class IntegrationPoolParams {

    @ApiModelProperty(value = "奖池ID")
    private Integer id;

    @ApiModelProperty(value = "奖品类型（1-通用券;2-区域券）")
    @NotNull(message = "奖品类型不能为空")
    private Byte type;

    @ApiModelProperty(value = "奖品属性（平台：1.礼品券，5.积分；中台：1实物券，2满减，3折扣，4代金）")
    @NotNull(message = "奖品类型不能为空")
    private Byte property;

    @ApiModelProperty(value = "奖品名")
    @NotBlank(message = "奖品名不能为空")
    @Size(max = 15, message = "奖品名长度不能超过15个字")
    private String name;

    @ApiModelProperty(value = "奖品说明")
    @NotBlank(message = "奖品说明不能为空")
    @Size(max = 1000, message = "奖品说明长度不能超过1000字")
    private String description;

    @ApiModelProperty(value = "奖品介绍")
    @NotBlank(message = "奖品介绍不能为空")
    @Size(max = 30, message = "奖品介绍长度不能超过30字")
    private String instructions;

    @ApiModelProperty(value = "图片")
    @NotBlank(message = "图片不能为空")
    private String image;

    @ApiModelProperty(value = "过期天数")
    @Min(value = 0, message = "最小值必须大于等于0")
    private Integer expireDay;

    @ApiModelProperty(value = "上架时间")
    @NotNull(message = "上架时间不能为空")
    private Date startTime;

    @ApiModelProperty(value = "下架时间")
    @NotNull(message = "下架时间不能为空")
    private Date endTime;

    @ApiModelProperty(value = "数量")
    @NotNull(message = "数量不能为空")
    @Min(value = 0, message = "最小值必须大于等于0")
    private Integer count;

    @ApiModelProperty(value = "概率")
    @NotNull(message = "概率不能为空")
    @DecimalMax(value = "100", message = "最大值不能大于100")
    @DecimalMin(value = "0.01", message = "最小值不能低于0.01")
    private BigDecimal probability;

    @ApiModelProperty(value = "是否收集信息,默认0不开启,1开启")
    @NotNull(message = "是否收集信息不能为空")
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
    @NotBlank(message = "图文描述不能为空")
    private String introduce;

    @ApiModelProperty(value = "创建人", hidden = true)
    private Integer userId;

    @ApiModelProperty(value = "奖品属性（-1表示通用券（为空默认为通用券），区域券存具体书屋ID）")
    private Integer bookId;

    @ApiModelProperty(value = "添加来源（1平台，2管理者）")
    private Byte addUserType;

    @ApiModelProperty(value = "区编码")
    private String adCode;

    @ApiModelProperty(value = "抽奖规则信息")
    @Valid
    private IntegrationRule integrationRule;

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

    public Byte getProperty() {
        return property;
    }

    public void setProperty(Byte property) {
        this.property = property;
    }

    public Byte getAddUserType() {
        return addUserType;
    }

    public void setAddUserType(Byte addUserType) {
        this.addUserType = addUserType;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public IntegrationRule getIntegrationRule() {
        return integrationRule;
    }

    public void setIntegrationRule(IntegrationRule integrationRule) {
        this.integrationRule = integrationRule;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}
