package io.github.iotclouddeveloper.common.utils;

import java.lang.reflect.*;
import java.util.*;

public class ReflectUtil
{
    private static final int MAX_INHERIT_DEPTH = 5;
    
    public static Field[] getAllFields(Class clazz) {
        final List<Field> fieldList = new ArrayList<Field>();
        int inheritTimes = 0;
        while (clazz != null && clazz != Object.class) {
            final Field[] declaredFields;
            final Field[] fields = declaredFields = clazz.getDeclaredFields();
            for (final Field f : declaredFields) {
                if (!fieldList.contains(f)) {
                    fieldList.add(f);
                    f.setAccessible(true);
                }
            }
            clazz = clazz.getSuperclass();
            if (++inheritTimes > 5) {
                break;
            }
        }
        final Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }
}
