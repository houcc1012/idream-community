package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;

/**
 * @author charles
 */
@ApiModel("用户标签")
public class AppUserSearchTagDto {
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
