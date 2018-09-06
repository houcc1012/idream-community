package com.idream.commons.lib.dto.appactivity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;
import java.util.List;

@ApiModel("喜欢类型保存")
public class AppSaveTypeDto {
    @ApiModelProperty("活动类型")
    @Size(min = 6, message = "至少选择6种类型")
    private List<AppActivityUserLikeTypeRequestDto> types;

    public List<AppActivityUserLikeTypeRequestDto> getTypes() {
        return types;
    }

    public void setTypes(List<AppActivityUserLikeTypeRequestDto> types) {
        this.types = types;
    }
}
