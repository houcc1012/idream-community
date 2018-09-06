package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: penghekai
 * @Date: 2018/6/20 13:39
 * @Description:
 */
@ApiModel("新增活动标签请求参数")
public class ActivityTagAddRequestDto {

    @ApiModelProperty("父标签id")
    private Integer pid;

    @Length(max = 6, message = "标签名不超过6个字符")
    @NotBlank(message = "标签名不能为空")
    @ApiModelProperty("标签名")
    private String label;

    @ApiModelProperty("标签级别(1 一级，2二级，3三级)")
    private Integer level;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}

