package io.github.iotclouddeveloper.common.interfaces;

public interface IConverter<IN, OUT>
{
    OUT convert(final IN in);
}
