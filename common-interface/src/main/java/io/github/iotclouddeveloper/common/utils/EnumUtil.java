package io.github.iotclouddeveloper.common.utils;

import io.github.iotclouddeveloper.common.consts.*;
import io.github.iotclouddeveloper.common.enums.*;
import io.github.iotclouddeveloper.common.exception.*;
import io.github.iotclouddeveloper.common.interfaces.*;
import io.github.iotclouddeveloper.common.consts.IntegerConst;
import io.github.iotclouddeveloper.common.enums.BaseErrorEnum;
import io.github.iotclouddeveloper.common.exception.BaseKnownException;
import io.github.iotclouddeveloper.common.interfaces.IKeyValueObject;

import java.util.*;

public class EnumUtil
{
    public static <T extends IKeyValueObject> Map<Integer, String> enumToMap(final Class<T> enumT) {
        final Map<Integer, String> enummap = new HashMap<Integer, String>(IntegerConst.MAP_SIZE);
        if (!enumT.isEnum()) {
            return enummap;
        }
        final T[] enums = enumT.getEnumConstants();
        if (enums == null || enums.length <= 0) {
            return enummap;
        }
        for (int i = 0, len = enums.length; i < len; ++i) {
            final T tObj = enums[i];
            final Integer value = tObj.getCode();
            final String name = tObj.getName();
            enummap.put(value, name);
        }
        return enummap;
    }
    
    public static <T extends IKeyValueObject> T valueOf(final Integer code, final Class<T> clazz) {
        final T[] enums = clazz.getEnumConstants();
        final Optional<T> result = Arrays.stream(enums).filter(t -> t.getCode().equals(code)).findFirst();
        if (!result.isPresent()) {
            throw new BaseKnownException(BaseErrorEnum.ENUM_NOT_EXISTS, new Object[0]);
        }
        return result.get();
    }
    
    public static <T extends IKeyValueObject> String getName(final Integer code, final Class<T> clazz) {
        return valueOf(code, clazz).getName();
    }
}
