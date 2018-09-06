package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

/**
 * @author: ZhengGaosheng
 * @date: 2018/7/3 12:10
 * @description:
 */
@ApiModel("校验活动是否能参加返回")
public class CheckJoinActivity {

    @ApiModelProperty("返回标志 true:成功, false失败")
    private boolean flag;

    @ApiModelProperty("返回消息")
    private String message;

    @ApiModelProperty("返回状态码. 0000:成功, 0001:没有权限参加, 0002:人数到上线, 0003:报名信息不未搜集")
    private String retCode;


    public CheckJoinActivity() {
    }

    public CheckJoinActivity(boolean flag, String message, String retCode) {
        this.flag = flag;
        this.message = message;
        this.retCode = retCode;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }
}

