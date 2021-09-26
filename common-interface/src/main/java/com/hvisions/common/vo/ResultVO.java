package com.hvisions.common.vo;

import com.hvisions.common.interfaces.*;
import com.fasterxml.jackson.annotation.*;
import com.hvisions.common.enums.*;

public class ResultVO<T> implements BaseErrorCode
{
    public static final int SUCCESS = 200;
    private T data;
    private Integer code;
    private String message;
    
    @JsonIgnore
    public boolean isSuccess() {
        return this.code == 200;
    }
    
    public ResultVO() {
        this.code = 0;
        this.message = "";
    }
    
    private ResultVO(final ResultEnum result, final T data) {
        this.message = result.getMessage();
        this.code = result.getCode();
        this.data = data;
    }
    
    public ResultVO(final int code, final String message) {
        this.code = code;
        this.message = message;
    }
    
    private void setMessage(final String message) {
        this.message = message;
    }
    
    public static <T> ResultVO<T> success(final T obj) {
        return new ResultVO<T>(ResultEnum.SUCCESS, obj);
    }
    
    public static ResultVO error(final String message) {
        final ResultVO result = new ResultVO(200, null);
        result.setMessage(message);
        return result;
    }
    
    @Override
    public String toString() {
        return "ResultVO{data=" + this.data + ", code=" + this.code + ", message='" + this.message + '\'' + '}';
    }
    
    public T getData() {
        return this.data;
    }
    
    @Override
    public Integer getCode() {
        return this.code;
    }
    
    @Override
    public String getMessage() {
        return this.message;
    }
}
