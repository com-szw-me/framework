package com.szw.me.framework.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@EnableConfigurationProperties(SzwExecutorProperties.class)
public class SzwExecutorAutoConfiguration {

    @Autowired
    SzwExecutorProperties szwExecutorProperties;

    @Bean
    public Executor szwTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(szwExecutorProperties.getCorePoolSize());
        executor.setMaxPoolSize(szwExecutorProperties.getMaxPoolSize());
        executor.setQueueCapacity(szwExecutorProperties.getQueueCapacity());
        executor.setKeepAliveSeconds(szwExecutorProperties.getKeepAliveSeconds());
        executor.setThreadNamePrefix(szwExecutorProperties.getThreadNamePrefix());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
