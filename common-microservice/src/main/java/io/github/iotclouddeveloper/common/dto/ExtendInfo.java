package io.github.iotclouddeveloper.common.dto;

import java.util.*;

import io.github.iotclouddeveloper.common.interfaces.IExtendObject;
import io.swagger.annotations.*;
import io.github.iotclouddeveloper.common.interfaces.*;

public class ExtendInfo
{
    private int entityId;
    @ApiModelProperty(name = "values", value = "\u5c5e\u6027\u503ckey-value ,\u4f8b\u5982\"test1\":\"123\"")
    private Map<String, Object> values;
    
    public ExtendInfo() {
    }
    
    public ExtendInfo(final IExtendObject iExtendObject) {
        this.entityId = iExtendObject.getId();
        this.values = (Map<String, Object>)iExtendObject.getExtend();
    }
    
    public int getEntityId() {
        return this.entityId;
    }
    
    public Map<String, Object> getValues() {
        return this.values;
    }
    
    public void setEntityId(final int entityId) {
        this.entityId = entityId;
    }
    
    public void setValues(final Map<String, Object> values) {
        this.values = values;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ExtendInfo)) {
            return false;
        }
        final ExtendInfo other = (ExtendInfo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getEntityId() != other.getEntityId()) {
            return false;
        }
        final Object this$values = this.getValues();
        final Object other$values = other.getValues();
        if (this$values == null) {
            if (other$values == null) {
                return true;
            }
        }
        else if (this$values.equals(other$values)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ExtendInfo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getEntityId();
        final Object $values = this.getValues();
        result = result * 59 + (($values == null) ? 43 : $values.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ExtendInfo(entityId=" + this.getEntityId() + ", values=" + this.getValues() + ")";
    }
}
