package com.hvisions.common.interfaces;

import io.swagger.annotations.*;

public interface IObjectType
{
    @ApiModelProperty("对象类型编码,此字段用于分辨对象类型，调用不必传递")
    Integer getObjectType();
}
