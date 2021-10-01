package io.github.iotclouddeveloper.common.advice;

import javax.servlet.http.*;
import io.github.iotclouddeveloper.common.vo.ResultVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.slf4j.*;

@ControllerAdvice
public class ControllerExceptionHandler
{
    private static final Logger log;
    ResultFactory resultFactory;
    
    public ControllerExceptionHandler(final ResultFactory resultFactory) {
        this.resultFactory = resultFactory;
    }
    
    @ExceptionHandler({ Throwable.class })
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResultVO handlerException(final Throwable ex, final HttpServletRequest request) {
        ex.printStackTrace();
        return this.resultFactory.getResultVOByException(ex, request);
    }
    
    static {
        log = LoggerFactory.getLogger((Class)ControllerExceptionHandler.class);
    }
}
