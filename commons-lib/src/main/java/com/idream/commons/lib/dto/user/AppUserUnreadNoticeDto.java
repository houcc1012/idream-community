package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author charles
 */
@ApiModel("未读消息")
public class AppUserUnreadNoticeDto {
    @ApiModelProperty("总未读数")
    private Integer totalNum;
    @ApiModelProperty("点赞未读")
    private Integer likeNum;
    @ApiModelProperty("粉丝未读")
    private Integer fansNum;
    @ApiModelProperty("系统未读")
    private Integer sysNum;
    @ApiModelProperty("动态未读")
    private Integer lifeNum;

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getFansNum() {
        return fansNum;
    }

    public void setFansNum(Integer fansNum) {
        this.fansNum = fansNum;
    }

    public Integer getSysNum() {
        return sysNum;
    }

    public void setSysNum(Integer sysNum) {
        this.sysNum = sysNum;
    }

    public Integer getLifeNum() {
        return lifeNum;
    }

    public void setLifeNum(Integer lifeNum) {
        this.lifeNum = lifeNum;
    }
}
