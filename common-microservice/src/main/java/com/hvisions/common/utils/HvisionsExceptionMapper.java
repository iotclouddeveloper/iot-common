package com.hvisions.common.utils;

import com.hvisions.common.interfaces.*;
import com.hvisions.common.enums.*;
import java.sql.*;
import org.springframework.dao.*;
import com.fasterxml.jackson.core.*;
import org.springframework.http.converter.*;
import javax.persistence.*;
import java.util.*;
import javax.validation.*;
import java.util.function.*;
import java.util.stream.*;
import com.microsoft.sqlserver.jdbc.*;

public class HvisionsExceptionMapper
{
    private final HashMap<Class, BaseErrorCode> map;
    
    public HvisionsExceptionMapper() {
        (this.map = new HashMap<Class, BaseErrorCode>()).putIfAbsent(ConstraintViolationException.class, (BaseErrorCode)BaseErrorEnum.CONSTRAINT_VIOLATION);
        this.map.putIfAbsent(SQLIntegrityConstraintViolationException.class, (BaseErrorCode)BaseErrorEnum.DATA_INTEGRITY_VIOLATION);
        this.map.putIfAbsent(EmptyResultDataAccessException.class, (BaseErrorCode)BaseErrorEnum.NULL_RESULT);
        this.map.putIfAbsent(DataIntegrityViolationException.class, (BaseErrorCode)BaseErrorEnum.VIOLATE_INTEGRITY);
        this.map.putIfAbsent(JsonProcessingException.class, (BaseErrorCode)BaseErrorEnum.JSON_PARSE_ERROR);
        this.map.putIfAbsent(HttpMessageNotReadableException.class, (BaseErrorCode)BaseErrorEnum.ILLEGAL_STRING);
        this.map.putIfAbsent(EntityNotFoundException.class, (BaseErrorCode)BaseErrorEnum.NULL_RESULT);
        this.map.putIfAbsent(NoSuchElementException.class, (BaseErrorCode)BaseErrorEnum.NO_SUCH_ELEMENT);
    }
    
    public void addException(final Class clas, final BaseErrorCode errorCode) {
        this.map.putIfAbsent(clas, errorCode);
    }
    
    public String getMessage(final Throwable ex) {
        BaseErrorCode obj = this.map.get(ex.getClass());
        if (obj == null) {
            obj = (BaseErrorCode)BaseErrorEnum.SERVER_ERROR;
        }
        return obj.getMessage();
    }
    
    public int getMessageCode(final Throwable ex) {
        BaseErrorCode obj = this.map.get(ex.getClass());
        if (obj == null) {
            obj = (BaseErrorCode)BaseErrorEnum.SERVER_ERROR;
        }
        return obj.getCode();
    }
    
    public String getExtendMessage(final Throwable ex) {
        String result = ex.getMessage();
        if (ex instanceof ConstraintViolationException) {
            result = (String)((ConstraintViolationException)ex).getConstraintViolations().stream().map(ConstraintViolation::getMessageTemplate).collect(Collectors.joining(","));
        }
        else if (ex instanceof SQLIntegrityConstraintViolationException) {
            result = ((SQLIntegrityConstraintViolationException)ex).getMessage();
            final String[] arr = result.split("'");
            if (arr.length > 1) {
                result = arr[arr.length - 1];
            }
        }
        else if (ex instanceof SQLServerException) {
            result = ((SQLServerException)ex).getMessage();
            if (((SQLServerException)ex).getErrorCode() == 2627) {
                result = ((SQLServerException)ex).getMessage();
                final String[] arr = result.split("'");
                if (arr.length > 1) {
                    result = arr[1];
                }
            }
        }
        return result;
    }
}
