package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description :
 * @Created by xiaogang on 2018/5/8.
 */
@ApiModel(value = "APP粉丝新老分界维护")
public class AppUpdateFansInfoParam{

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Integer authUserId;

    @ApiModelProperty(value = "关注ID", hidden = true)
    private Integer attendId;

    public Integer getAttendId() {
        return attendId;
    }

    public void setAttendId(Integer attendId) {
        this.attendId = attendId;
    }

    public Integer getAuthUserId() {
        return authUserId;
    }

    public void setAuthUserId(Integer authUserId) {
        this.authUserId = authUserId;
    }
}
