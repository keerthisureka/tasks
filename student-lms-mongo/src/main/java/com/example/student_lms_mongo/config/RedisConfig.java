package com.example.student_lms_mongo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
@EnableCaching
public class RedisConfig {
    @Value("${redis.port}")
    private int port;

    @Value("${redis.host}")
    private String host;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {

        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
        jedisConFactory.setHostName(host);
        jedisConFactory.setPort(port);
        return jedisConFactory;
    }
}
