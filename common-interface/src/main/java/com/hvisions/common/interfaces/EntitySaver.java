package com.hvisions.common.interfaces;

public interface EntitySaver<T>
{
    void saveOrUpdate(final T t);
}
