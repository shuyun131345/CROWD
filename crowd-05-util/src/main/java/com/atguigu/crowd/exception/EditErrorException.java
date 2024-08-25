package com.atguigu.crowd.exception;

/**
 * @author shuyun
 * @date 2024-08-25 09:57:11
 */
public class EditErrorException extends RuntimeException{
    public EditErrorException() {
        super();
    }

    public EditErrorException(String message) {
        super(message);
    }

    public EditErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public EditErrorException(Throwable cause) {
        super(cause);
    }

    protected EditErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
