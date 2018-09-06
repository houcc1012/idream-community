package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/8 17:08
 * @Description:
 */
@ApiModel("更新用户消息反馈接受参数实体封装")
public class UpdateUserFeedBackRequestDto {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    @NotNull(message = "反馈id不能为空")
    private Integer id;

    /**
     * 反馈信息是否处理 0否;1-是
     */
    @ApiModelProperty(value = "反馈信息是否处理 false-否;true-是")
    private Boolean infoIsHandle;

    public UpdateUserFeedBackRequestDto() {
    }

    public Boolean getInfoIsHandle() {
        return infoIsHandle;
    }

    public void setInfoIsHandle(Boolean infoIsHandle) {
        this.infoIsHandle = infoIsHandle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

