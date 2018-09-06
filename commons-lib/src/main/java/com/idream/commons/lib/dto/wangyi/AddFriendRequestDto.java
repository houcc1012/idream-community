package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/14 19:40
 * @Description:
 */
@ApiModel("添加好友请求参数封装")
public class AddFriendRequestDto {

    @ApiModelProperty("加好友的发起者accid")
    private String accid;
    @ApiModelProperty("对方accid")
    private String faccid;

    //@ApiModelProperty
    //private String type;
    @ApiModelProperty("加好友请求的消息")
    private String msg;


    public AddFriendRequestDto() {
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getFaccid() {
        return faccid;
    }

    public void setFaccid(String faccid) {
        this.faccid = faccid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

