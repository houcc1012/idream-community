package com.idream.commons.lib.dto.appactivity;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author charles
 */
@ApiModel("活动搜索入参")
public class AppActivitySearchParams extends PagesParam {
    @ApiModelProperty("活动标题")
    @NotBlank(message = "搜索条件不能为空")
    private String title;
    @ApiModelProperty("城市编码")
    private String cityCode;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
