package com.hvisions.common.exception;

import feign.hystrix.*;
import com.hvisions.common.vo.*;
import org.apache.commons.lang.*;
import org.slf4j.*;

public abstract class BaseFallbackFactory<T> implements FallbackFactory
{
    private static final Logger log;
    
    private ResultVO handleError(final Throwable throwable) {
        String msg = "feign远程服务调用异常";
        if (null != throwable) {
            msg = (StringUtils.isNotBlank(throwable.getMessage()) ? throwable.getMessage() : "feign远程服务调用异常");
        }
        BaseFallbackFactory.log.error("feign远程服务调用异常", throwable);
        return new ResultVO(500, msg);
    }
    
    public T create(final Throwable throwable) {
        final ResultVO vo = this.handleError(throwable);
        return this.getFallBack(vo);
    }
    
    public abstract T getFallBack(final ResultVO vo);
    
    static {
        log = LoggerFactory.getLogger(BaseFallbackFactory.class);
    }
}
