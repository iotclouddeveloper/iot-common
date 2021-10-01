package io.github.iotclouddeveloper.common.service.imp;

import io.github.iotclouddeveloper.common.dto.*;
import io.github.iotclouddeveloper.common.dto.ExtendInfoParam;
import org.springframework.jdbc.core.*;

public class OracleExtendServiceImpl extends ExtendServiceImpl
{
    private ExtendInfoParam extendInfoParam;
    
    public OracleExtendServiceImpl(final JdbcOperations jdbcOperations, final ExtendInfoParam extendInfoParam) {
        super(jdbcOperations, extendInfoParam);
    }
    
    @Override
    protected String getSql() {
        return String.format("select column_name,Data_Type,Column_Comment  from user_tab_columns  where  table_name='%s' and column_name not in ('id','create_time','update_time','%s')", this.extendInfoParam.getExtendTableName(), this.extendInfoParam.getOriginTableIdName());
    }
}
