package com.atguigu.crowd.entity;

public class ResultEntity<T> {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";


    //请求结果，成功或失败
    private String result;

    //返回的错误信息
    private String message;


    //返回的数据
    private T data;

    public ResultEntity() {
    }

    public ResultEntity(String result , String message, T data) {
        this.message = message;
        this.result = result;
        this.data = data;
    }


    /**
     * 请求成功，有返回信息，有返回数据
     * @param message
     * @param data
     * @return
     * @param <E>
     */
    public static <E> ResultEntity<E> successWithData(String message,E data){
        return new ResultEntity<>(SUCCESS,message,data);
    }


    /**
     * 请求失败，有返回信息和数据
     * @param message
     * @param data
     * @return
     * @param <E>
     */
    public static <E> ResultEntity<E> failedWithData(String message, E data){
        return new ResultEntity<>(FAILED,message,data);
    }

}
