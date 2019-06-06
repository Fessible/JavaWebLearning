package com.example.wechat.exception;

import com.example.wechat.enums.ResultEnum;

public class SellException extends RuntimeException {
    private int code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
