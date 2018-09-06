package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "查询书屋关联的社区返回参数")
public class FindBookAssociationCommunityResponseDto {

    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "城市")
    private String city;
    @ApiModelProperty(value = "城市编码")
    private String cityCode;
    @ApiModelProperty(value = "区域")
    private String district;
    @ApiModelProperty(value = "社区id")
    private Integer communityId;
    @ApiModelProperty(value = "社区")
    private String communityName;
    @ApiModelProperty(value = "书屋")
    private String bookName;
    @ApiModelProperty(value = "累计发布活动")
    private Integer publishedTotal;
    @ApiModelProperty(value = "社区人数")
    private Integer communityUserCount;

    public Integer getCommunityUserCount() {
        return communityUserCount;
    }

    public void setCommunityUserCount(Integer communityUserCount) {
        this.communityUserCount = communityUserCount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getPublishedTotal() {
        return publishedTotal;
    }

    public void setPublishedTotal(Integer publishedTotal) {
        this.publishedTotal = publishedTotal;
    }
}
