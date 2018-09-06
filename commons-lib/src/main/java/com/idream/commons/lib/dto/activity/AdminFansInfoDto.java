package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/5/8.
 */
@ApiModel(value = "粉丝列表")
public class AdminFansInfoDto extends AppNeighborInfoDto {

    @ApiModelProperty("关注时间")
    private Date createTime;

    @ApiModelProperty("电话号码")
    private String phone;

    @ApiModelProperty("社区信息")
    private List<AppCommunityInfoDto> communityList;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<AppCommunityInfoDto> getCommunityList() {
        return communityList;
    }

    public void setCommunityList(List<AppCommunityInfoDto> communityList) {
        this.communityList = communityList;
    }
}
