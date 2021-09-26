package com.hvisions.common.dto;

import io.swagger.annotations.*;

public final class ExtendColumnInfo
{
    private static final int ORIGIN_COLUMN_COUNT = 3;
    private static final String COLUMN_INFO_SPLIT = ",";
    @ApiModelProperty("\u5217\u540d")
    private String columnName;
    @ApiModelProperty(value = "\u5217\u7c7b\u578b", allowableValues = "VARCHAR,INT,FLOAT,DECIMAL,DATE,DATETIME")
    private String columnType;
    @ApiModelProperty("\u5217\u6240\u7528\u63a7\u4ef6\u540d")
    private String frontControlName;
    @ApiModelProperty("\u82f1\u6587\u663e\u793a\u540d")
    private String enName;
    @ApiModelProperty("\u4e2d\u6587\u663e\u793a\u540d")
    private String chName;
    
    public String formDes() {
        return String.format("%s,%s,%s", this.getFrontControlName(), this.getEnName(), this.getChName());
    }
    
    public ExtendColumnInfo() {
    }
    
    public ExtendColumnInfo(final String columnName, final String columnType, final String frontControlName, final String enName, final String chName) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.frontControlName = frontControlName;
        this.enName = enName;
        this.chName = chName;
    }
    
    public ExtendColumnInfo(final String columnName, final String columnType, final String def) {
        this.columnName = columnName;
        this.columnType = columnType;
        final String[] columnInfo = def.split(",");
        if (columnInfo.length == 3) {
            this.setFrontControlName(columnInfo[0]);
            this.setEnName(columnInfo[1]);
            this.setChName(columnInfo[2]);
        }
        else {
            this.setFrontControlName("");
            this.setEnName("");
            this.setChName("");
        }
    }
    
    public String getColumnName() {
        return this.columnName;
    }
    
    public String getColumnType() {
        return this.columnType;
    }
    
    public String getFrontControlName() {
        return this.frontControlName;
    }
    
    public String getEnName() {
        return this.enName;
    }
    
    public String getChName() {
        return this.chName;
    }
    
    public void setColumnName(final String columnName) {
        this.columnName = columnName;
    }
    
    public void setColumnType(final String columnType) {
        this.columnType = columnType;
    }
    
    public void setFrontControlName(final String frontControlName) {
        this.frontControlName = frontControlName;
    }
    
    public void setEnName(final String enName) {
        this.enName = enName;
    }
    
    public void setChName(final String chName) {
        this.chName = chName;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ExtendColumnInfo)) {
            return false;
        }
        final ExtendColumnInfo other = (ExtendColumnInfo)o;
        final Object this$columnName = this.getColumnName();
        final Object other$columnName = other.getColumnName();
        Label_0055: {
            if (this$columnName == null) {
                if (other$columnName == null) {
                    break Label_0055;
                }
            }
            else if (this$columnName.equals(other$columnName)) {
                break Label_0055;
            }
            return false;
        }
        final Object this$columnType = this.getColumnType();
        final Object other$columnType = other.getColumnType();
        Label_0092: {
            if (this$columnType == null) {
                if (other$columnType == null) {
                    break Label_0092;
                }
            }
            else if (this$columnType.equals(other$columnType)) {
                break Label_0092;
            }
            return false;
        }
        final Object this$frontControlName = this.getFrontControlName();
        final Object other$frontControlName = other.getFrontControlName();
        Label_0129: {
            if (this$frontControlName == null) {
                if (other$frontControlName == null) {
                    break Label_0129;
                }
            }
            else if (this$frontControlName.equals(other$frontControlName)) {
                break Label_0129;
            }
            return false;
        }
        final Object this$enName = this.getEnName();
        final Object other$enName = other.getEnName();
        Label_0166: {
            if (this$enName == null) {
                if (other$enName == null) {
                    break Label_0166;
                }
            }
            else if (this$enName.equals(other$enName)) {
                break Label_0166;
            }
            return false;
        }
        final Object this$chName = this.getChName();
        final Object other$chName = other.getChName();
        if (this$chName == null) {
            if (other$chName == null) {
                return true;
            }
        }
        else if (this$chName.equals(other$chName)) {
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $columnName = this.getColumnName();
        result = result * 59 + (($columnName == null) ? 43 : $columnName.hashCode());
        final Object $columnType = this.getColumnType();
        result = result * 59 + (($columnType == null) ? 43 : $columnType.hashCode());
        final Object $frontControlName = this.getFrontControlName();
        result = result * 59 + (($frontControlName == null) ? 43 : $frontControlName.hashCode());
        final Object $enName = this.getEnName();
        result = result * 59 + (($enName == null) ? 43 : $enName.hashCode());
        final Object $chName = this.getChName();
        result = result * 59 + (($chName == null) ? 43 : $chName.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ExtendColumnInfo(columnName=" + this.getColumnName() + ", columnType=" + this.getColumnType() + ", frontControlName=" + this.getFrontControlName() + ", enName=" + this.getEnName() + ", chName=" + this.getChName() + ")";
    }
}
