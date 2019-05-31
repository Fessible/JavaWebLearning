package cn.spring.demo.utils;

public enum ResultEnum {

    UNKNOW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    MIN_MONEY(100, "金额不得小于50");

    private String msg;
    private Integer code;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
