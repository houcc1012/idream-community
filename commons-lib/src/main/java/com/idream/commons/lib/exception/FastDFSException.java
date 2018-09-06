package com.idream.commons.lib.exception;

/**
 * Created by DELL2018-007 on 2018/3/20.
 */
public class FastDFSException extends BusinessException {

    String errorCode = "default";

    /**
     *
     */

    public FastDFSException(Throwable e) {
        super(e);
    }

    public FastDFSException(String message) {
        super(message);
    }

    public FastDFSException(String message, Throwable e) {
        super(message, e);
    }

    public FastDFSException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public FastDFSException(String errorCode, String message, Throwable e) {
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
