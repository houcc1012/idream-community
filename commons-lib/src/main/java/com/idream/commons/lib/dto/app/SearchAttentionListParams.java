package com.idream.commons.lib.dto.app;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/7/17 15:22
 */
@ApiModel(value = "app端 聊天搜索 联系人(关注的朋友)请求参数")
public class SearchAttentionListParams extends PagesParam {
    @ApiModelProperty(value = "昵称")
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
