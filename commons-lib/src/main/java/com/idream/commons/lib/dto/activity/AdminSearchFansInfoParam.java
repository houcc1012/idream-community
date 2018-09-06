package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description :
 * @Created by xiaogang on 2018/5/8.
 */
@ApiModel(value = "粉丝搜索条件")
public class AdminSearchFansInfoParam extends PagesParam {

    @ApiModelProperty("用户ID")
    @NotNull(message = "用户ID不能为null")
    private Integer userId;

    @ApiModelProperty("查询类型（1为我的粉丝，2为我的关注，3为互相关注）")
    @NotNull(message = "查询类型不能为null")
    private Integer type;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
