package com.dq.house.common.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author DQ1243
 */

// 自定义RedisTemplate的配置类，主要是为了设置序列化器，解决默认的JDK序列化方式导致的乱码问题
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Object> stringObjectRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // 设置key的序列化器为StringRedisSerializer
        template.setKeySerializer(RedisSerializer.string());
        // 设置value的序列化器为java序列化器
        template.setValueSerializer(RedisSerializer.java());
        return template;
    }
}
