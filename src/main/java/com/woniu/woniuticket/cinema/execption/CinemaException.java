package com.woniu.woniuticket.cinema.execption;

public class CinemaException extends RuntimeException {
    public CinemaException() {
        super();
    }

    public CinemaException(String message) {
        super(message);
    }

    public CinemaException(String message, Throwable cause) {
        super(message, cause);
    }

    public CinemaException(Throwable cause) {
        super(cause);
    }

    protected CinemaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
