package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/14 19:57
 * @Description:
 */
@ApiModel("更改好友备注请求参数封装")
public class UpdateFriendRequestDto {
    @ApiModelProperty("发起者的accid")
    private String accid;
    @ApiModelProperty("要修改朋友的accid")
    private String faccid;
    @ApiModelProperty("备注")
    private String alias;

    public UpdateFriendRequestDto() {
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}

