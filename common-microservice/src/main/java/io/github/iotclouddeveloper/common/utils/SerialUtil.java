package io.github.iotclouddeveloper.common.utils;

import org.springframework.data.redis.core.*;
import java.text.*;
import java.util.*;
import org.springframework.util.*;
import java.util.concurrent.*;

public class SerialUtil
{
    private StringRedisTemplate stringRedisTemplate;
    
    public SerialUtil(final StringRedisTemplate template) {
        this.stringRedisTemplate = template;
    }
    
    public String getSerialNumber(final String service) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        final String dateString = simpleDateFormat.format(new Date());
        final String key = String.format("SerialNumber-%s-%s", service, dateString);
        String values = (String)this.stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(values)) {
            values = "0";
        }
        String number = "0000" + (Integer.parseInt(values) + 1);
        number = number.substring(number.length() - 4);
        this.depositRedis(key, number);
        return dateString + number;
    }
    
    private void depositRedis(final String key, final String value) {
        this.stringRedisTemplate.opsForValue().set(key, value);
        this.stringRedisTemplate.expire(key, 1L, TimeUnit.DAYS);
    }
}
