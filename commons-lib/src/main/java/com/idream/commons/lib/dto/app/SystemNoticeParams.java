package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "编辑修改系统消息请求dto")
public class SystemNoticeParams {

    @ApiModelProperty(value = "系统消息id")
    private Integer id;

    @ApiModelProperty(value = "系统消息内容")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
