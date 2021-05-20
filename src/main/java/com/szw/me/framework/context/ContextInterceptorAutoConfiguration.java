package com.szw.me.framework.context;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ConditionalOnClass(ContextInterceptor.class)
@EnableConfigurationProperties(ContextInterceptorProperties.class)
public class ContextInterceptorAutoConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(contextInterceptor());
    }

    @Bean
    @ConditionalOnMissingBean
    public ContextInterceptor contextInterceptor() {
        return new ContextInterceptor();
    }
}
