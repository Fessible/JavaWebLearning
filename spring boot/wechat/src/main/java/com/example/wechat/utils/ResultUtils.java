package com.example.wechat.utils;

import com.example.wechat.VO.ResultVO;

public class ResultUtils {

    public static ResultVO success(Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(data);
        resultVO.setMessage("成功");
        resultVO.setCode(0);
        return resultVO;
    }

    public static ResultVO failue(int code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(msg);
        return resultVO;
    }
}
