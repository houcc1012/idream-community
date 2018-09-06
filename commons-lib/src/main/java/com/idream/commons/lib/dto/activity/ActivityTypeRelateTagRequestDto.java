package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @Auther: penghekai
 * @Date: 2018/6/15 13:07
 * @Description:
 */
@ApiModel("活动类型关联活动标签请求参数")
public class ActivityTypeRelateTagRequestDto {

    @ApiModelProperty("活动类型")
    @NotNull
    private Integer typeId;

    @ApiModelProperty("所有一级活动标签id")
    @Size(max = 5, message = "一级标签个数必须在0-5之间")
    private List<Integer> firstTagIds;

    @ApiModelProperty("所有二级活动标签id")
    @Size(max = 20, message = "二级标签个数必须在0-20之间")
    private List<Integer> secondTagIds;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public List<Integer> getFirstTagIds() {
        return firstTagIds;
    }

    public void setFirstTagIds(List<Integer> firstTagIds) {
        this.firstTagIds = firstTagIds;
    }

    public List<Integer> getSecondTagIds() {
        return secondTagIds;
    }

    public void setSecondTagIds(List<Integer> secondTagIds) {
        this.secondTagIds = secondTagIds;
    }
}

