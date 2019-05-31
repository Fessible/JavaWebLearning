package cn.spring.demo.entity;

/**
 * 请求返回的对象
 */
public class Result<T> extends ErrorResult {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
