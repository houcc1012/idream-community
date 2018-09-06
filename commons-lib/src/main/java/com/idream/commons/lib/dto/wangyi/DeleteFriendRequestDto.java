package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/14 20:02
 * @Description:
 */
@ApiModel("删除好友请求参数封装")
public class DeleteFriendRequestDto {
    @ApiModelProperty("发起者的accid")
    private String accid;
    @ApiModelProperty("要删除朋友的accid")
    private String faccid;

    public DeleteFriendRequestDto() {
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
}

