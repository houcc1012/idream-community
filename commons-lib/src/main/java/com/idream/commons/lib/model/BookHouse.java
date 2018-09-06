package com.idream.commons.lib.model;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 书屋表
 */
@ApiModel("书屋表")
public class BookHouse {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 书屋编码,六位数字,添加书屋时,随机生成添加
     */
    @ApiModelProperty(value = "书屋编码,六位数字,添加书屋时,随机生成添加")
    private String code;

    /**
     * 书屋名
     */
    @ApiModelProperty(value = "书屋名")
    private String name;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    /**
     * 地区编码
     */
    @ApiModelProperty(value = "地区编码")
    private String adCode;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    /**
     * 书屋描述
     */
    @ApiModelProperty(value = "书屋描述")
    private String description;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode == null ? null : adCode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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