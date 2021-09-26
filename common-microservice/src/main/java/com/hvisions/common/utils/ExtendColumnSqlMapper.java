package com.hvisions.common.utils;

import com.hvisions.common.enums.*;

public class ExtendColumnSqlMapper
{
    public static String generateSql(final ExtendColumnTypeEnum extendColumnTypeEnum, final String schema, final String tableName, final String columnName, final String comment) {
        String result = null;
        switch (extendColumnTypeEnum) {
            case VARCHAR: {
                result = "`" + columnName + "` VARCHAR(200) NULL ";
                break;
            }
            case INT: {
                result = "`" + columnName + "` INT NULL ";
                break;
            }
            case FLOAT: {
                result = "`" + columnName + "` FLOAT NULL ";
                break;
            }
            case DECIMAL: {
                result = "`" + columnName + "` DECIMAL(12,4) NULL ";
                break;
            }
            case DATE: {
                result = " `" + columnName + "` DATE NULL";
                break;
            }
            case DATETIME: {
                result = "`" + columnName + "` DATETIME NULL";
                break;
            }
            default: {
                result = "";
                break;
            }
        }
        if (!"".equals(result)) {
            result = "ALTER TABLE `" + schema + "`.`" + tableName + "` ADD COLUMN " + result + " COMMENT '" + comment + "'";
        }
        return result;
    }
}
