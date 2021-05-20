package com.szw.me.framework.executor;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "szw.executor.pool")
public class SzwExecutorProperties {
    private Integer corePoolSize = 10;
    private Integer maxPoolSize = 20;
    private Integer queueCapacity = 200;
    private Integer keepAliveSeconds = 60;
    private String threadNamePrefix = "szwTaskExecutor-";
}
