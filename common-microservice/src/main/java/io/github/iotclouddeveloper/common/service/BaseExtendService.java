package io.github.iotclouddeveloper.common.service;

import java.util.*;
import io.github.iotclouddeveloper.common.dto.*;
import io.github.iotclouddeveloper.common.dto.ExtendColumnInfo;
import io.github.iotclouddeveloper.common.dto.ExtendInfo;

public interface BaseExtendService
{
    Map<String, Object> getExtend(final int entityId);
    
    List<ExtendInfo> getExtend(final List<Integer> entityIds);
    
    List<ExtendInfo> getAll();
    
    void addExtend(final ExtendColumnInfo extendColumnInfo);
    
    void dropExtend(final String name);
    
    void addExtendInfo(final ExtendInfo extendInfo);
    
    void deleteExtendInfo(final int userId);
    
    void updateExtendInfo(final ExtendInfo extendInfo);
    
    List<ExtendColumnInfo> getExtendColumnInfo();
}
