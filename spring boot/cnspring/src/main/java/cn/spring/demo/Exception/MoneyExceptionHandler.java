package cn.spring.demo.Exception;

import cn.spring.demo.entity.ErrorResult;
import cn.spring.demo.entity.Result;
import cn.spring.demo.utils.ResultEnum;
import cn.spring.demo.utils.ResultUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MoneyExceptionHandler {

    //自定义错误返回的内容
    @ResponseBody
    @ExceptionHandler(Exception.class) //捕获什么类型的异常
    public ErrorResult handle(Exception e) {
        if (e instanceof MoneyException) {
            MoneyException moneyException = (MoneyException) e;
            return ResultUtils.Error(moneyException.getCode(), moneyException.getMessage());
        }
        return ResultUtils.Error(ResultEnum.UNKNOW_ERROR);
    }

}
