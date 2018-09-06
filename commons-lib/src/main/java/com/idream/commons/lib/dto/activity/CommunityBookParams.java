package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 编辑社区和书屋请求参数
 *
 * @param
 *
 * @author zhanfeifei
 */
@ApiModel(value = "编辑社区和书屋请求参数")
public class CommunityBookParams extends CommunityBookHouseParams {

    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    @ApiModelProperty(value = "书屋id")
    private Integer bookHouseId;

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getBookHouseId() {
        return bookHouseId;
    }

    public void setBookHouseId(Integer bookHouseId) {
        this.bookHouseId = bookHouseId;
    }


}
