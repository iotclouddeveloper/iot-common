package io.github.iotclouddeveloper.common.enums;

import io.github.iotclouddeveloper.common.interfaces.*;
import io.github.iotclouddeveloper.common.interfaces.BaseErrorCode;

public enum ExtendExceptionEnum implements BaseErrorCode
{
    COLUMN_EXISTS(Integer.valueOf(11010)), 
    COLUMN_EXISTS_VALUE(Integer.valueOf(11011)), 
    COLUMN_NOT_EXISTS(Integer.valueOf(11020)), 
    ENTITY_NOT_EXISTS(Integer.valueOf(11030)), 
    INFO_EXISTS(Integer.valueOf(11040)), 
    INFO_NOT_EXISTS(Integer.valueOf(11050)), 
    ORIGIN_COLUMN_CAN_NOT_MODIFY(Integer.valueOf(11060)), 
    ENTITY_EXTEND_NOT_EXISTS(Integer.valueOf(11070));
    
    private Integer code;
    
    private ExtendExceptionEnum(final Integer code) {
        this.code = code;
    }
    
    @Override
    public String getMessage() {
        return this.toString();
    }
    
    @Override
    public Integer getCode() {
        return this.code;
    }
}
