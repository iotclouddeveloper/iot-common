package com.hvisions.common.dto;

public class ExtendInfoParam
{
    private String extendTableName;
    private String originTableIdName;
    private String originTableName;
    private String schema;
    
    public String getExtendTableName() {
        return this.extendTableName;
    }
    
    public String getOriginTableIdName() {
        return this.originTableIdName;
    }
    
    public String getOriginTableName() {
        return this.originTableName;
    }
    
    public String getSchema() {
        return this.schema;
    }
    
    public void setExtendTableName(final String extendTableName) {
        this.extendTableName = extendTableName;
    }
    
    public void setOriginTableIdName(final String originTableIdName) {
        this.originTableIdName = originTableIdName;
    }
    
    public void setOriginTableName(final String originTableName) {
        this.originTableName = originTableName;
    }
    
    public void setSchema(final String schema) {
        this.schema = schema;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ExtendInfoParam)) {
            return false;
        }
        final ExtendInfoParam other = (ExtendInfoParam)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$extendTableName = this.getExtendTableName();
        final Object other$extendTableName = other.getExtendTableName();
        Label_0065: {
            if (this$extendTableName == null) {
                if (other$extendTableName == null) {
                    break Label_0065;
                }
            }
            else if (this$extendTableName.equals(other$extendTableName)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$originTableIdName = this.getOriginTableIdName();
        final Object other$originTableIdName = other.getOriginTableIdName();
        Label_0102: {
            if (this$originTableIdName == null) {
                if (other$originTableIdName == null) {
                    break Label_0102;
                }
            }
            else if (this$originTableIdName.equals(other$originTableIdName)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$originTableName = this.getOriginTableName();
        final Object other$originTableName = other.getOriginTableName();
        Label_0139: {
            if (this$originTableName == null) {
                if (other$originTableName == null) {
                    break Label_0139;
                }
            }
            else if (this$originTableName.equals(other$originTableName)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$schema = this.getSchema();
        final Object other$schema = other.getSchema();
        if (this$schema == null) {
            if (other$schema == null) {
                return true;
            }
        }
        else if (this$schema.equals(other$schema)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ExtendInfoParam;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $extendTableName = this.getExtendTableName();
        result = result * 59 + (($extendTableName == null) ? 43 : $extendTableName.hashCode());
        final Object $originTableIdName = this.getOriginTableIdName();
        result = result * 59 + (($originTableIdName == null) ? 43 : $originTableIdName.hashCode());
        final Object $originTableName = this.getOriginTableName();
        result = result * 59 + (($originTableName == null) ? 43 : $originTableName.hashCode());
        final Object $schema = this.getSchema();
        result = result * 59 + (($schema == null) ? 43 : $schema.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ExtendInfoParam(extendTableName=" + this.getExtendTableName() + ", originTableIdName=" + this.getOriginTableIdName() + ", originTableName=" + this.getOriginTableName() + ", schema=" + this.getSchema() + ")";
    }
}
