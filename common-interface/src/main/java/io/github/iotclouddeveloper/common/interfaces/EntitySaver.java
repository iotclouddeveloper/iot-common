package io.github.iotclouddeveloper.common.interfaces;

public interface EntitySaver<T>
{
    void saveOrUpdate(final T t);
}
