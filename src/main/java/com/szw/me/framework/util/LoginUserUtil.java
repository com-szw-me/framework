package com.szw.me.framework.util;

public class LoginUserUtil {

    public static String getUserCode() {
        return ContextUtil.getSzwContext().getCode();
    }

    public static String getUserNick() {
        return ContextUtil.getSzwContext().getNick();
    }
}
