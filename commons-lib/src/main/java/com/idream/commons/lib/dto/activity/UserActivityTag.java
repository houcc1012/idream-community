package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;

/**
 * @author: ZhengGaosheng
 * @date: 2018/8/23 23:03
 * @description:
 */
@ApiModel("用户参加活动标签搜集")
public class UserActivityTag {

    private Integer tagId;

    private String tagName;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}

