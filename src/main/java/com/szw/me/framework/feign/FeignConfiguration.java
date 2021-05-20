package com.szw.me.framework.feign;

import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
    @Bean
    @ConditionalOnMissingBean(RequestInterceptor.class)
    public RequestInterceptor requestInterceptor() {
        return new TokenInterceptor();
    }
}
