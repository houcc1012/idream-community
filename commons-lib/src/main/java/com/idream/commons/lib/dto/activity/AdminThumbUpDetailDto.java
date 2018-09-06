package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/5/9.
 */

@ApiModel(value = "点赞详情")
public class AdminThumbUpDetailDto extends AppNeighborInfoDto {

    @ApiModelProperty("点赞时间")
    private Date createTime;

    @ApiModelProperty("社区信息")
    private List<AppCommunityInfoDto> communityList;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<AppCommunityInfoDto> getCommunityList() {
        return communityList;
    }

    public void setCommunityList(List<AppCommunityInfoDto> communityList) {
        this.communityList = communityList;
    }
}
