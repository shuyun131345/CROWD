package com.atguigu.crowd.entity.common;

/**
 * @author shuyun
 * @date 2024-08-17 19:27:07
 */
public class AjaxResultEntity<T> {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";

    private String result;
    private String message;
    private T data;


    /**
     * 发送失败
     * @param message 失败消息
     * @param data 数据
     * @return
     * @param <E>
     */
    public static <E> AjaxResultEntity<E> failed(String message,E data) {
        return new AjaxResultEntity(FAILED,message,data);
    }


    /**
     * 发送成功
     * @param message
     * @param data
     * @return
     * @param <E>
     */
    public static <E> AjaxResultEntity<E> success(String message,E data){
        return new AjaxResultEntity(SUCCESS,message,data);
    }

    public AjaxResultEntity() {
    }

    private AjaxResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AjaxResultEntity{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
