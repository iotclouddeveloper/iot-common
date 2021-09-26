package com.hvisions.common.utils;

import javax.servlet.http.*;
import com.hvisions.common.consts.*;
import java.util.*;

public class HttpHeaderUtil
{
    static Map<String, String> getHeader(final HttpServletRequest request) {
        final Enumeration<String> headerNames = (Enumeration<String>)request.getHeaderNames();
        final Map<String, String> result = new HashMap<String, String>(IntegerConst.MAP_SIZE);
        while (headerNames.hasMoreElements()) {
            final String elementKey = headerNames.nextElement();
            final String value = request.getHeader(elementKey);
            result.put(elementKey, value);
        }
        return result;
    }
}
