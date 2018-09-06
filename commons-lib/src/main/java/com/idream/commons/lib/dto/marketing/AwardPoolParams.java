package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

/**
 * Description :
 * Created by DELL2018-003 on 2018/4/11.
 */
@ApiModel("兑奖奖池")
public class AwardPoolParams {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "奖品名")
    @NotBlank(message = "奖品名不能为空")
    @Size(max = 15, message = "长度不能超过15个字")
    private String name;

    @ApiModelProperty(value = "奖品图片")
    @NotBlank(message = "图片不能为空")
    private String image;

    @ApiModelProperty(value = "奖品说明")
    @Size(max = 1000, message = "奖品说明长度不能超过1000字")
    @NotBlank(message = "奖品说明不能为空")
    private String description;

    @ApiModelProperty(value = "奖品介绍")
    @Size(max = 30, message = "奖品介绍长度不能超过30字")
    @NotBlank(message = "奖品说明不能为空")
    private String instructions;

    @ApiModelProperty(value = "积分值")
    @NotNull(message = "积分值不能为空")
    @Min(value = 0, message = "最小值必须大于等于0")
    private Integer exchangeScore;

    @ApiModelProperty(value = "兑换次数,每天")
    @NotNull(message = "兑换次数不能为空")
    @Min(value = -1, message = "值必须大于等于-1")
    private Short exchangeCount;

    @ApiModelProperty(value = "过期天数")
    @Min(value = 0, message = "最小值必须大于等于0")
    private Integer expireDay;

    @ApiModelProperty(value = "上架时间 格式yyyy-MM-dd")
    @NotNull(message = "上架时间不能为空")
    private Date startTime;

    @ApiModelProperty(value = "下架时间 格式yyyy-MM-dd")
    @NotNull(message = "下架时间不能为空")
    private Date endTime;

    @ApiModelProperty(value = "数量")
    @NotNull(message = "数量不能为空")
    @Min(value = 0, message = "最小值必须大于等于0")
    private Integer count;

    @ApiModelProperty(value = "是否收集信息,默认0不开启,1开启")
    @NotNull(message = "是否收集信息不能为空")
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
    @NotBlank(message = "奖品描述不能为空")
    private String introduce;

    @ApiModelProperty(value = "总计兑换次数 -1,表示无限次数")
    private Short totalExchangeConut;

    @ApiModelProperty(value = "创建人", hidden = true)
    private Integer userId;

    @ApiModelProperty(value = "奖品属性（-1表示通用券，区域券传regionID）")
    private Integer bookId;

    @ApiModelProperty(value = "奖品类型（1-通用券;2-区域券）")
    @NotNull(message = "奖品类型不能为空")
    private Byte type;

    @ApiModelProperty(value = "奖品属性（1实物券，2满减，3折扣，4代金）")
    @NotNull(message = "奖品类型不能为空")
    private Byte property;

    @ApiModelProperty(value = "添加来源（1平台，2管理者）")
    private Byte addUserType;

    @ApiModelProperty(value = "区编码")
    private String adCode;

    @ApiModelProperty(value = "距离")
    private double distance;

    @ApiModelProperty(value = "距离近的书屋名称")
    private String bookName;

    @ApiModelProperty(value = "券规则")
    @Valid
    private AwardRule awardValue;

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

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public AwardRule getAwardValue() {
        return awardValue;
    }

    public void setAwardValue(AwardRule awardValue) {
        this.awardValue = awardValue;
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


    public Short getTotalExchangeConut() {
        return totalExchangeConut;
    }

    public void setTotalExchangeConut(Short totalExchangeConut) {
        this.totalExchangeConut = totalExchangeConut;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

    public Short getExchangeCount() {
        return exchangeCount;
    }

    public void setExchangeCount(Short exchangeCount) {
        this.exchangeCount = exchangeCount;
    }
}
