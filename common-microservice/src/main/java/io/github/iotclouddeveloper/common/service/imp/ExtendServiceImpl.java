package io.github.iotclouddeveloper.common.service.imp;

import io.github.iotclouddeveloper.common.service.*;
import io.github.iotclouddeveloper.common.dto.ExtendColumnInfo;
import io.github.iotclouddeveloper.common.dto.ExtendInfo;
import io.github.iotclouddeveloper.common.dto.ExtendInfoParam;
import io.github.iotclouddeveloper.common.enums.BaseErrorEnum;
import io.github.iotclouddeveloper.common.enums.ExtendColumnTypeEnum;
import io.github.iotclouddeveloper.common.enums.ExtendExceptionEnum;
import io.github.iotclouddeveloper.common.exception.BaseKnownException;
import io.github.iotclouddeveloper.common.interfaces.BaseErrorCode;
import io.github.iotclouddeveloper.common.service.BaseExtendService;
import io.github.iotclouddeveloper.common.utils.ExtendColumnSqlMapper;
import org.springframework.jdbc.core.*;
import io.github.iotclouddeveloper.common.dto.*;

import java.util.stream.*;
import org.apache.commons.lang.*;
import io.github.iotclouddeveloper.common.exception.*;
import io.github.iotclouddeveloper.common.interfaces.*;
import io.github.iotclouddeveloper.common.utils.*;
import java.util.regex.*;
import io.github.iotclouddeveloper.common.enums.*;
import java.util.*;

public class ExtendServiceImpl implements BaseExtendService
{
    final JdbcOperations jdbcOperations;
    ExtendInfoParam extendInfoParam;
    static final Integer ZERO;
    
    public ExtendServiceImpl(final JdbcOperations jdbcOperations, final ExtendInfoParam extendInfoParam) {
        this.jdbcOperations = jdbcOperations;
        this.extendInfoParam = extendInfoParam;
    }
    
    @Deprecated
    public ExtendServiceImpl(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }
    
    @Override
    public Map<String, Object> getExtend(final int entityId) {
        final List<Integer> idList = new ArrayList<Integer>();
        idList.add(entityId);
        final List<ExtendInfo> result = this.getExtend(idList);
        if (result.size() > 0) {
            return result.get(0).getValues();
        }
        return null;
    }
    
    @Override
    public List<ExtendInfo> getAll() {
        final List<ExtendColumnInfo> extendColumnInfos = this.getExtendColumnInfo();
        final List<String> columnNames = extendColumnInfos.stream().map(ExtendColumnInfo::getColumnName).collect(Collectors.toList());
        columnNames.add(this.extendInfoParam.getOriginTableIdName());
        final String columnSql = StringUtils.join((Collection)columnNames, ",");
        final String sql = String.format("select %s from %s ", columnSql, this.extendInfoParam.getExtendTableName());
        final List<Map<String, Object>> result = (List<Map<String, Object>>)this.jdbcOperations.queryForList(sql);
        return this.mapResult(result);
    }
    
    @Override
    public List<ExtendInfo> getExtend(final List<Integer> entityIds) {
        if (entityIds.size() <= 0) {
            return new ArrayList<ExtendInfo>();
        }
        final List<ExtendColumnInfo> extendColumnInfos = this.getExtendColumnInfo();
        final List<String> columnNames = extendColumnInfos.stream().map(ExtendColumnInfo::getColumnName).collect(Collectors.toList());
        columnNames.add(this.extendInfoParam.getOriginTableIdName());
        final String columnSql = columnNames.stream().map(t -> String.format("`%s`", t)).collect(Collectors.joining(","));
        final List<String> idStr = entityIds.stream().map(t -> String.format("'%s'", t)).collect(Collectors.toList());
        final String idString = StringUtils.join((Collection)idStr, ',');
        final String sql = String.format("select %s from %s where %s in (%s)", columnSql, this.extendInfoParam.getExtendTableName(), this.extendInfoParam.getOriginTableIdName(), idString);
        final List<Map<String, Object>> result = (List<Map<String, Object>>)this.jdbcOperations.queryForList(sql);
        return this.mapResult(result);
    }
    
    List<ExtendInfo> mapResult(final List<Map<String, Object>> result) {
        final List<ExtendInfo> infos = new ArrayList<ExtendInfo>();
        if (result.size() > 0) {
            for (final Map<String, Object> obj : result) {
                final ExtendInfo info = new ExtendInfo();
                info.setEntityId((Integer)obj.get(this.extendInfoParam.getOriginTableIdName()));
                info.setValues(obj);
                infos.add(info);
            }
        }
        return infos;
    }
    
    @Override
    public void addExtend(final ExtendColumnInfo extendColumnInfo) {
        this.validColumnName(extendColumnInfo.getColumnName());
        if (this.checkColumn(extendColumnInfo.getColumnName())) {
            throw new BaseKnownException((BaseErrorCode) ExtendExceptionEnum.COLUMN_EXISTS, new Object[0]);
        }
        final String addColumnSql = ExtendColumnSqlMapper.generateSql(ExtendColumnTypeEnum.valueOf(extendColumnInfo.getColumnType().toUpperCase()), this.extendInfoParam.getSchema(), this.extendInfoParam.getExtendTableName(), extendColumnInfo.getColumnName(), extendColumnInfo.formDes());
        this.jdbcOperations.execute(addColumnSql);
    }
    
    void validColumnName(final String columnName) {
        final String pattern = "^([a-z]|[A-Z]|_|[0-9])+$";
        if (!Pattern.matches(pattern, columnName)) {
            throw new BaseKnownException((BaseErrorCode) BaseErrorEnum.COLUMN_PATTERN_ILLEGAL, new Object[0]);
        }
    }
    
    @Override
    public void dropExtend(final String name) {
        final String selectColumnSql = String.format("select count(1) from %s where `%s` != null ", this.extendInfoParam.getExtendTableName(), name);
        final Integer count = (Integer)this.jdbcOperations.queryForObject(selectColumnSql, (Class)Integer.class);
        if (ExtendServiceImpl.ZERO < count) {
            throw new BaseKnownException((BaseErrorCode)BaseErrorEnum.COLUMN_EXISTS_VALUE, new Object[0]);
        }
        if (this.checkColumn(name)) {
            final String dropColumnSql = String.format("ALTER TABLE `%s`.`%s` DROP COLUMN `%s`", this.extendInfoParam.getSchema(), this.extendInfoParam.getExtendTableName(), name);
            this.jdbcOperations.execute(dropColumnSql);
            return;
        }
        throw new BaseKnownException((BaseErrorCode)ExtendExceptionEnum.COLUMN_NOT_EXISTS, new Object[0]);
    }
    
    void checkOriginExists(final int id) {
        final String countSql = String.format("select count(1) from %s where id = %s", this.extendInfoParam.getOriginTableName(), id);
        final Integer count = (Integer)this.jdbcOperations.queryForObject(countSql, (Class)Integer.class);
        if (0 == count) {
            throw new BaseKnownException((BaseErrorCode)ExtendExceptionEnum.ENTITY_NOT_EXISTS, new Object[0]);
        }
    }
    
    boolean checkExists(final ExtendInfo extendInfo) {
        final String checkSql = String.format("select count(1) from %s where %s =  %d", this.extendInfoParam.getExtendTableName(), this.extendInfoParam.getOriginTableIdName(), extendInfo.getEntityId());
        final Integer count = (Integer)this.jdbcOperations.queryForObject(checkSql, (Class)Integer.class);
        return 0 < count;
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
        final String columnSql = StringUtils.join((Collection)extendInfo.getValues().keySet().stream().map(t -> String.format("`%s`", t)).collect(Collectors.toList()), ",");

        final String valueSql = StringUtils.join((Collection)extendInfo.getValues().values().stream().map(t -> {
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
    public void deleteExtendInfo(final int entityId) {
        final String sql = String.format("delete from %s where %s  = %s", this.extendInfoParam.getExtendTableName(), this.extendInfoParam.getOriginTableIdName(), entityId);
        this.jdbcOperations.execute(sql);
    }
    
    @Override
    public void updateExtendInfo(final ExtendInfo extendInfo) {
        this.checkOriginExists(extendInfo.getEntityId());
        if (!this.checkExists(extendInfo)) {
            this.addExtendInfo(extendInfo);
        }
        else if (extendInfo.getValues() != null && extendInfo.getValues().size() > 0) {

            final String setSql = StringUtils.join(extendInfo.getValues().entrySet().stream().map(t -> {
                String s;
                if (t.getValue() == null) {
                    s = String.format("`%s`= null", t.getKey());
                }
                else {
                    s = String.format("`%s` = '%s'", t.getKey(), t.getValue());
                }
                return s;
            }).collect(Collectors.toList()), ",");
            final String sql = String.format("update %s set %s where %s = %d", this.extendInfoParam.getExtendTableName(), setSql, this.extendInfoParam.getOriginTableIdName(), extendInfo.getEntityId());
            this.jdbcOperations.execute(sql);
        }
    }
    
    private boolean checkColumn(final String name) {
        if (Arrays.asList("id", "create_time", "update_time", this.extendInfoParam.getOriginTableIdName()).contains(name)) {
            throw new BaseKnownException((BaseErrorCode)ExtendExceptionEnum.ORIGIN_COLUMN_CAN_NOT_MODIFY, new Object[0]);
        }
        final String sql = String.format("select column_name from information_schema.columns where table_schema='%s' and table_name = '%s'", this.extendInfoParam.getSchema(), this.extendInfoParam.getExtendTableName());
        final List<String> result = (List<String>)this.jdbcOperations.queryForList(sql, (Class)String.class);
        return result.contains(name);
    }
    
    @Override
    public List<ExtendColumnInfo> getExtendColumnInfo() {
        final List<ExtendColumnInfo> extendColumnInfos = new ArrayList<ExtendColumnInfo>();
        final String sql = this.getSql();
        final List<Map<String, Object>> result = this.jdbcOperations.queryForList(sql);
        for (final Map<String, Object> map : result) {
            final ExtendColumnInfo info = new ExtendColumnInfo(map.get("column_name").toString(), map.get("data_type").toString(), map.get("column_comment").toString());
            extendColumnInfos.add(info);
        }
        return extendColumnInfos;
    }
    
    protected String getSql() {
        return String.format("select column_name,Data_Type,Column_Comment from information_schema.columns where table_schema='%s' and table_name='%s' and column_name not in ('id','create_time','update_time','%s')", this.extendInfoParam.getSchema(), this.extendInfoParam.getExtendTableName(), this.extendInfoParam.getOriginTableIdName());
    }
    
    static {
        ZERO = 0;
    }
}
