package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description :
 * Created by DELL2018-003 on 2018/4/16.
 */
@ApiModel("发现频道返回状态")
public class FoundStatusDto {

    @ApiModelProperty(value = "今天是否签过到")
    private Boolean isSigned;

    @ApiModelProperty(value = "用户参与的活动是否有活动")
    private Boolean havePrize;

    public Boolean getSigned() {
        return isSigned;
    }

    public void setSigned(Boolean signed) {
        isSigned = signed;
    }

    public Boolean getHavePrize() {
        return havePrize;
    }

    public void setHavePrize(Boolean havePrize) {
        this.havePrize = havePrize;
    }
}
