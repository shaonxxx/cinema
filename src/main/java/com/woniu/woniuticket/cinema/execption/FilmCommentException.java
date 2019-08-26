package com.woniu.woniuticket.cinema.execption;

public class FilmCommentException extends RuntimeException {
    public FilmCommentException() {
        super();
    }

    public FilmCommentException(String message) {
        super(message);
    }

    public FilmCommentException(String message, Throwable cause) {
        super(message, cause);
    }

    public FilmCommentException(Throwable cause) {
        super(cause);
    }

    protected FilmCommentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
