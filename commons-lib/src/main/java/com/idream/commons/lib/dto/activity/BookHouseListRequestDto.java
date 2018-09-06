package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 书屋查询请求参数
 *
 * @param
 *
 * @author zhanfeifei
 */
@ApiModel(value = "书屋查询请求参数")
public class BookHouseListRequestDto {

    @ApiModelProperty(value = "社区名称")
    private String communityName;

    @ApiModelProperty(value = "地区编码")
    private String adCode;

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }


}
