package com.hvisions.common.enums;

public enum ResultEnum
{
    SUCCESS(200, "\u6210\u529f"), 
    ERROR(1, "\u5931\u8d25");
    
    private int code;
    private String message;
    
    private ResultEnum(final int code, String message) {
        this.code = code;
        message = message;
    }
    
    public int getCode() {
        return this.code;
    }
    
    public String getMessage() {
        return this.message;
    }
}
