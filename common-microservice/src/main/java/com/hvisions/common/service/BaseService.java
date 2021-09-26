package com.hvisions.common.service;

import java.util.*;

@Deprecated
public interface BaseService<T, E>
{
    T findOne(final E id);
    
    T save(final T t);
    
    void delete(final E id);
    
    List<T> findAll();
    
    boolean exists(final E id);
}
