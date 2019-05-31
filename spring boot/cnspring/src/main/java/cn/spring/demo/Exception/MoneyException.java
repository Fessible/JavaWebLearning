package cn.spring.demo.Exception;

import cn.spring.demo.utils.ResultEnum;

public class MoneyException extends RuntimeException {

    private Integer code;

    public MoneyException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public MoneyException(ResultEnum minMoney) {
        super(minMoney.getMsg());
        this.code = minMoney.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
