package com.hvisions.common.utils;

import javax.servlet.http.*;
import java.util.*;

public class CookieUtil
{
    public static Cookie setCookie(final HttpServletResponse respose, final String key, final String value, final int maxAge) {
        final Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        respose.addCookie(cookie);
        return cookie;
    }
    
    public static Cookie getCookie(final HttpServletRequest httpServletRequest, final String name) {
        final Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            final Optional<Cookie> result = Arrays.stream(cookies).filter(t -> t.getName().equals(name)).findFirst();
            if (result.isPresent()) {
                return result.get();
            }
        }
        return null;
    }
}
