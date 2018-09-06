package com.idream.commons.lib.model;

import java.util.List;

import com.idream.commons.lib.dto.appactivity.AppActivityInfoResponseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("根据活动id产看详细信息返回参数")
public class AppActivityDetailResponseDto extends AppActivityInfoResponseDto {

    @ApiModelProperty("报名设置")
    private List<InformationRule> registInfo;
    @ApiModelProperty("活动标签")
    private List<TagInfo> tagInfo;

}
