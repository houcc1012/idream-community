package com.idream.commons.lib.dto.user;

import com.idream.commons.lib.dto.region.RegionCommunityDto;
import com.idream.commons.lib.model.RegionInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("管理员信息")
public class ManagerInfoDto {
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "账号名称")
    private String accountName;
    @ApiModelProperty(value = "用户昵称")
    private String nickName;
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "书屋id")
    private Integer bookId;
    @ApiModelProperty(value = "书屋名")
    private String bookHouse;
    @ApiModelProperty(value = "地区编码")
    private String adCode;
    @ApiModelProperty(value = "书屋管理的小社区")
    private List<RegionCommunityDto> communities;
    @ApiModelProperty(value = "管理的大社区")
    private List<RegionInfo> regions;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getBookHouse() {
        return bookHouse;
    }

    public void setBookHouse(String bookHouse) {
        this.bookHouse = bookHouse;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<RegionCommunityDto> getCommunities() {
        return communities;
    }

    public void setCommunities(List<RegionCommunityDto> communities) {
        this.communities = communities;
    }

    public List<RegionInfo> getRegions() {
        return regions;
    }

    public void setRegions(List<RegionInfo> regions) {
        this.regions = regions;
    }
}
