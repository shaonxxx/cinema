package com.woniu.woniuticket.cinema.execption;

public class ScreeningExecption extends RuntimeException {

    public ScreeningExecption() {
        super();
    }

    public ScreeningExecption(String message) {
        super(message);
    }

    public ScreeningExecption(String message, Throwable cause) {
        super(message, cause);
    }

    public ScreeningExecption(Throwable cause) {
        super(cause);
    }

    protected ScreeningExecption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
