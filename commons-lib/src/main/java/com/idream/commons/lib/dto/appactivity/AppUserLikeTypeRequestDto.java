package com.idream.commons.lib.dto.appactivity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @Auther: penghekai
 * @Date: 2018/7/17 14:57
 * @Description:
 */
@ApiModel("感兴趣活动请求参数")
public class AppUserLikeTypeRequestDto {

    @ApiModelProperty("活动id")
    @Size(min = 6, message = "至少选择6种类型")
    private List<Integer> typeIds;

    public List<Integer> getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(List<Integer> typeIds) {
        this.typeIds = typeIds;
    }
}

