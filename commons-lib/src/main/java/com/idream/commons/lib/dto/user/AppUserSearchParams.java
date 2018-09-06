package com.idream.commons.lib.dto.user;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author charles
 */
@ApiModel("用户首页搜索入参")
public class AppUserSearchParams extends PagesParam {
    @NotBlank(message = "搜索条件不能为空")
    @ApiModelProperty("昵称")
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
