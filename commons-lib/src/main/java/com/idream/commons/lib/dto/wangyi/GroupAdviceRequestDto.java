package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/17 14:41
 * @Description:
 */
@ApiModel("群组内加入成员推送通知")
public class GroupAdviceRequestDto {

    @ApiModelProperty("发送者accid")
    private String from;

    @ApiModelProperty("0：点对点个人消息，1：群消息（高级群），其他返回414")
    private String ope;

    @ApiModelProperty("ope==0是表示accid即用户id，ope==1表示tid即群id")
    private String to;

    @ApiModelProperty("发送通知这里写10")
    private String type;

    @ApiModelProperty("发送内容json")
    private String body;

    @ApiModelProperty("拓展字段")
    private String ext;


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getOpe() {
        return ope;
    }

    public void setOpe(String ope) {
        this.ope = ope;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}

