package com.szw.me.framework.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultVO<T> {

    private String code;
    private String msg;
    private T data;
}
