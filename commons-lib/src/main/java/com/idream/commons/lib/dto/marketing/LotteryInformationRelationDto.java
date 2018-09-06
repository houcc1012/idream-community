package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Description :
 * Created by DELL2018-003 on 2018/4/13.
 */
public class LotteryInformationRelationDto {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 产品池id
     */
    @ApiModelProperty(value = "产品池id")
    private Integer lotteryPoolId;

    /**
     * 信息id
     */
    @ApiModelProperty(value = "信息名称")
    private String infoName;

    /**
     * 信息名称
     */
    @ApiModelProperty(value = "信息id")
    private Integer infoId;


    /**
     * 生成时间
     */
    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLotteryPoolId() {
        return lotteryPoolId;
    }

    public void setLotteryPoolId(Integer lotteryPoolId) {
        this.lotteryPoolId = lotteryPoolId;
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
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


    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }
}
