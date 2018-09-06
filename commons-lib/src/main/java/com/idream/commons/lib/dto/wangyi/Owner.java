/**
 * Copyright 2018 bejson.com
 */
package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/13 16:50
 * @Description:
 */
@ApiModel("查询群组详情,群主资料返回封装")
public class Owner {

    @ApiModelProperty("创建时间")
    private long createtime;

    @ApiModelProperty("修改时间")
    private long updatetime;

    @ApiModelProperty("昵称")
    private String nick;

    @ApiModelProperty("群主accid")
    private String accid;

    @ApiModelProperty("")
    private boolean mute;

    @ApiModelProperty("")
    private String custom;

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setUpdatetime(long updatetime) {
        this.updatetime = updatetime;
    }

    public long getUpdatetime() {
        return updatetime;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getAccid() {
        return accid;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public boolean getMute() {
        return mute;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getCustom() {
        return custom;
    }

}