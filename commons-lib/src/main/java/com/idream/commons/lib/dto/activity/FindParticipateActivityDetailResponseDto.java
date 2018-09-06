package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "查询参与活动明细返回参数")
public class FindParticipateActivityDetailResponseDto extends FindPublishedActivityDetailResponseDto {

    @ApiModelProperty(value = "发布者")
    private String publisher;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

}
