package io.github.iotclouddeveloper.common.component;

import org.springframework.context.support.*;
import javax.servlet.http.*;
import java.util.*;
import org.springframework.context.*;
import org.slf4j.*;

public class HvisionsI18nInternational
{
    private static final Logger log;
    private MessageSource messageSource;
    private static final String EN_US = "en-US";
    private static final String ZH_CN = "zh-CN";
    
    public HvisionsI18nInternational(final String basename, final long cacheMillis, final String encoding) {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(basename);
        messageSource.setDefaultEncoding(encoding);
        messageSource.setCacheMillis(cacheMillis);
        this.messageSource = (MessageSource)messageSource;
    }
    
    public String getMessage(final HttpServletRequest request, final String code) {
        final String lauage = request.getHeader("Accept-Language");
        Locale locale;
        if (lauage == null) {
            locale = request.getLocale();
        }
        else if ("en-US".equals(lauage)) {
            locale = Locale.ENGLISH;
        }
        else if ("zh-CN".equals(lauage)) {
            locale = Locale.CHINA;
        }
        else {
            locale = request.getLocale();
        }
        String result = null;
        try {
            result = this.messageSource.getMessage(code, (Object[])null, locale);
        }
        catch (NoSuchMessageException e) {
            HvisionsI18nInternational.log.error("Cannot find the error message of internationalization, return the original error message.");
        }
        if (result == null) {
            return code;
        }
        return result;
    }
    
    static {
        log = LoggerFactory.getLogger((Class)HvisionsI18nInternational.class);
    }
}
