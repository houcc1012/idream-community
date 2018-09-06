package com.idream.commons.lib.exception;

/**
 * 请求参数校验自定义异常
 *
 * @author hejiang
 */
public class RequestParamValidException extends RuntimeException {


    String errorCode = "default";

    public RequestParamValidException(Throwable e) {
        super(e);
    }

    public RequestParamValidException(String message) {
        super(message);
    }

    public RequestParamValidException(String message, Throwable e) {
        super(message, e);
    }

    public RequestParamValidException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public RequestParamValidException(String errorCode, String message, Throwable e) {
        super(message, e);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
