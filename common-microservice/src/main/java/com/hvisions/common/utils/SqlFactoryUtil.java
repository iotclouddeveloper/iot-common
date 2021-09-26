package com.hvisions.common.utils;

import org.springframework.stereotype.*;
import org.springframework.jdbc.core.*;
import org.springframework.beans.factory.annotation.*;
import com.hvisions.common.dto.*;
import com.hvisions.common.service.*;
import com.hvisions.common.service.imp.*;

@Component
public class SqlFactoryUtil
{
    private static final String SQL_MYSQL = "mysql";
    private static final String SQL_ORACLE = "oracle";
    private static final String SQL_SERVER = "sqlserver";
    @Value("${spring.datasource.url}")
    private String sqlConnection;
    @Autowired
    private JdbcOperations jdbcOperations;
    
    public BaseExtendService getSqlBridge(final ExtendInfoParam extendInfoParam) {
        BaseExtendService serviceImp = null;
        if (this.sqlConnection == null) {
            return null;
        }
        if (this.sqlConnection.contains("mysql")) {
            serviceImp = new ExtendServiceImpl(this.jdbcOperations, extendInfoParam);
        }
        else if (this.sqlConnection.contains("oracle")) {
            serviceImp = new OracleExtendServiceImpl(this.jdbcOperations, extendInfoParam);
        }
        else if (this.sqlConnection.contains("sqlserver")) {
            serviceImp = new SqlServerExtendServiceImpl(this.jdbcOperations, extendInfoParam);
        }
        return serviceImp;
    }
}
