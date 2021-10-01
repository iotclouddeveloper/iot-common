package io.github.iotclouddeveloper.common.utils;

import io.github.iotclouddeveloper.common.service.*;
import io.github.iotclouddeveloper.common.dto.ExtendInfo;
import io.github.iotclouddeveloper.common.interfaces.IExtendObject;
import io.github.iotclouddeveloper.common.service.BaseExtendService;
import org.springframework.data.domain.*;
import io.github.iotclouddeveloper.common.interfaces.*;

import java.util.stream.*;
import io.github.iotclouddeveloper.common.dto.*;
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
