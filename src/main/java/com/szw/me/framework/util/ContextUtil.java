package com.szw.me.framework.util;

import com.szw.me.framework.context.SzwContext;

public class ContextUtil {
    public static SzwContext getSzwContext() {
        return SzwContext.getSzwThreadLocal().get();
    }
}
