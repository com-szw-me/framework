package com.szw.me.framework.feign;

import com.szw.me.framework.context.ContextInterceptor;
import com.szw.me.framework.context.SzwContext;
import com.szw.me.framework.util.ContextUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class TokenInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        SzwContext context = ContextUtil.getSzwContext();
        if (context != null) {
            context.getToken();
            template.header(ContextInterceptor.TOKEN_KEY, context.getToken());
        }
    }
}
