package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "根据书屋管理者查询关联社区返回参数")
public class FindUserAsocciationCommunityResponseDto {

    @ApiModelProperty(value = "社区id")
    private Integer communityId;
    @ApiModelProperty(value = "书屋id")
    private Integer bookId;
    @ApiModelProperty(value = "社区名")
    private String communityName;

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}
