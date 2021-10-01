package io.github.iotclouddeveloper.common.enums;

import io.github.iotclouddeveloper.common.interfaces.*;
import io.github.iotclouddeveloper.common.interfaces.IKeyValueObject;

public enum LogTypeEnum implements IKeyValueObject
{
    SUCCESS(Integer.valueOf(1), "\u6210\u529f"), 
    ERROR(Integer.valueOf(2), "\u5f02\u5e38"), 
    WARNING(Integer.valueOf(3), "\u8b66\u544a"), 
    DEBUGGER(Integer.valueOf(4), "\u6d4b\u8bd5"), 
    TRACE(Integer.valueOf(5), "\u8ffd\u8e2a");
    
    private Integer code;
    private String name;
    
    private LogTypeEnum(final Integer code, final String name) {
        this.code = code;
        this.name = name;
    }
    
    public static LogTypeEnum valueOf(final Integer code) {
        switch (code) {
            case 1: {
                return LogTypeEnum.SUCCESS;
            }
            case 2: {
                return LogTypeEnum.ERROR;
            }
            case 3: {
                return LogTypeEnum.WARNING;
            }
            case 4: {
                return LogTypeEnum.DEBUGGER;
            }
            case 5: {
                return LogTypeEnum.TRACE;
            }
            default: {
                throw new IllegalArgumentException("Invalid type value");
            }
        }
    }
    
    @Override
    public Integer getCode() {
        return this.code;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
}
