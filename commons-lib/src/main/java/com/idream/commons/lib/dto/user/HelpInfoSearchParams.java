package com.idream.commons.lib.dto.user;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 帮助表
 */
@ApiModel("帮助中心搜索条件")
public class HelpInfoSearchParams extends PagesParam {

    @ApiModelProperty(value = "类型id")
    private Integer typeId;

    @ApiModelProperty(value = "标题")
    private String title;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

}