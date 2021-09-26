package com.hvisions.common.enums;

import com.hvisions.common.interfaces.*;

public enum BaseErrorEnum implements BaseErrorCode
{
    SERVER_ERROR(10000), 
    JSON_PARSE_ERROR(10010), 
    ILLEGAL_STRING(10020), 
    NULL_RESULT(10040), 
    VIOLATE_INTEGRITY(10050), 
    IMPORT_FILE_NO_SUPPORT(10090), 
    IMPORT_SHEET_IS_NULL(10100), 
    ENTITY_PROPERTY_NOT_SUPPORT(10130), 
    SAVE_SHOULD_NO_IDENTITY(10140), 
    UPDATE_SHOULD_HAVE_IDENTITY(10150), 
    NO_SUCH_ELEMENT(10170), 
    DATA_INTEGRITY_VIOLATION(10180), 
    CONSTRAINT_VIOLATION(10190), 
    COLUMN_PATTERN_ILLEGAL(10200), 
    OBJECT_NOT_SUPPORT(10300), 
    COLUMN_EXISTS_VALUE(10400), 
    ENUM_NOT_EXISTS(10500);
    
    private Integer code;
    
    private BaseErrorEnum(final int code) {
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
