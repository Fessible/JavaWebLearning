package cn.spring.demo.utils;

import cn.spring.demo.entity.ErrorResult;
import cn.spring.demo.entity.Result;

public class ResultUtils {

    public static Result success(Object object) {
        Result result = new Result();
        result.setMsg("成功");
        result.setStatus(0);
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static ErrorResult Error(int status, String msg) {
        ErrorResult result = new ErrorResult();
        result.setMsg(msg);
        result.setStatus(status);
        return result;
    }

    public static ErrorResult Error(ResultEnum unknowError) {
        ErrorResult result = new ErrorResult();
        result.setStatus(unknowError.getCode());
        result.setMsg(unknowError.getMsg());
        return result;
    }
}
