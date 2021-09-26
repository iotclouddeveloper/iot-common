package com.hvisions.common.exception;

import com.hvisions.common.interfaces.*;

public class BaseKnownException extends RuntimeException implements BaseErrorCode
{
    private Integer code;
    private String message;
    private Object[] args;
    
    public BaseKnownException(final BaseErrorCode errorCode, final Object... args) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        if (args != null && args.length > 0) {
            this.args = args;
        }
    }
    
    public BaseKnownException(final Integer errorCode, final String errorMessage, final Object... args) {
        this.code = errorCode;
        this.message = errorMessage;
        if (args != null && args.length > 0) {
            this.args = args;
        }
    }
    
    public BaseKnownException() {
        this.code = -1;
        this.message = "报错信息未设置";
    }
    
    @Override
    public Integer getCode() {
        return this.code;
    }
    
    @Override
    public String getMessage() {
        return this.message;
    }
    
    public Object[] getArgs() {
        return this.args;
    }
}
