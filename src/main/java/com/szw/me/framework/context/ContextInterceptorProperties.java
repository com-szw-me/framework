package com.szw.me.framework.context;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties("spring.framework.context")
public class ContextInterceptorProperties {
    private List<String> blackList = new ArrayList<>();
    private List<String> whiteList = new ArrayList<>();
}
