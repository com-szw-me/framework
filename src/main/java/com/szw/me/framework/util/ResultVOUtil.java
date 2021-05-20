package com.szw.me.framework.util;

import com.szw.me.framework.vo.ResultVO;

public class ResultVOUtil {
    public static final String CODE_SUCCESS = "0";
    public static final String MSG_SUCCESS = "success";
    public static final String CODE_ERROR = "-1";
    public static final String MSG_ERROR = "error";

    public static ResultVO success() {
        return new ResultVO(CODE_SUCCESS, MSG_SUCCESS, null);
    }

    public static ResultVO success(Object data) {
        return new ResultVO(CODE_SUCCESS, MSG_SUCCESS, data);
    }

    public static ResultVO error() {
        return new ResultVO(CODE_ERROR, MSG_ERROR, null);
    }

    public static ResultVO error(String msg) {
        return new ResultVO(CODE_ERROR, msg, null);
    }

    public static ResultVO error(String code, String msg) {
        return new ResultVO(code, msg, null);
    }
}
