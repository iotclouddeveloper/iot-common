package com.hvisions.common.utils;

import java.lang.reflect.*;
import java.util.*;
import java.sql.*;
import java.math.*;
import com.hvisions.common.exception.*;
import com.hvisions.common.interfaces.*;
import java.text.*;
import java.util.Date;

import com.hvisions.common.dto.*;
import com.hvisions.common.enums.*;
import org.slf4j.*;

public class StringObjectMapper
{
    private static final Logger log;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT;
    private static final SimpleDateFormat SIMPLE_DATE_TIME_FORMAT;
    
    static Object convertStringToObject(final Field field, final String cellString) {
        final Class cls = field.getType();
        if (cls.equals(String.class)) {
            return cellString;
        }
        if (cls.equals(Date.class) || cls.equals(java.sql.Date.class)) {
            return parseDateTime(cellString);
        }
        if (cls.equals(Time.class)) {
            try {
                return Time.valueOf(cellString);
            }
            catch (IllegalArgumentException ex) {
                return Time.valueOf("00:00");
            }
        }
        Label_0097: {
            if (!cls.equals(Float.TYPE)) {
                if (!cls.equals(Float.class)) {
                    break Label_0097;
                }
            }
            try {
                return Float.parseFloat(cellString);
            }
            catch (NumberFormatException ex2) {
                return Float.parseFloat("0");
            }
        }
        Label_0130: {
            if (!cls.equals(Integer.class)) {
                if (!cls.equals(Integer.TYPE)) {
                    break Label_0130;
                }
            }
            try {
                return Integer.parseInt(cellString);
            }
            catch (NumberFormatException ex2) {
                return 0;
            }
        }
        if (cls.equals(BigDecimal.class)) {
            try {
                return new BigDecimal(cellString);
            }
            catch (NumberFormatException ex2) {
                return new BigDecimal("0");
            }
        }
        Label_0198: {
            if (!cls.equals(Double.class)) {
                if (!cls.equals(Double.TYPE)) {
                    break Label_0198;
                }
            }
            try {
                return new Double(cellString);
            }
            catch (NumberFormatException ex2) {
                return new Double("0");
            }
        }
        if (cls.equals(Boolean.TYPE) || cls.equals(Boolean.class)) {
            return Boolean.valueOf(cellString);
        }
        throw new BaseKnownException((BaseErrorCode)BaseErrorEnum.ENTITY_PROPERTY_NOT_SUPPORT, new Object[0]);
    }
    
    private static Object parseDateTime(final String cellString) {
        try {
            return StringObjectMapper.SIMPLE_DATE_TIME_FORMAT.parse(cellString);
        }
        catch (ParseException ex) {
            try {
                return StringObjectMapper.SIMPLE_DATE_FORMAT.parse(cellString);
            }
            catch (ParseException e) {
                return null;
            }
        }
    }
    
    static Object getExtendObject(final String cellString, final ExtendColumnInfo cellPropertyExtendInfo) {
        final String typeName = cellPropertyExtendInfo.getColumnType().toUpperCase();
        if (typeName.equals(ExtendColumnTypeEnum.VARCHAR.toString())) {
            return cellString;
        }
        if (typeName.equals(ExtendColumnTypeEnum.DATE.toString())) {
            return parseDateTime(cellString);
        }
        if (typeName.equals(ExtendColumnTypeEnum.DATETIME.toString())) {
            try {
                return Time.valueOf(cellString);
            }
            catch (IllegalArgumentException ex) {
                return Time.valueOf("00:00");
            }
        }
        if (typeName.equals(ExtendColumnTypeEnum.FLOAT.toString())) {
            try {
                return Float.parseFloat(cellString);
            }
            catch (NumberFormatException ex2) {
                return Float.parseFloat("0");
            }
        }
        if (typeName.equals(ExtendColumnTypeEnum.INT.toString())) {
            try {
                return Integer.parseInt(cellString);
            }
            catch (NumberFormatException ex2) {
                return 0;
            }
        }
        if (typeName.equals(ExtendColumnTypeEnum.DECIMAL.toString())) {
            try {
                return new BigDecimal(cellString);
            }
            catch (NumberFormatException ex2) {
                return new BigDecimal("0");
            }
        }
        throw new BaseKnownException((BaseErrorCode)BaseErrorEnum.ENTITY_PROPERTY_NOT_SUPPORT, new Object[0]);
    }
    
    static {
        log = LoggerFactory.getLogger((Class)StringObjectMapper.class);
        SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        SIMPLE_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
