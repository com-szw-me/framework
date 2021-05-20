package com.szw.me.framework.context;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SzwContext {

    private String code;
    private String nick;
    private String token;
    private static final ThreadLocal<SzwContext> szwThreadLocal = new ThreadLocal<SzwContext>();

    public static ThreadLocal<SzwContext> getSzwThreadLocal() {
        return szwThreadLocal;
    }

    public static void setSzwContext(SzwContext szwContext) {
        szwThreadLocal.set(szwContext);
    }

    public static void removeSzwContext() {
        szwThreadLocal.remove();
    }
}
