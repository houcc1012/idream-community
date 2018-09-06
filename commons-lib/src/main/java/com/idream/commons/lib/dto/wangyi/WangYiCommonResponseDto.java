package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/12 13:34
 * @Description:
 */
@ApiModel("网易公共参数返回封装")
public class WangYiCommonResponseDto {

    @ApiModelProperty("返回标记,true成功,false失败")
    private boolean flag;

    @ApiModelProperty("返回信息")
    private String msg;


    private String resultCode;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public WangYiCommonResponseDto() {
    }

    public WangYiCommonResponseDto(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static WangYiCommonResponseDto returnErrorData(String resultCode) {
        WangYiCommonResponseDto data = new WangYiCommonResponseDto();
        data.setResultCode(resultCode);
        return data;
    }
}

