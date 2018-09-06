package com.idream.commons.lib.exception;

import com.idream.commons.lib.base.RetCodeConstants;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -2678203134198782909L;

    String errorCode = RetCodeConstants.UNKOWN_ERROR;

    /**
     *
     */
    public BusinessException(Throwable e) {
        super(e);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
    }

    public BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(String errorCode, String message, Throwable e) {
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
