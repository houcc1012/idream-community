package com.idream.commons.lib.dto.app;

import com.idream.commons.lib.dto.PagesParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "通知列表的请求dto")
public class NoticeListRequestDto extends PagesParam {

    @ApiModelProperty(value = "消息内容")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
