package com.idream.commons.lib.dto.app;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/7/17 14:54
 */
@ApiModel(value = "app端 聊天搜索 趣聊(已加入活动的趣聊) 请求参数")
public class SearchChatListParams extends PagesParam {
    @ApiModelProperty(value = "群 名称")
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
