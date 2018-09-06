package com.idream.commons.lib.dto.getui;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("个推推送消息请求参数")
public class PushMessageRequestDto {

    @ApiModelProperty(value = "推送标题")
    private String title;

    @ApiModelProperty(value = "推送内容")
    private String text;

    @ApiModelProperty(value = "点击跳转链接")
    private String url;

    @ApiModelProperty(value = "用户id")
    private String cid;

    @ApiModelProperty(value = "透传信息")
    private Map<String, Object> customContent;

    @ApiModelProperty(value = "用户别名")
    private String alias;

    @ApiModelProperty(value = "指定用户列表")
    private List<String> cids;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Map<String, Object> getCustomContent() {
        return customContent;
    }

    public void setCustomContent(Map<String, Object> customContent) {
        this.customContent = customContent;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<String> getCids() {
        return cids;
    }

    public void setCids(List<String> cids) {
        this.cids = cids;
    }

}
