package io.github.iotclouddeveloper.common.interfaces;

import io.swagger.annotations.*;
import java.util.*;

public class ImportResult
{
    @ApiModelProperty(" \u5bfc\u5165\u6570\u76ee")
    private int totalCount;
    @ApiModelProperty(" \u5bfc\u5165\u6210\u529f\u7684\u884c")
    private List<Integer> successLines;
    @ApiModelProperty(" \u5bfc\u5165\u5931\u8d25\u7684\u884c")
    private Map<Integer, String> errorLines;
    
    public ImportResult() {
        this.successLines = new ArrayList<Integer>();
        this.errorLines = new HashMap<Integer, String>();
    }
    
    public int getTotalCount() {
        return this.totalCount;
    }
    
    public List<Integer> getSuccessLines() {
        return this.successLines;
    }
    
    public Map<Integer, String> getErrorLines() {
        return this.errorLines;
    }
    
    public void setTotalCount(final int totalCount) {
        this.totalCount = totalCount;
    }
    
    public void setSuccessLines(final List<Integer> successLines) {
        this.successLines = successLines;
    }
    
    public void setErrorLines(final Map<Integer, String> errorLines) {
        this.errorLines = errorLines;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ImportResult)) {
            return false;
        }
        final ImportResult other = (ImportResult)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getTotalCount() != other.getTotalCount()) {
            return false;
        }
        final Object this$successLines = this.getSuccessLines();
        final Object other$successLines = other.getSuccessLines();
        Label_0078: {
            if (this$successLines == null) {
                if (other$successLines == null) {
                    break Label_0078;
                }
            }
            else if (this$successLines.equals(other$successLines)) {
                break Label_0078;
            }
            return false;
        }
        final Object this$errorLines = this.getErrorLines();
        final Object other$errorLines = other.getErrorLines();
        if (this$errorLines == null) {
            if (other$errorLines == null) {
                return true;
            }
        }
        else if (this$errorLines.equals(other$errorLines)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ImportResult;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getTotalCount();
        final Object $successLines = this.getSuccessLines();
        result = result * 59 + (($successLines == null) ? 43 : $successLines.hashCode());
        final Object $errorLines = this.getErrorLines();
        result = result * 59 + (($errorLines == null) ? 43 : $errorLines.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ImportResult(totalCount=" + this.getTotalCount() + ", successLines=" + this.getSuccessLines() + ", errorLines=" + this.getErrorLines() + ")";
    }
}
