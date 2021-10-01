package io.github.iotclouddeveloper.common.dto;

import java.util.*;

public class ExcelExportDto
{
    private String fileName;
    private byte[] body;
    
    public String getFileName() {
        return this.fileName;
    }
    
    public byte[] getBody() {
        return this.body;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    
    public void setBody(final byte[] body) {
        this.body = body;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ExcelExportDto)) {
            return false;
        }
        final ExcelExportDto other = (ExcelExportDto)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$fileName = this.getFileName();
        final Object other$fileName = other.getFileName();
        if (this$fileName == null) {
            if (other$fileName == null) {
                return Arrays.equals(this.getBody(), other.getBody());
            }
        }
        else if (this$fileName.equals(other$fileName)) {
            return Arrays.equals(this.getBody(), other.getBody());
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ExcelExportDto;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $fileName = this.getFileName();
        result = result * 59 + (($fileName == null) ? 43 : $fileName.hashCode());
        result = result * 59 + Arrays.hashCode(this.getBody());
        return result;
    }
    
    @Override
    public String toString() {
        return "ExcelExportDto(fileName=" + this.getFileName() + ", body=" + Arrays.toString(this.getBody()) + ")";
    }
}
