package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(value = "搜索社区(社区列表)")
public class CommunityInfoResponseDto {

    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    @ApiModelProperty(value = "社区名称")
    private String communityName;

    @ApiModelProperty(value = "社区地址")
    private String address;

    @ApiModelProperty(value = "社区区域编码")
    private Integer adCode;

    @ApiModelProperty(value = "社区认证状态 0:1 | 未认证:认证")
    private Integer status = 0;

    @ApiModelProperty(value = "书屋id")
    private Integer bookId;

    @ApiModelProperty(value = "距离")
    private BigDecimal distance;

    @ApiModelProperty(value = "大社区名")
    private String regionName;

    @ApiModelProperty(value = "书屋名 多个用逗号隔开")
    private String bookName;

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionName() {
        return regionName;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAdCode() {
        return adCode;
    }

    public void setAdCode(Integer adCode) {
        this.adCode = adCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}