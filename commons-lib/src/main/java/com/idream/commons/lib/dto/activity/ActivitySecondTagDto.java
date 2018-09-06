package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.model.ActivityTag;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "关联标签返回参数")
public class ActivitySecondTagDto extends ActivityTag {

    //是否关联一级标签
    @ApiModelProperty(value = "是否关联一级标签   1:2  |  是 : 否")
    private Integer isConnectToSecond;

    public Integer getIsConnectToSecond() {
        return isConnectToSecond;
    }

    public void setIsConnectToSecond(Integer isConnectToSecond) {
        this.isConnectToSecond = isConnectToSecond;
    }


}
