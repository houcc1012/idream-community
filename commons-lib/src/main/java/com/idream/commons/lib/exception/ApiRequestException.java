package com.idream.commons.lib.exception;


public class ApiRequestException extends BusinessException {
    private static final long serialVersionUID = -7980532772047897013L;

    String errorCode = "default";

    /**
     *
     */

    public ApiRequestException(Throwable e) {
        super(e);
    }

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable e) {
        super(message, e);
    }

    public ApiRequestException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ApiRequestException(String errorCode, String message, Throwable e) {
        super(message, e);
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }


}
