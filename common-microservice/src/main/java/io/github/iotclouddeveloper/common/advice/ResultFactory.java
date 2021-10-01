package io.github.iotclouddeveloper.common.advice;

import javax.servlet.http.*;
import io.github.iotclouddeveloper.common.component.HvisionsI18nInternational;
import io.github.iotclouddeveloper.common.exception.BaseKnownException;
import io.github.iotclouddeveloper.common.utils.HvisionsExceptionMapper;
import io.github.iotclouddeveloper.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;

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
