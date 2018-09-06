package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @Author: houcc
 * @Date: 2018/6/14
 */
public class UserRegionPoolInfo {
    @ApiModelProperty(value = "社区ID")
    private Integer regionId;
    @ApiModelProperty(value = "书屋ID")
    private Integer bookId;
    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;
    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;
    @ApiModelProperty(value = "积分抽奖池ID")
    private Integer integrationPoolId;
    @ApiModelProperty(value = "数量")
    private Integer awardCount;

    @ApiModelProperty(value = "书屋经度")
    private BigDecimal bookLongitude;
    @ApiModelProperty(value = "书屋纬度")
    private BigDecimal bookLatitude;


    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
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

    public Integer getIntegrationPoolId() {
        return integrationPoolId;
    }

    public void setIntegrationPoolId(Integer integrationPoolId) {
        this.integrationPoolId = integrationPoolId;
    }

    public Integer getAwardCount() {
        return awardCount;
    }

    public void setAwardCount(Integer count) {
        this.awardCount = count;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public BigDecimal getBookLongitude() {
        return bookLongitude;
    }

    public void setBookLongitude(BigDecimal bookLongitude) {
        this.bookLongitude = bookLongitude;
    }

    public BigDecimal getBookLatitude() {
        return bookLatitude;
    }

    public void setBookLatitude(BigDecimal bookLatitude) {
        this.bookLatitude = bookLatitude;
    }
}
