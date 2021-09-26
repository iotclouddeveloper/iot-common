package com.hvisions.common.interfaces;

public interface IConverter<IN, OUT>
{
    OUT convert(final IN in);
}
