package io.github.iotclouddeveloper.common.service.imp;

import io.github.iotclouddeveloper.common.dto.ExtendColumnInfo;
import io.github.iotclouddeveloper.common.dto.ExtendInfo;
import io.github.iotclouddeveloper.common.dto.ExtendInfoParam;
import io.github.iotclouddeveloper.common.enums.BaseErrorEnum;
import io.github.iotclouddeveloper.common.enums.ExtendColumnTypeEnum;
import io.github.iotclouddeveloper.common.enums.ExtendExceptionEnum;
import io.github.iotclouddeveloper.common.exception.BaseKnownException;
import io.github.iotclouddeveloper.common.interfaces.BaseErrorCode;
import org.springframework.jdbc.core.*;
import io.github.iotclouddeveloper.common.dto.*;

import java.util.stream.*;
import org.apache.commons.lang.*;
import java.util.*;
import io.github.iotclouddeveloper.common.exception.*;
import io.github.iotclouddeveloper.common.interfaces.*;
import io.github.iotclouddeveloper.common.enums.*;

public class SqlServerExtendServiceImpl extends ExtendServiceImpl
{
    public SqlServerExtendServiceImpl(final JdbcOperations jdbcOperations, final ExtendInfoParam extendInfoParam) {
        super(jdbcOperations, extendInfoParam);
    }
    
    @Override
    public List<ExtendInfo> getExtend(final List<Integer> entityIds) {
        if (entityIds.size() <= 0) {
            return new ArrayList<ExtendInfo>();
        }
        final List<ExtendColumnInfo> extendColumnInfos = this.getExtendColumnInfo();
        final List<String> columnNames = extendColumnInfos.stream().map(ExtendColumnInfo::getColumnName).collect(Collectors.toList());
        columnNames.add(this.extendInfoParam.getOriginTableIdName());
        final String columnSql = columnNames.stream().map(t -> String.format("[%s]", t)).collect(Collectors.joining(","));
        final List<String> idStr = entityIds.stream().map(t -> String.format("'%s'", t)).collect(Collectors.toList());
        final String idString = StringUtils.join((Collection)idStr, ',');
        final String sql = String.format("select %s from %s where %s in (%s)", columnSql, this.extendInfoParam.getExtendTableName(), this.extendInfoParam.getOriginTableIdName(), idString);
        final List<Map<String, Object>> result = (List<Map<String, Object>>)this.jdbcOperations.queryForList(sql);
        return this.mapResult(result);
    }
    
    @Override
    public void updateExtendInfo(final ExtendInfo extendInfo) {
        this.checkOriginExists(extendInfo.getEntityId());
        if (!this.checkExists(extendInfo)) {
            this.addExtendInfo(extendInfo);
        }
        else if (extendInfo.getValues() != null && extendInfo.getValues().size() > 0) {

            final String setSql = StringUtils.join((Collection)extendInfo.getValues().entrySet().stream().map(t -> {
                String s;
                if (t.getValue() == null) {
                    s = String.format("%s= null", t.getKey());
                }
                else {
                    s = String.format("%s = '%s'", t.getKey(), t.getValue());
                }
                return s;
            }).collect(Collectors.toList()), ",");
            final String sql = String.format("update %s set %s where %s = %d", this.extendInfoParam.getExtendTableName(), setSql, this.extendInfoParam.getOriginTableIdName(), extendInfo.getEntityId());
            this.jdbcOperations.execute(sql);
        }
    }
    
    @Override
    public void dropExtend(final String name) {
        if (Arrays.asList("id", "create_time", "update_time", this.extendInfoParam.getOriginTableIdName()).contains(name)) {
            throw new BaseKnownException((BaseErrorCode) ExtendExceptionEnum.ORIGIN_COLUMN_CAN_NOT_MODIFY, new Object[0]);
        }
        final String sql = String.format("select name from syscolumns where id=object_id('%s')", this.extendInfoParam.getExtendTableName());
        final List<String> result = (List<String>)this.jdbcOperations.queryForList(sql, (Class)String.class);
        if (!result.contains(name)) {
            throw new BaseKnownException((BaseErrorCode)ExtendExceptionEnum.COLUMN_NOT_EXISTS, new Object[0]);
        }
        final String selectColumnSql = String.format("select count(1) from %s where %s != null ", this.extendInfoParam.getExtendTableName(), name);
        final Integer count = (Integer)this.jdbcOperations.queryForObject(selectColumnSql, (Class)Integer.class);
        if (ZERO < count) {
            throw new BaseKnownException((BaseErrorCode) BaseErrorEnum.COLUMN_EXISTS_VALUE, new Object[0]);
        }
        final String dropColumnSql = String.format("ALTER TABLE %s DROP COLUMN %s", this.extendInfoParam.getExtendTableName(), name);
        this.jdbcOperations.execute(dropColumnSql);
    }
    
    @Override
    public void addExtend(final ExtendColumnInfo extendColumnInfo) {
        this.validColumnName(extendColumnInfo.getColumnName());
        if (Arrays.asList("id", "create_time", "update_time", this.extendInfoParam.getOriginTableIdName()).contains(extendColumnInfo.getColumnName())) {
            throw new BaseKnownException((BaseErrorCode)ExtendExceptionEnum.ORIGIN_COLUMN_CAN_NOT_MODIFY, new Object[0]);
        }
        final String sql = String.format("select column_name from information_schema.columns where table_name = '%s'", this.extendInfoParam.getExtendTableName());
        final List<String> result = (List<String>)this.jdbcOperations.queryForList(sql, (Class)String.class);
        if (result.stream().anyMatch(t -> t.equals(extendColumnInfo.getColumnName()))) {
            throw new BaseKnownException((BaseErrorCode)ExtendExceptionEnum.COLUMN_EXISTS, new Object[0]);
        }
        String addColumnSql = null;
        switch (ExtendColumnTypeEnum.valueOf(extendColumnInfo.getColumnType().toUpperCase())) {
            case VARCHAR: {
                addColumnSql = extendColumnInfo.getColumnName() + " VARCHAR(200) NULL ";
                break;
            }
            case INT: {
                addColumnSql = extendColumnInfo.getColumnName() + " INT NULL ";
                break;
            }
            case FLOAT: {
                addColumnSql = extendColumnInfo.getColumnName() + " FLOAT NULL ";
                break;
            }
            case DECIMAL: {
                addColumnSql = extendColumnInfo.getColumnName() + " DECIMAL(12,4) NULL ";
                break;
            }
            case DATE: {
                addColumnSql = extendColumnInfo.getColumnName() + " DATE NULL";
                break;
            }
            case DATETIME: {
                addColumnSql = extendColumnInfo.getColumnName() + " DATETIME NULL";
                break;
            }
            default: {
                addColumnSql = "";
                break;
            }
        }
        if (!"".equals(addColumnSql)) {
            addColumnSql = "ALTER TABLE " + this.extendInfoParam.getExtendTableName() + " ADD " + addColumnSql;
        }
        this.jdbcOperations.execute(addColumnSql);
        final String commentSql = "EXEC sp_addextendedproperty N'MS_Description', N'" + extendColumnInfo.formDes() + "', N'SCHEMA', N'dbo',N'TABLE', N'" + this.extendInfoParam.getExtendTableName() + "', N'COLUMN', N'" + extendColumnInfo.getColumnName() + "'";
        this.jdbcOperations.execute(commentSql);
    }
    
    @Override
    public void addExtendInfo(final ExtendInfo extendInfo) {
        this.checkOriginExists(extendInfo.getEntityId());
        if (this.checkExists(extendInfo)) {
            return;
        }
        if (extendInfo.getValues() == null) {
            return;
        }
        extendInfo.getValues().put(this.extendInfoParam.getOriginTableIdName(), extendInfo.getEntityId());
        extendInfo.getValues().put(this.extendInfoParam.getOriginTableIdName(), String.valueOf(extendInfo.getEntityId()));
        final String columnSql = StringUtils.join(extendInfo.getValues().keySet().stream().map(t -> String.format("[%s]", t)).collect(Collectors.toList()), ",");

        final String valueSql = StringUtils.join(extendInfo.getValues().values().stream().map(t -> {
            String format;
            if (t == null) {
                format = "null";
            }
            else {
                format = String.format("'%s'", t);
            }
            return format;
        }).collect(Collectors.toList()), ",");
        final String sql = String.format("insert into %s (%s) values (%s)", this.extendInfoParam.getExtendTableName(), columnSql, valueSql);
        this.jdbcOperations.execute(sql);
    }
    
    @Override
    protected String getSql() {
        return String.format("SELECT\n\tcolumn_name = convert(varchar(50),a.name) ,\n\tdata_type = convert(varchar(50),b.name),\n\tcolumn_comment = convert(varchar(50),isnull( g.[value], '' ))  \nFROM\n\tsyscolumns a\n\tLEFT JOIN systypes b ON a.xusertype= b.xusertype\n\tINNER JOIN sysobjects d ON a.id= d.id \n\tAND d.xtype= 'U' \n\tAND d.name<> 'dtproperties'\n\tLEFT JOIN sys.extended_properties g ON a.id= g.major_id \n\tAND a.colid= g.minor_id \nWHERE\n\td.name= '%s' \n\tAND a.name NOT IN (\n\t'id',\n\t'create_time',\n\t'update_time', \n\t'%s' \n\t)", this.extendInfoParam.getExtendTableName(), this.extendInfoParam.getOriginTableIdName());
    }
}
