package com.idream.commons.lib.exception;

/**
 * 验证异常
 *
 * @author zjzc
 */
public class ValidateException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 5214146953001236471L;

    String errorCode = "default";

    /**
     *
     */
    public ValidateException(Throwable e) {
        super(e);
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Throwable e) {
        super(message, e);
    }

    public ValidateException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ValidateException(String errorCode, String message, Throwable e) {
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
