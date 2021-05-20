package com.szw.me.framework.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.redis")
public class SzwRedisProperties {
    private int database = 0;
    private String host = "localhost";
    private int port = 6379;
}
