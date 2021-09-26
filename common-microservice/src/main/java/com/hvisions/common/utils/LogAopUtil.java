package com.hvisions.common.utils;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.*;
import org.springframework.data.redis.core.*;
import com.hvisions.common.interfaces.*;
import com.hvisions.common.dto.*;
import org.springframework.beans.factory.annotation.*;
import org.aspectj.lang.*;
import org.aspectj.lang.reflect.*;
import cn.hutool.core.util.*;
import org.springframework.core.*;
import com.hvisions.common.consts.*;
import org.springframework.web.context.request.*;
import javax.servlet.http.*;
import java.lang.reflect.*;
import java.util.*;
import com.hvisions.common.enums.*;
import java.io.*;
import org.aspectj.lang.annotation.*;

@Aspect
@Component
public class LogAopUtil
{
    private final StringRedisTemplate stringRedisTemplate;
    private final ILog iLog;
    private static final ThreadLocal<LogDto> THREAD_LOCAL;
    @Value("${h-visions.service-name:服务名未配置}")
    private String logModular;
    @Value("${h-visions.log.enable:true}")
    private boolean enable;
    
    public LogAopUtil(final StringRedisTemplate stringRedisTemplate, final ILog iLog) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.iLog = iLog;
    }
    
    @Pointcut("execution(* com.hvisions..*.*Controller.*(..))")
    public void controllerMethodPointcut() {
    }
    
    private void addLog(final JoinPoint point) {
        // todo log
//        final LogDto logEntity = new LogDto();
//        LogAopUtil.THREAD_LOCAL.set(logEntity);
//        final MethodSignature signature = (MethodSignature)point.getSignature();
//        logEntity.setLogModular(this.logModular);
//        final RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        final ServletRequestAttributes sra = (ServletRequestAttributes)ra;
//        final HttpServletRequest httpServletRequest = sra.getRequest();
//        final String token = httpServletRequest.getHeader("token");
//        if (token != null) {
//            final String userIdStr = (String)this.stringRedisTemplate.opsForValue().get(String.format("auth_%s", token));
//            logEntity.setLogInvocation(userIdStr);
//        }
//        Method m = ((MethodSignature)point.getSignature()).getMethod();
//        if (point.getThis().getClass() != point.getTarget().getClass()) {
//            m = ReflectUtil.getMethod((Class)point.getTarget().getClass(), m.getName(), (Class[])m.getParameterTypes());
//        }
//        final LocalVariableTableParameterNameDiscoverer paramNames = new LocalVariableTableParameterNameDiscoverer();
//        final String[] parameterNames = paramNames.getParameterNames(m);
//        final Object[] args = point.getArgs();
//        final Map<String, Object> params = new HashMap<String, Object>(IntegerConst.MAP_SIZE);
//        assert parameterNames != null;
//        for (int i = 0; i < parameterNames.length; ++i) {
//            params.put(parameterNames[i], args[i]);
//        }
//        logEntity.setLogParameter(params.toString());
//        logEntity.setLocation(httpServletRequest.getRequestURI());
    }
    
    @Before("controllerMethodPointcut()")
    public void before(final JoinPoint point) {
        if (!this.enable) {
            return;
        }
        this.addLog(point);
    }
    
    @AfterReturning(value = "controllerMethodPointcut()", returning = "rtv")
    public void after(final JoinPoint point, final Object rtv) {
        if (!this.enable) {
            return;
        }
        final LogDto log = LogAopUtil.THREAD_LOCAL.get();
        log.setLogType(LogTypeEnum.SUCCESS.getCode());
        try {
            this.iLog.logRecord(log);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        LogAopUtil.THREAD_LOCAL.remove();
    }
    
    @AfterThrowing(value = "controllerMethodPointcut()", throwing = "throwing")
    public void error(Throwable throwing) {
        if (!this.enable) {
            return;
        }
        final LogDto log = LogAopUtil.THREAD_LOCAL.get();
        try (final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            throwing.printStackTrace(new PrintStream(byteArrayOutputStream));
            log.setLogExceptionMessage(byteArrayOutputStream.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        log.setLogType(LogTypeEnum.ERROR.getCode());
        try {
            this.iLog.logRecord(log);
        }
        catch (Exception a) {
            a.printStackTrace();
        }
        LogAopUtil.THREAD_LOCAL.remove();
    }
    
    static {
        THREAD_LOCAL = new ThreadLocal<LogDto>();
    }
}
