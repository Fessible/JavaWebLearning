package com.example.wechat.VO;

import lombok.Data;

/**
 * 最外层数据
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String message;

    private T data;
}
