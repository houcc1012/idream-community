package com.idream.commons.lib.exception;


public class DataBaseException extends BusinessException {
    private static final long serialVersionUID = -7980532772047897013L;

    String errorCode = "default";

    /**
     *
     */

    public DataBaseException(Throwable e) {
        super(e);
    }

    public DataBaseException(String message) {
        super(message);
    }

    public DataBaseException(String message, Throwable e) {
        super(message, e);
    }

    public DataBaseException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public DataBaseException(String errorCode, String message, Throwable e) {
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
