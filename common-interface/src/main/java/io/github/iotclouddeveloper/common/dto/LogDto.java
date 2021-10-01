package io.github.iotclouddeveloper.common.dto;

import io.swagger.annotations.*;
import java.util.*;

public class LogDto extends PageInfo
{
    @ApiModelProperty("\u4e3b\u952eID")
    private Integer id;
    @ApiModelProperty("\u7c7b\u578b")
    private Integer logType;
    @ApiModelProperty("\u8c03\u7528\u65f6\u4f20\u5165\u53c2\u6570")
    private String logParameter;
    @ApiModelProperty("\u6a21\u5757\uff08\u8c03\u7528\u7684\u5fae\u670d\u52a1\u7684\u540d\u79f0\uff09")
    private String logModular;
    @ApiModelProperty("\u5f02\u5e38\u4fe1\u606f")
    private String logExceptionMessage;
    @ApiModelProperty("\u8c03\u7528\u4eba")
    private String logInvocation;
    @ApiModelProperty("\u8c03\u7528\u7684API\u540d\u79f0\u548c\u8def\u5f84")
    private String location;
    @ApiModelProperty("\u8c03\u7528\u65f6\u95f4(\u9ed8\u8ba4\u7cfb\u7edf\u5f53\u524d\u65f6\u95f4)")
    private Date logCaptureTime;
    
    public LogDto() {
        this.logCaptureTime = new Date();
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public Integer getLogType() {
        return this.logType;
    }
    
    public String getLogParameter() {
        return this.logParameter;
    }
    
    public String getLogModular() {
        return this.logModular;
    }
    
    public String getLogExceptionMessage() {
        return this.logExceptionMessage;
    }
    
    public String getLogInvocation() {
        return this.logInvocation;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public Date getLogCaptureTime() {
        return this.logCaptureTime;
    }
    
    public void setId(final Integer id) {
        this.id = id;
    }
    
    public void setLogType(final Integer logType) {
        this.logType = logType;
    }
    
    public void setLogParameter(final String logParameter) {
        this.logParameter = logParameter;
    }
    
    public void setLogModular(final String logModular) {
        this.logModular = logModular;
    }
    
    public void setLogExceptionMessage(final String logExceptionMessage) {
        this.logExceptionMessage = logExceptionMessage;
    }
    
    public void setLogInvocation(final String logInvocation) {
        this.logInvocation = logInvocation;
    }
    
    public void setLocation(final String location) {
        this.location = location;
    }
    
    public void setLogCaptureTime(final Date logCaptureTime) {
        this.logCaptureTime = logCaptureTime;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LogDto)) {
            return false;
        }
        final LogDto other = (LogDto)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        Label_0065: {
            if (this$id == null) {
                if (other$id == null) {
                    break Label_0065;
                }
            }
            else if (this$id.equals(other$id)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$logType = this.getLogType();
        final Object other$logType = other.getLogType();
        Label_0102: {
            if (this$logType == null) {
                if (other$logType == null) {
                    break Label_0102;
                }
            }
            else if (this$logType.equals(other$logType)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$logParameter = this.getLogParameter();
        final Object other$logParameter = other.getLogParameter();
        Label_0139: {
            if (this$logParameter == null) {
                if (other$logParameter == null) {
                    break Label_0139;
                }
            }
            else if (this$logParameter.equals(other$logParameter)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$logModular = this.getLogModular();
        final Object other$logModular = other.getLogModular();
        Label_0176: {
            if (this$logModular == null) {
                if (other$logModular == null) {
                    break Label_0176;
                }
            }
            else if (this$logModular.equals(other$logModular)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$logExceptionMessage = this.getLogExceptionMessage();
        final Object other$logExceptionMessage = other.getLogExceptionMessage();
        Label_0213: {
            if (this$logExceptionMessage == null) {
                if (other$logExceptionMessage == null) {
                    break Label_0213;
                }
            }
            else if (this$logExceptionMessage.equals(other$logExceptionMessage)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$logInvocation = this.getLogInvocation();
        final Object other$logInvocation = other.getLogInvocation();
        Label_0250: {
            if (this$logInvocation == null) {
                if (other$logInvocation == null) {
                    break Label_0250;
                }
            }
            else if (this$logInvocation.equals(other$logInvocation)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$location = this.getLocation();
        final Object other$location = other.getLocation();
        Label_0287: {
            if (this$location == null) {
                if (other$location == null) {
                    break Label_0287;
                }
            }
            else if (this$location.equals(other$location)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$logCaptureTime = this.getLogCaptureTime();
        final Object other$logCaptureTime = other.getLogCaptureTime();
        if (this$logCaptureTime == null) {
            if (other$logCaptureTime == null) {
                return true;
            }
        }
        else if (this$logCaptureTime.equals(other$logCaptureTime)) {
            return true;
        }
        return false;
    }
    
    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof LogDto;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $logType = this.getLogType();
        result = result * 59 + (($logType == null) ? 43 : $logType.hashCode());
        final Object $logParameter = this.getLogParameter();
        result = result * 59 + (($logParameter == null) ? 43 : $logParameter.hashCode());
        final Object $logModular = this.getLogModular();
        result = result * 59 + (($logModular == null) ? 43 : $logModular.hashCode());
        final Object $logExceptionMessage = this.getLogExceptionMessage();
        result = result * 59 + (($logExceptionMessage == null) ? 43 : $logExceptionMessage.hashCode());
        final Object $logInvocation = this.getLogInvocation();
        result = result * 59 + (($logInvocation == null) ? 43 : $logInvocation.hashCode());
        final Object $location = this.getLocation();
        result = result * 59 + (($location == null) ? 43 : $location.hashCode());
        final Object $logCaptureTime = this.getLogCaptureTime();
        result = result * 59 + (($logCaptureTime == null) ? 43 : $logCaptureTime.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "LogDto(id=" + this.getId() + ", logType=" + this.getLogType() + ", logParameter=" + this.getLogParameter() + ", logModular=" + this.getLogModular() + ", logExceptionMessage=" + this.getLogExceptionMessage() + ", logInvocation=" + this.getLogInvocation() + ", location=" + this.getLocation() + ", logCaptureTime=" + this.getLogCaptureTime() + ")";
    }
}
