package com.hvisions.common.enums;

public enum ExtendColumnTypeEnum
{
    VARCHAR(10, "\u5b57\u7b26\u4e32"), 
    INT(20, "\u6574\u6570"), 
    FLOAT(30, "\u5c0f\u6570"), 
    DECIMAL(40, "\u7cbe\u786e\u5c0f\u65702\u4f4d"), 
    DATE(50, "\u65e5\u671f"), 
    DATETIME(60, "\u7cbe\u786e\u65f6\u95f4");
    
    private int code;
    private String message;
    
    private ExtendColumnTypeEnum(final int code, final String message) {
        this.code = code;
        this.message = message;
    }
    
    public int getCode() {
        return this.code;
    }
    
    public String getMessage() {
        return this.message;
    }
}
