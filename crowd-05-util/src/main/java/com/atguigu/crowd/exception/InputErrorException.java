package com.atguigu.crowd.exception;

/**
 * @author shuyun
 * @date 2024-08-25 09:57:11
 */
public class InputErrorException extends RuntimeException{
    public InputErrorException() {
        super();
    }

    public InputErrorException(String message) {
        super(message);
    }

    public InputErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputErrorException(Throwable cause) {
        super(cause);
    }

    protected InputErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
