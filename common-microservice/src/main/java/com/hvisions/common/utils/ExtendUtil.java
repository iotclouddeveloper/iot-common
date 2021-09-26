package com.hvisions.common.utils;

import com.hvisions.common.service.*;
import org.springframework.data.domain.*;
import com.hvisions.common.interfaces.*;
import java.util.function.*;
import java.util.stream.*;
import com.hvisions.common.dto.*;
import java.util.*;

public class ExtendUtil
{
    public static <T extends IExtendObject> Page<T> completeExtend(final BaseExtendService service, final Page<T> pageExtend) {
        final List<Integer> ids = pageExtend.stream().map(IExtendObject::getId).collect(Collectors.toList());
        final List<ExtendInfo> extendInfos = service.getExtend(ids);
        for (final ExtendInfo extendInfo : extendInfos) {
            final Optional<T> object = pageExtend.stream().filter(t -> t.getId() == extendInfo.getEntityId()).findFirst();
            object.ifPresent(iExtendObject -> iExtendObject.setExtend((Map)extendInfo.getValues()));
        }
        return pageExtend;
    }
    
    public static <T extends IExtendObject> List<T> completeExtend(final BaseExtendService service, final List<T> pageExtend) {
        final List<Integer> ids = pageExtend.stream().map(IExtendObject::getId).collect(Collectors.toList());
        final List<ExtendInfo> extendInfos = service.getExtend(ids);
        for (final ExtendInfo extendInfo : extendInfos) {
            final Optional<T> object = pageExtend.stream().filter(t -> t.getId() == extendInfo.getEntityId()).findFirst();
            object.ifPresent(iExtendObject -> iExtendObject.setExtend((Map)extendInfo.getValues()));
        }
        return pageExtend;
    }
    
    public static <T extends IExtendObject> IExtendObject completeExtend(final BaseExtendService service, final T iExtendObject) {
        final Map<String, Object> extendMap = service.getExtend(iExtendObject.getId());
        iExtendObject.setExtend((Map)extendMap);
        return iExtendObject;
    }
}
