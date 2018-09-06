package com.idream.commons.lib.exception;

public class RedisException extends BusinessException {

    private static final long serialVersionUID = 2567583235070129394L;

    String errorCode = "default";

    /**
     *
     */
    public RedisException(Throwable e) {
        super(e);
    }

    public RedisException(String message) {
        super(message);
    }

    public RedisException(String message, Throwable e) {
        super(message, e);
    }

    public RedisException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public RedisException(String errorCode, String message, Throwable e) {
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
