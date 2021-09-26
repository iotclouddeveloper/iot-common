package com.hvisions.common.interfaces;

import java.util.*;

public interface IExtendObject
{
    Integer getId();
    
    Map<String, Object> getExtend();
    
    void setExtend(final Map<String, Object> stringObjectHashMap);
}
