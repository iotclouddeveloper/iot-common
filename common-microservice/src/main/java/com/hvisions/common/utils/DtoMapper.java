package com.hvisions.common.utils;

import org.springframework.beans.*;
import java.util.stream.*;
import org.springframework.data.domain.*;
import com.hvisions.common.interfaces.*;
import java.util.function.*;
import java.util.*;
import com.google.common.collect.*;
import com.hvisions.common.annotation.*;
import java.lang.annotation.*;
import java.lang.reflect.*;
import org.slf4j.*;

public class DtoMapper
{
    private static final Logger log;
    
    public static <T> T convert(final Object obj, final Class<T> clas) {
        final T t = (T)BeanUtils.instantiateClass((Class)clas);
        BeanUtils.copyProperties(obj, t);
        return t;
    }
    
    @Deprecated
    public static <T> Page<T> convertPage(final Page page, final Pageable pageable, final Class<T> clas) {
        return (Page<T>)new PageImpl((List)page.stream().map(t -> convert(t, clas)).collect((Collector<? super Object, ?, List<Object>>)Collectors.toList()), pageable, page.getTotalElements());
    }
    
    public static <IN, OUT> Page<OUT> convertPage(final Page<IN> page, final IConverter<IN, OUT> converter) {
        return (Page<OUT>)new PageImpl((List)page.stream().map(converter::convert).collect((Collector<? super Object, ?, List<Object>>)Collectors.toList()), page.getPageable(), page.getTotalElements());
    }
    
    public static <IN, OUT> Page<OUT> convertPage(final Page<IN> page, final Function<IN, OUT> function) {
        return (Page<OUT>)new PageImpl((List)page.stream().map(function::apply).collect((Collector<? super Object, ?, List<Object>>)Collectors.toList()), page.getPageable(), page.getTotalElements());
    }
    
    public static <T> Page<T> convertPage(final Page page, final Class<T> clas) {
        return (Page<T>)new PageImpl((List)page.stream().map(t -> convert(t, clas)).collect((Collector<? super Object, ?, List<Object>>)Collectors.toList()), page.getPageable(), page.getTotalElements());
    }
    
    public static <IN, OUT> List<OUT> convertList(final List<IN> list, final IConverter<IN, OUT> converter) {
        return list.stream().map(converter::convert).collect(Collectors.toList());
    }
    
    public static <IN, OUT> List<OUT> convertList(final List<IN> list, final Function<IN, OUT> function) {
        return list.stream().map(function::apply).collect(Collectors.toList());
    }
    
    public static <T> List<T> convertList(final List list, final Class<T> clas) {
        return (List<T>)list.stream().map(t -> convert(t, clas)).collect(Collectors.toList());
    }
    
    public static Map<String, Object> convertMap(final Object object) throws IllegalAccessException {
        final Field[] fields = ReflectUtil.getAllFields(object.getClass());
        final Map<String, Object> result = Maps.newHashMap();
        for (final Field field : fields) {
            if (!field.isAnnotationPresent((Class<? extends Annotation>)MapperIgnore.class)) {
                final String key = field.getName();
                final Object value = field.get(key);
                result.put(key, value);
            }
        }
        return result;
    }
    
    public static <T> T convertObject(final Map<String, Object> map, final Class<T> claz) {
        final Field[] fields = ReflectUtil.getAllFields(claz);
        final T entity = (T)BeanUtils.instantiateClass((Class)claz);
        for (final Field field : fields) {
            if (!field.isAnnotationPresent((Class<? extends Annotation>)MapperIgnore.class)) {
                final Object value = map.get(field.getName());
                if (value != null) {
                    try {
                        field.set(entity, value);
                    }
                    catch (Exception ex) {
                        DtoMapper.log.info("field :{} parse error", field.toString());
                    }
                }
            }
        }
        return entity;
    }
    
    static {
        log = LoggerFactory.getLogger((Class)DtoMapper.class);
    }
}
