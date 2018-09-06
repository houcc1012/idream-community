package com.idream.commons.lib.dto;

import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.base.RetMessageConstans;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author hejiang
 */
@ApiModel("公共返回参数")
public class JSONPublicDto<T> implements Serializable {

    @ApiModelProperty(value = "返回code", required = true)
    private String retCode = RetCodeConstants.SUCESS;

    @ApiModelProperty(value = "返回说明", required = false)
    private String retMsg = "";

    @ApiModelProperty(value = "返回数据", required = false)
    private T responseData;

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }


    /**
     * @Author: hejiang
     * @Description: 报错返回
     * @Date: 17:01 2018/3/22
     */
    public static JSONPublicDto returnErrorData(String resultCode) {
        JSONPublicDto data = new JSONPublicDto();
        data.setRetCode(resultCode);
        data.setRetMsg(RetMessageConstans.getRetMessage().get(resultCode));
        return data;
    }

    /**
     * @Author: hejiang
     * @Description: 报错返回
     * @Date: 17:01 2018/3/22
     */
    public static JSONPublicDto returnErrorData(String resultCode, String errorMessage) {
        JSONPublicDto data = new JSONPublicDto();
        data.setRetCode(resultCode);
        data.setRetMsg(errorMessage);
        return data;
    }

    /**
     * @Author: hejiang
     * @Description: 成功返回
     * @Date: 17:01 2018/3/22
     */
    public static <T> JSONPublicDto<T> returnSuccessData(String resultCode, String message, T responseData) {
        JSONPublicDto<T> data = new JSONPublicDto<T>();
        data.setRetCode(resultCode);
        data.setResponseData(responseData);
        data.setRetMsg(message);
        return data;
    }

    /**
     * @Author: hejiang
     * @Description: 成功返回
     * @Date: 17:01 2018/3/22
     */
    public static <T> JSONPublicDto<T> returnSuccessData(String message) {
        JSONPublicDto<T> data = new JSONPublicDto<T>();
        data.setRetCode(RetCodeConstants.SUCESS);
        data.setResponseData(null);
        data.setRetMsg(message);
        return data;
    }

    /**
     * 默认返回 message=OK,code=0000
     *
     * @param responseData
     *
     * @return JSONPublicDto<T>
     */
    public static <T> JSONPublicDto<T> returnSuccessData(T responseData) {
        JSONPublicDto<T> data = new JSONPublicDto<T>();
        data.setRetCode(RetCodeConstants.SUCESS);
        data.setResponseData(responseData);
        data.setRetMsg("OK");
        return data;
    }
}
