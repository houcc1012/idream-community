package com.idream.commons.lib.dto.user;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/6/8 19:09
 */
@ApiModel(value = "banner图片列表请求参数")
public class AdminBannerImageListParams extends PagesParam {

    @ApiModelProperty(value = "typeId")
    private Integer typeId;

    @ApiModelProperty(value = "上架:下架 | 1:0")
    private Byte displayEnable;

    public Byte getDisplayEnable() {
        return displayEnable;
    }

    public void setDisplayEnable(Byte displayEnable) {
        this.displayEnable = displayEnable;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
