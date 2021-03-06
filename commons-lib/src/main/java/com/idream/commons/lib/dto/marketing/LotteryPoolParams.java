package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Description :
 * Created by DELL2018-003 on 2018/4/12.
 */

@ApiModel("开奖奖池")
public class LotteryPoolParams {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "抽奖id")
    private Integer lotteryId;

    @ApiModelProperty(value = "奖品名")
    @NotBlank(message = "奖品名不能为空")
    @Size(max = 15, message = "奖品名长度不能超过15个字")
    private String awardName;

    @ApiModelProperty(value = "奖品图片")
    @NotBlank(message = "图片不能为空")
    private String image;

    @ApiModelProperty(value = "说明")
    @NotBlank(message = "说明不能为空")
    @Size(max = 1000, message = "说明不能超过1000字")
    private String description;

    @ApiModelProperty(value = "介绍")
    @NotBlank(message = "介绍不能为空")
    @Size(max = 30, message = "介绍长度不能超过30字")
    private String instructions;

    @ApiModelProperty(value = "描述")
    @NotBlank(message = "描述不能为空")
    private String introduce;

    @ApiModelProperty(value = "过期天数")
    private Integer expireDay;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束展示时间")
    private Date endTime;

    @ApiModelProperty(value = "百分比")
    @NotNull(message = "概率不能为空")
    @DecimalMax(value = "100", message = "最大值不能大于100")
    @DecimalMin(value = "0.01", message = "最小值不能低于0.01")
    private BigDecimal probability;

    @ApiModelProperty(value = "数量")
    @NotNull(message = "数量不能为空")
    @Min(value = 0, message = "最小值为0")
    private Integer count;

    @ApiModelProperty(value = "默认1开启,0不开启")
    @NotNull(message = "是否需要填写报名信息不能为空")
    private Boolean infoEnable;

    @ApiModelProperty(value = "1上架,2下架")
    private Integer status;

    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "信息项")
    private Integer[] infoIds;

    @ApiModelProperty(value = "开奖时间点")
    private List<LotteryTimeDto> times;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Integer lotteryId) {
        this.lotteryId = lotteryId;
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

    public BigDecimal getProbability() {
        return probability;
    }

    public void setProbability(BigDecimal probability) {
        this.probability = probability;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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


    public Integer[] getInfoIds() {
        return infoIds;
    }

    public void setInfoIds(Integer[] infoIds) {
        this.infoIds = infoIds;
    }

    public List<LotteryTimeDto> getTimes() {
        return times;
    }

    public void setTimes(List<LotteryTimeDto> times) {
        this.times = times;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
