package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户拉黑信息")
public class AppUserBlackDto {
    @ApiModelProperty("是否拉黑")
    private Boolean black = false;

    public Boolean getBlack() {
        return black;
    }

    public void setBlack(Boolean black) {
        this.black = black;
    }
}
