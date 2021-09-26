package com.hvisions.common.advice;

import com.hvisions.common.utils.*;
import com.hvisions.common.component.*;
import javax.servlet.http.*;
import com.hvisions.common.vo.*;
import com.hvisions.common.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.*;

import java.util.logging.Logger;

@Slf4j
public class ResultFactory
{
    public static final String MESSAGE_SPLIT = " : ";
    private HvisionsExceptionMapper hVisionsExceptionMapper;
    private HvisionsI18nInternational i18nComponent;
    
    public ResultFactory(final HvisionsI18nInternational hVisionsI18nInternational, final HvisionsExceptionMapper hVisionsExceptionMapper) {
        this.i18nComponent = hVisionsI18nInternational;
        this.hVisionsExceptionMapper = hVisionsExceptionMapper;
    }
    
    public ResultVO getResultVOByException(final Throwable ex, final HttpServletRequest request) {
        int code;
        String message;
        if (ex instanceof BaseKnownException) {
            final BaseKnownException exception = (BaseKnownException)ex;
            code = exception.getCode();
            message = this.i18nComponent.getMessage(request, exception.getMessage());
            if (exception.getArgs() != null) {
                message = String.format(message, exception.getArgs());
            }
        }
        else {
            final Throwable causeException = this.getRootCause(ex);
            final String messageCode = this.hVisionsExceptionMapper.getMessage(causeException);
            code = this.hVisionsExceptionMapper.getMessageCode(causeException);
            final String extendMessage = this.hVisionsExceptionMapper.getExtendMessage(causeException);
            message = this.i18nComponent.getMessage(request, messageCode);
            message = message + " : " + this.i18nComponent.getMessage(request, extendMessage);
        }
        return new ResultVO(code, message);
    }
    
    private Throwable getRootCause(final Throwable ex) {
        if (ex.getCause() == null) {
            return ex;
        }
        return (ex.getCause() == ex) ? ex : this.getRootCause(ex.getCause());
    }
    
}
