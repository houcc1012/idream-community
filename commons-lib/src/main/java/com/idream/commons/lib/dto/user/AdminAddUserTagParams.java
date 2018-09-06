package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * Created by DELL2018-005 on 2018/6/2.
 */
@ApiModel(value = "新增和编辑用户标签请求参数dto")
public class AdminAddUserTagParams {
    @ApiModelProperty(value = "用户标签")
    @NotBlank(message = "用户标签不能为空")
    @Length(max = 15, message = "用户标签名长度不能超过15个字符")
    private String label;

    @ApiModelProperty(value = "标签id")
    private Integer tagId;

    @ApiModelProperty(value = "父级id")
    private Integer pid;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
