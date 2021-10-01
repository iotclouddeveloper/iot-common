package io.github.iotclouddeveloper.common.advice;

import io.github.iotclouddeveloper.common.annotation.ApiResultIgnore;
import io.github.iotclouddeveloper.common.vo.ResultVO;
import org.springframework.web.servlet.mvc.method.annotation.*;
import com.fasterxml.jackson.databind.*;
import java.util.function.*;
import org.springframework.core.*;
import org.springframework.http.*;
import org.springframework.http.server.*;
import com.fasterxml.jackson.core.*;

import java.util.*;
import java.lang.reflect.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class HvisionsApiResultHandler implements ResponseBodyAdvice
{
    private ThreadLocal<ObjectMapper> mapperThreadLocal;
    private static final Class[] ANNOS;
    private static final Class IGNORE;
    
    public HvisionsApiResultHandler() {
        this.mapperThreadLocal = ThreadLocal.withInitial((Supplier<? extends ObjectMapper>)ObjectMapper::new);
    }
    
    public Object beforeBodyWrite(final Object returnValue, final MethodParameter methodParameter, final MediaType mediaType, final Class clas, final ServerHttpRequest serverHttpRequest, final ServerHttpResponse serverHttpResponse) {
        final ObjectMapper mapper = this.mapperThreadLocal.get();
        if (returnValue instanceof String) {
            try {
                final Object strObject = mapper.writeValueAsString(ResultVO.success(returnValue));
                serverHttpResponse.getHeaders().setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));
                return strObject;
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ResultVO.success(returnValue);
    }
    
    public boolean supports(final MethodParameter returnType, final Class converterType) {
        final AnnotatedElement element = returnType.getAnnotatedElement();
        // todo
        return returnType.getContainingClass().toString().contains("hvisions") && !element.isAnnotationPresent(HvisionsApiResultHandler.IGNORE) && Arrays.stream(HvisionsApiResultHandler.ANNOS).anyMatch(anno -> anno.isAnnotation());
//        final AnnotatedElement annotatedElement = null;
//        return returnType.getContainingClass().toString().contains("hvisions") && !element.isAnnotationPresent(HvisionsApiResultHandler.IGNORE) && Arrays.stream(HvisionsApiResultHandler.ANNOS).anyMatch(anno -> anno.isAnnotation() && annotatedElement.isAnnotationPresent(anno));
    }
    
    static {
        ANNOS = new Class[] { RequestMapping.class, GetMapping.class, PostMapping.class, DeleteMapping.class, PutMapping.class };
        IGNORE = ApiResultIgnore.class;
    }
}
